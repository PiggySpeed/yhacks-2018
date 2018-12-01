package com.hacks.yale.yhacks_2018.ocr;

import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.text.TextBlock;

public class OCRParser {

    OCRParser() {
        // stub
    }

    public boolean parse(SparseArray<TextBlock> data) {
        if (data == null) return false;

        for (int i = 0; i < data.size(); ++i) {
            // TODO: process data here
            TextBlock item = data.valueAt(i);
            Log.i("OCR Result", "data is: " + "[" + i + "] " + item.getValue());
        }
        return false;
    }
}
