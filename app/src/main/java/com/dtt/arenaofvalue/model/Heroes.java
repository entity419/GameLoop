package com.dtt.arenaofvalue.model;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Heroes {
    private String name;
    private String linkDetail;
    private String image;
    private String type;

    private HeroDetail heroDetail;

    public Heroes() {
    }

    public Heroes(String name, String linkDetail, HeroDetail heroDetail, String image, String type) {
        this.name = name;
        this.linkDetail = linkDetail;
        this.heroDetail = heroDetail;
        this.image = image;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public HeroDetail getHeroDetail() {
        return heroDetail;
    }

    public void setHeroDetail(HeroDetail heroDetail) {
        this.heroDetail = heroDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkDetail() {
        return linkDetail;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
    }


}
