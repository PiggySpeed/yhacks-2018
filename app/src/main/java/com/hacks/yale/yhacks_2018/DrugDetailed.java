package com.hacks.yale.yhacks_2018;

import android.widget.TextView;

public class DrugDetailed {
    private final String mTitle;
    public String tvTag;
    public String tvShortDescription;
    public String tvLongDescription;

    public DrugDetailed(String title, String tag, String shortS, String longS) {
        tvTag = tag;
        tvShortDescription = shortS;
        tvLongDescription = longS;
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getT() { return tvTag; }
    public String getS() { return tvShortDescription; }
    public String getL() { return tvLongDescription; }
}
