package com.dtt.arenaofvalue.model;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Skill {
    private String img;
    private String tileSkill;
    private String mana;
    private String countDown;
    private String detail;
    private String imgBg;

    public Skill() {
    }

    public Skill(String img, String tileSkill, String mana, String countDown, String detail) {
        this.img = img;
        this.tileSkill = tileSkill;
        this.mana = mana;
        this.countDown = countDown;
        this.detail = detail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTileSkill() {
        return tileSkill;
    }

    public void setTileSkill(String tileSkill) {
        this.tileSkill = tileSkill;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public String getCountDown() {
        return countDown;
    }

    public void setCountDown(String countDown) {
        this.countDown = countDown;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }



}
