package com.aveteam.avewallpaper.Model;

/**
 * Created by Lorenzo on 09/08/2016.
 */
public class Image{
    String string;
    int state;
    public Image(String s){
        this.string=s;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
