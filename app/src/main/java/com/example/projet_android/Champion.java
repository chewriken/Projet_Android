package com.example.projet_android;

import android.icu.text.IDNA;

import java.util.List;

public class Champion {

    private String version;
    private String id;
    private Integer key;
    private String name;
    private String title;
    private String blurb;
    /*private List<Info> info;
    private String partype;
    private List<ImageChampion> image;
    private List<String> tags;

    private List<Stats> stats;*/

    public String getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getBlurb() {
        return blurb;
    }

    /*public List<Info> getInfo() {
        return info;
    }

    public List<ImageChampion> getImage() {
        return image;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getPartype() {
        return partype;
    }

    public List<Stats> getStats() {
        return stats;
    }*/
}
