package com.example.sofie.transenlog;

import java.util.Date;

/**
 * Created by sofie on 14-03-2018.
 */


public class Shock {

    int id;
    String time;
    long shockStrenght;


    // constructors


    public Shock(int id, String string, String cursorString) {
    }

    public Shock(){

    }

    public Shock(int id, String time, long shockStrenght) {
        this.id = id;
        this.time = time;
        this.shockStrenght = shockStrenght;
    }

    public Shock(String time, long shock) {
        this.time = time;
        this.shockStrenght = shockStrenght;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setShockStrenght(long shockStrenght) {this.shockStrenght = shockStrenght;}



    // getters
    public int getId() {
        return this.id;
    }

    public String getTime() {
        return this.time;
    }

    public long getShock() {
        return this.shockStrenght;
    }
}

