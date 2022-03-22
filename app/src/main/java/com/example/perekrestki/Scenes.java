package com.example.perekrestki;
public class Scenes {
    public int id;
    public int layout;
    public int transition;
    public int correctMS;
    public int secondMS;
    public int thirdMS;
    public String correct;
    public String second;
    public String third;
    public int idML;

    public Scenes(int _id,int _layout, int _transition,int _correctMS,int _secondMS,int _thirdMS,String _correct,String _second,String _third,int _idML){
        id = _id;
        layout = _layout;
        transition = _transition;
        correctMS = _correctMS;
        secondMS = _secondMS;
        thirdMS = _thirdMS;
        correct = _correct;
        second = _second;
        third = _third;
        idML = _idML;
    }
}
