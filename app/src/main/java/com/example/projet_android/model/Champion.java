package com.example.projet_android.model;

import java.util.List;

public class Champion {

    private String version;
    private String id;
    private Integer key;
    private String name;
    private String title;
    private String blurb;
    private InfoChampion info;
    private ImageChampion image;
    private List<String> tags;
    private String partype;
    private Stats stats;

    public Champion() {
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public InfoChampion getInfo() {
        return info;
    }

    public ImageChampion getImage() {
        return image;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getPartype() {
        return partype;
    }

    public Stats getStats() {
        return stats;
    }
}
