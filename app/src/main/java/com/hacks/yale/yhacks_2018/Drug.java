package com.hacks.yale.yhacks_2018;

public class Drug {
    private String mName;
    private int mDosage;
    private String mNDC;

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
}
