package com.hacks.yale.yhacks_2018;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class Drug implements ParentListItem {
    private String mName;
    private int mDosage;
    private String mNDC;
    private List<DrugDetailed> mChildItemList;

    public Drug(String name, int dosage, String NDC) {
        mName = name;
        mDosage = dosage;
        mNDC = NDC;
    }

    public String getName() {
        return mName;
    }
    public String getDosage() {return Integer.toString(mDosage);}
    public String getNDC() {return mNDC; }

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
