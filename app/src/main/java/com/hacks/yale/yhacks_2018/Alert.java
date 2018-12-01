package com.hacks.yale.yhacks_2018;

public class Alert {
    private String mDescription;
    private String mType;

    public Alert(String description, String type) {
        mDescription = description;
        mType = type;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getType() {
        return mType;
    }


}
