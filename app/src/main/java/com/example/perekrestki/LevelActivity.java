package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelActivity extends AppCompatActivity {
    DBHelper db;
    ViewFlipper flipper;
    Button btn1,btn2,btn3,btnNxt,btnNxtLvl;
    MotionLayout ml;
    Scenes currentScene;
    int curSceneNum = 1;
    int scenesCount;
    int lvlNum;
    int fails = 0;
    //for infinity mode
    Boolean isInfinity = false;
    List<Scenes> sceneList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        db = new DBHelper(this);
        flipper = findViewById(R.id.sceneContainer);
        TextView lvlText = findViewById(R.id.lvlTxt);
        isInfinity = getIntent().getBooleanExtra("Infinity",false);
        if(!isInfinity) {
            lvlNum = getIntent().getIntExtra("Number", 0);
            Cursor res = db.getLevel(lvlNum);
            res.moveToFirst();
            fails = res.getInt(1);
            lvlText.setText("Уровень " + lvlNum);
            loadScenesData();
        }
        else {
            loadInfinityData();
        }
        loadLayoutData();
        loadButtonsData();
    }

    private void loadInfinityData() {
        Cursor scene = db.getScenes();
        scenesCount = scene.getCount();
        while (scene.moveToNext()){
            sceneList.add(new Scenes(scene.getInt(0), scene.getInt(1), scene.getInt(2), scene.getInt(3), scene.getInt(4), scene.getInt(5), scene.getString(6), scene.getString(7), scene.getString(8), scene.getInt(9)));
        }
        Collections.shuffle(sceneList);
        ((TextView)findViewById(R.id.lvlTxt)).setText("Бесконечный режим");
    }
    private void loadScenesData() {
        Cursor lvlscene = db.getLevelScene(lvlNum);
        scenesCount = lvlscene.getCount();
        while(lvlscene.moveToNext()) {
            Cursor scene = db.getScene(lvlscene.getInt(2));
            while (scene.moveToNext()) {
                sceneList.add(new Scenes(scene.getInt(0), scene.getInt(1), scene.getInt(2), scene.getInt(3), scene.getInt(4), scene.getInt(5), scene.getString(6), scene.getString(7), scene.getString(8), scene.getInt(9)));
            }
        }
    }
    public void openHelp(View view) {
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    private void loadButtonsData() {
        List<String> btnTextList = new ArrayList<>();
        btnTextList.add(currentScene.correct);
        btnTextList.add(currentScene.second);
        btnTextList.add(currentScene.third);
        Collections.shuffle(btnTextList);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btnNxt = findViewById(R.id.buttonNxt);
        btnNxt.setVisibility(View.INVISIBLE);
        btnNxtLvl = findViewById(R.id.buttonNxtLvl);
        btn1.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn1.setText(btnTextList.get(0));
        btn2.setText(btnTextList.get(1));
        btn3.setText(btnTextList.get(2));
    }

    private void loadLayoutData() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        try {
            flipper.removeViewAt(1);
        }
        catch (Exception exception){}
        currentScene = sceneList.get(curSceneNum-1);
        View child = inflater.inflate(currentScene.layout, (ViewGroup) findViewById(R.id.sceneContainer), false);
        flipper.addView(child);
        ml = ((MotionLayout) findViewById(currentScene.idML));
        flipper.setDisplayedChild(1);
    }

    private void correctBtnPressed(Boolean nxtLevelPressed) {
        if(curSceneNum==scenesCount){
            if(isInfinity) {
                Toast msg = Toast.makeText(this,"Вы дошли до конца.",Toast.LENGTH_SHORT);
                msg.setGravity(Gravity.CENTER,0,-130);
                msg.show();
                onBackPressed();
            }
            else{
                Cursor res = db.getUserStat();
                res.moveToFirst();
                db.updateUserStat(1, (lvlNum<res.getInt(1))?res.getInt(1):lvlNum, res.getInt(2), res.getInt(3));
                res.close();
                if(nxtLevelPressed){
                    res = db.getScene(lvlNum+1);
                    if (res.getCount() == 0) {
                        startActivity(new Intent(this, LevelActivity.class).putExtra("Number", lvlNum + 1));
                    }
                    return;
                }
                startActivity(new Intent(LevelActivity.this,LevelInfoActivity.class).putExtra("Number",""+lvlNum).putExtra("isAvaible",true));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        }
        else{
            curSceneNum++;
            loadLayoutData();
            loadButtonsData();
        }
    }

    public void goMain(View view) {
        startActivity(new Intent(LevelActivity.this,LevelInfoActivity.class).putExtra("Number",""+lvlNum).putExtra("isAvaible",true));
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void buttonClick(View view) throws InterruptedException {
        Button button = (Button) view;
        if(button.getText() == currentScene.correct){
            //ml.loadLayoutDescription(currentScene.correctMS);
            changeButtonBack(view,R.drawable.correct_back_set,300);
            return;
        }
        else if (button.getText() == currentScene.second){
            //ml.loadLayoutDescription(currentScene.secondMS);
            //ml.setTransition(currentScene.transition);
            fails++;
            changeButtonBack(view,R.drawable.uncorrect_back_set,500);
        }
        else if (button.getText() == currentScene.third){
            //ml.loadLayoutDescription(currentScene.thirdMS);
            //ml.setTransition(currentScene.transition);
            fails++;
            changeButtonBack(view,R.drawable.uncorrect_back_set,500);
        }
        if(!isInfinity) {
            db.updateLevel(lvlNum, fails);
        }
        Cursor res = db.getUserStat();
        res.moveToFirst();
        db.updateUserStat(1, res.getInt(1), res.getInt(2) + 1, res.getInt(3));
    }
private void changeButtonBack(View view, int backSetId, int duration){
    view.setBackground(ContextCompat.getDrawable(this,backSetId));
    TransitionDrawable transition = (TransitionDrawable) view.getBackground();
    transition.startTransition(duration);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            if (duration == 300) {
                transition.reverseTransition(0);
                changeButtonToNext();
            }
            else
                transition.reverseTransition(duration);
        }
    }, duration);
}
    private void changeButtonToNext() {
        ml.setTransition(R.id.tran0);
        ml.transitionToEnd();
        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.INVISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        if(curSceneNum==scenesCount) {
            btnNxt.setText("Готово!");
            btnNxtLvl.setVisibility(View.VISIBLE);
        }
        btnNxt.setVisibility(View.VISIBLE);
    }

    public void buttonNextClick(View view) {
        btnNxt.setVisibility(View.INVISIBLE);
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        correctBtnPressed(false);
    }

    public void buttonNextLevelClick(View view) {
        correctBtnPressed(true);
    }
}