package com.dtt.arenaofvalue.model;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Gem {
    private String imgR;
    private String imgP;
    private String imgG;
    private String laneR;
    private String laneP;
    private String laneG;

    public Gem(String imgR, String imgP, String imgG, String laneR, String laneP, String laneG) {
        this.imgR = imgR;
        this.imgP = imgP;
        this.imgG = imgG;
        this.laneR = laneR;
        this.laneP = laneP;
        this.laneG = laneG;
    }

    public Gem() {
    }

    public String getImgR() {
        return imgR;
    }

    public void setImgR(String imgR) {
        this.imgR = imgR;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }

    public String getImgG() {
        return imgG;
    }

    public void setImgG(String imgG) {
        this.imgG = imgG;
    }

    public String getLaneR() {
        return laneR;
    }

    public void setLaneR(String laneR) {
        this.laneR = laneR;
    }

    public String getLaneP() {
        return laneP;
    }

    public void setLaneP(String laneP) {
        this.laneP = laneP;
    }

    public String getLaneG() {
        return laneG;
    }

    public void setLaneG(String laneG) {
        this.laneG = laneG;
    }


}
