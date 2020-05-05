package com.example.projet_android;

import java.util.List;

public class RestLeagueOfLegendsResponse {

    private String type;
    private String format;
    private String version;
    private List<Champion> data;

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }

    public String getVersion() {
        return version;
    }

    public List<Champion> getResults() {
        return data;
    }

}
