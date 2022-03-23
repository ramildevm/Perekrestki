package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
    Button btn1,btn2,btn3,btnNxt;
    MotionLayout ml;
    Scenes currentScene;
    int curSceneNum = 1;
    int scenesCount;
    int lvlNum;
    int fails = 0;
    boolean checkCorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        db = new DBHelper(this);
        flipper = findViewById(R.id.sceneContainer);
        TextView lvlText = findViewById(R.id.lvlTxt);
        lvlNum = getIntent().getIntExtra("Number",0);
        Cursor res = db.getlevel(lvlNum);
        res.moveToFirst();
        fails = res.getInt(1);
        lvlText.setText("Уровень " + lvlNum);
        loadLayoutData();
        loadButtonsData();
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
        btn1.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn1.setText(btnTextList.get(0));
        btn2.setText(btnTextList.get(1));
        btn3.setText(btnTextList.get(2));
    }

    private void loadLayoutData() {
        Cursor lvlscene = db.getlevelscene(lvlNum);
        scenesCount = lvlscene.getCount(); //save levels count
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        int checkSceneNum = 1;
        while(lvlscene.moveToNext()) {
            Cursor scene = db.getscene(lvlscene.getInt(2));
            scene.moveToNext();
            if(curSceneNum == checkSceneNum){ //current scene saving
                currentScene = new Scenes(scene.getInt(0),scene.getInt(1),scene.getInt(2),scene.getInt(3),scene.getInt(4),scene.getInt(5),scene.getString(6),scene.getString(7),scene.getString(8),scene.getInt(9));
            }
            if(curSceneNum==1) {//load
                View child = inflater.inflate(scene.getInt(1), (ViewGroup) findViewById(R.id.sceneContainer), false);
                flipper.addView(child);
            }
            checkSceneNum++;
        }
         ml = ((MotionLayout)findViewById(currentScene.idML));
        flipper.setDisplayedChild(curSceneNum);
    }

    private void correctBtnPressed() {
        if(curSceneNum==scenesCount){
            startActivity(new Intent(this,LevelInfoActivity.class));
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
        else{
            curSceneNum++;
            loadLayoutData();
            loadButtonsData();
        }
    }

    public void goMain(View view) {
        onBackPressed();
    }

    public void buttonClick(View view) throws InterruptedException {
        Button button = (Button) view;
        if(button.getText() == currentScene.correct){
            //ml.loadLayoutDescription(currentScene.correctMS);
            checkCorrect = true;
            Toast msg = Toast.makeText(this,"Верно!",Toast.LENGTH_SHORT);
            msg.setGravity(Gravity.CENTER,0,-130);
            msg.show();
            ml.setTransition(R.id.tran0);
            ml.transitionToEnd();
            changeButtonToNext();
        }
        else if (button.getText() == currentScene.second){
            //ml.loadLayoutDescription(currentScene.secondMS);
            //ml.setTransition(currentScene.transition);
            fails++;
            Toast msg = Toast.makeText(this,"Неправильный ответ!",Toast.LENGTH_SHORT);
            msg.setGravity(Gravity.CENTER,0,-130);
            msg.show();
        }
        else if (button.getText() == currentScene.third){
            //ml.loadLayoutDescription(currentScene.thirdMS);
            //ml.setTransition(currentScene.transition);
            fails++;
            Toast msg = Toast.makeText(this,"Неправильный ответ!",Toast.LENGTH_SHORT);
            msg.setGravity(Gravity.CENTER,0,-130);
            msg.show();
        }
        Cursor res = db.getuserstat();
        res.moveToFirst();
        db.updatelevel(lvlNum,fails);
        db.updateuserstat(1,lvlNum,res.getInt(2)+1,res.getInt(3));
    }

    private void changeButtonToNext() {
        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.INVISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        if(curSceneNum==scenesCount)
            btnNxt.setText("Готово!");
        btnNxt.setVisibility(View.VISIBLE);
    }

    public void buttonNextClick(View view) {
        btnNxt.setVisibility(View.INVISIBLE);
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        correctBtnPressed();
    }
}