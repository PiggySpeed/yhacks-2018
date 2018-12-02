package com.hacks.yale.yhacks_2018;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class Drug implements ParentListItem {
    private String mName;
    private String mDosage;
    private String mNDC;
    private String mQTY;
    private String mRoute;
    private String mFrequency;
    private String mTag;
    private String mShortDescription;
    private String mLongDescription;

    private List<DrugDetailed> mChildItemList;

    public Drug(String name, String dosage, String NDC, String QTY, String route, String frequency, String tag, String short_description, String long_description) {
        mName = name;
        mDosage = dosage;
        mNDC = NDC;
        mQTY = QTY;
        mRoute = route;
        mFrequency = frequency;
        mTag = tag;
        mShortDescription = short_description;
        mLongDescription = long_description;
    }

    public String getName() {
        return mName;
    }
    public String getDosage() {return mDosage;}
    public String getNDC() {return mNDC; }
    public String getQTY() {return mQTY; }
    public String getRoute() {return mRoute; }
    public String getFrequency() {return mFrequency; }
    public String getTag() {return mTag; }
    public String getShortDescription() {return mShortDescription; }
    public String getLongDescription() {return mLongDescription; }

    @Override
    public List<DrugDetailed> getChildItemList() {
        return mChildItemList;
    }

    public void setmChildItemList(List<DrugDetailed> list) {
        mChildItemList = list;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
