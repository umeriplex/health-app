package com.example.fitraho.activities;

public class GifModel {

    int gif;
    String desc;

    public GifModel(int gif, String desc) {
        this.gif = gif;
        this.desc = desc;
    }

    public int getGif() {
        return gif;
    }

    public void setGif(int gif) {
        this.gif = gif;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
