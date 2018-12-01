package com.hacks.yale.yhacks_2018.ocr;

import android.content.Intent;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.hacks.yale.yhacks_2018.camera.GraphicOverlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;

public class OCRDetectorProcessor2 implements Detector.Processor<TextBlock> {
    private GraphicOverlay<OCRGraphic> graphicOverlay;
    private OCRParser parser;
    private OCRCaptureActivity ocrCaptureActivity;
    private HashMap<String, Integer> drugsFoundSoFar;
    private Timer timer;
    private int timerDuration;
    private int timerDurationInMilliSeconds;
    private int startingTimerDurationInMilliSeconds;
//    private ProgressBar progressBar;

    public OCRDetectorProcessor2(GraphicOverlay<OCRGraphic> ocrGraphicOverlay, OCRCaptureActivity ocrCaptureActivity1) {
        graphicOverlay = ocrGraphicOverlay;
        ocrCaptureActivity = ocrCaptureActivity1;
        parser = new OCRParser();
        drugsFoundSoFar = new HashMap<>();
        timer = new Timer();
        timerDuration = 20;
        timerDurationInMilliSeconds = 20000;
        startingTimerDurationInMilliSeconds = 20000;

//        progressBar = ocrCaptureActivity1.findViewById(R.id.determinateBar);
//        setProgressBarValues();
    }

    /* Receives TextBlocks from the TextRecognizer as they become available */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        OCRGraphic graphic;
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        ArrayList<String> ndcCodes = parser.parse(items);

        // add each found NDC to drugsFoundSoFar
        for (int i = 0; i < ndcCodes.size(); ++i) {
            String ndcCode = ndcCodes.get(i);
            int newCount = 1;
            if (drugsFoundSoFar.containsKey(ndcCode)) {
                newCount = drugsFoundSoFar.get(ndcCodes.get(i)) + 1;
            }

            drugsFoundSoFar.put(ndcCodes.get(i), newCount);
        }

        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            String text = item.getValue().trim();

            // If the item contains a NDC seen before, highlight its box
            if (drugsFoundSoFar.containsKey(text)) {
                graphic = new OCRGraphic(graphicOverlay, item, true);
            } else {
                graphic = new OCRGraphic(graphicOverlay, item, false);
            }

            graphicOverlay.add(graphic);
        }

        // if counter is 0, then stop activity and sent to API
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (setInterval() < 1) {
                    Intent intent = new Intent();
                    // TODO: pass data through here and trigger opening of drug list
                    ocrCaptureActivity.setResult(RESULT_OK, intent);
                    ocrCaptureActivity.finish();
//                    setProgressBarValues();
                }
//                progressBar.setProgress((int) ((startingTimerDurationInMilliSeconds - timerDurationInMilliSeconds) / 50));
            }
        }, 1000, 1000);
    }

    /* Cleanly get rid of resources when TextRecognizer is disposed of */
    @Override
    public void release() {
        graphicOverlay.clear();
    }

    public int setInterval() {
        timerDuration = timerDuration - 1;
        timerDurationInMilliSeconds = timerDurationInMilliSeconds - 1000;
        return timerDuration;
    }

//    private void setProgressBarValues() {
//        progressBar.setMax((int) timerDurationInMilliSeconds / 50);
//        progressBar.setProgress((int) (startingTimerDurationInMilliSeconds - timerDurationInMilliSeconds) / 1000);
//    }
}
