package com.hacks.yale.yhacks_2018.ocr;

import android.util.SparseArray;

import com.google.android.gms.vision.text.TextBlock;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OCRParser {
    private Pattern ndcPattern;

    OCRParser() {
        ndcPattern = Pattern.compile("(\\d{5}-\\d{3}-\\d{2})");
    }

    // checks to see if the passed in text contains a valid NDC
    // if so, return the NDC, otherwise return an empty string
    public String returnNDC(String text) {
        String result = "";
        Matcher matcher = ndcPattern.matcher(text);
        if (matcher.find()) {
            result = matcher.group();
            return result;
        }
        return result;
    }

    public ArrayList<String> parse(SparseArray<TextBlock> data) {
        ArrayList<String> result = new ArrayList<>();

        if (data == null) return result;

        for (int i = 0; i < data.size(); ++i) {
            String text = data.valueAt(i).getValue();
            String ndc = returnNDC(text);
            if (!ndc.equals("")) {
                result.add(ndc);
            }
        }
        return result;
    }
}
