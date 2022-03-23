package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelActivity extends AppCompatActivity {
    DBHelper db;
    ViewFlipper flipper;
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
        lvlNum = getIntent().getIntExtra("Number",0);
        Cursor res = db.getlevel(lvlNum);
        res.moveToFirst();
        fails = res.getInt(1);
        loadLayoutData();
        loadButtonsData();
    }
    private void loadButtonsData() {
        List<String> btnTextList = new ArrayList<>();
        btnTextList.add(currentScene.correct);
        btnTextList.add(currentScene.second);
        btnTextList.add(currentScene.third);
        Collections.shuffle(btnTextList);
        Button btn1,btn2,btn3;
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
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
         ml.setTransitionListener(new MotionLayout.TransitionListener() {
             @Override
             public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

             }

             @Override
             public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

             }

             @Override
             public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                 if(checkCorrect){
                     correctBtnPressed(); checkCorrect=false;}
             }
             @Override
             public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

             }
         });
    }

    private void correctBtnPressed() {
        if(curSceneNum==scenesCount){
            Cursor res = db.getuserstat();
            res.moveToFirst();
            Cursor levels = db.getmaxlevel();
            levels.moveToFirst();
            if(fails >= levels.getInt(1))
                db.updateuserstat(1,lvlNum,res.getInt(2)+fails,lvlNum);
            else
                db.updateuserstat(1,lvlNum,res.getInt(2)+fails,res.getInt(3));
            onBackPressed();
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
            ml.loadLayoutDescription(currentScene.correctMS);
            checkCorrect = true;
            Toast.makeText(this,"Верно!",Toast.LENGTH_SHORT).show();
            ml.setTransition(currentScene.transition);
        }
        else if (button.getText() == currentScene.second){
            //ml.loadLayoutDescription(currentScene.secondMS);
            //ml.setTransition(currentScene.transition);
            fails++;
            Toast.makeText(this,"Неправильный ответ!",Toast.LENGTH_SHORT).show();
        }
        else if (button.getText() == currentScene.third){
            //ml.loadLayoutDescription(currentScene.thirdMS);
            //ml.setTransition(currentScene.transition);
            fails++;
            Toast.makeText(this,"Неправильный ответ!",Toast.LENGTH_SHORT).show();
        }
        db.updatelevel(lvlNum,fails);
    }
}