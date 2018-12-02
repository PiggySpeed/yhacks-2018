package com.hacks.yale.yhacks_2018.ocr;

import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacks.yale.yhacks_2018.DrugActivity;
import com.hacks.yale.yhacks_2018.MainActivity;
import com.hacks.yale.yhacks_2018.camera.GraphicOverlay;
import com.hacks.yale.yhacks_2018.firebase.Sync;
import com.hacks.yale.yhacks_2018.firebase.Upload;

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
    private int numDrugsSoFar;
    private Timer timer;
    private int timerDuration;
    private int timerDurationInMilliSeconds;
    private int startingTimerDurationInMilliSeconds;
    private TextView drugCounter;
    private Upload uploadManager;

//    private ProgressBar progressBar;

    public OCRDetectorProcessor2(GraphicOverlay<OCRGraphic> ocrGraphicOverlay, OCRCaptureActivity ocrCaptureActivity1) {
        graphicOverlay = ocrGraphicOverlay;
        ocrCaptureActivity = ocrCaptureActivity1;
        parser = new OCRParser();
        drugsFoundSoFar = new HashMap<>();
        numDrugsSoFar = 0;
        timer = new Timer();
        timerDuration = 15;
        timerDurationInMilliSeconds = 15000;
        startingTimerDurationInMilliSeconds = 15000;
        uploadManager = new Upload();

//        drugCounter = ocrCaptureActivity1.findViewById(R.id.drug_count);
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

        // update current drug count
        int numDrugs = drugsFoundSoFar.size();
        if (numDrugs != numDrugsSoFar) {
//            Log.i("------ numDrugs: ", numDrugs + "");
//            ocrCaptureActivity.incrementDrugCount(Integer.toString(numDrugs));
//            drugCounter.setText("awef");
        }

        // if the item is an NDC seen before, highlight it
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            String text = item.getValue().trim();

            if (drugsFoundSoFar.containsKey(text)) {
                graphic = new OCRGraphic(graphicOverlay, item, true);
            } else {
                graphic = new OCRGraphic(graphicOverlay, item, false);
            }

            graphicOverlay.add(graphic);
        }

        // if counter is 0, then stop activity and sent to API
        timer.schedule(new TimerTask() {
            public void run() {
            if (setInterval() == 1) {
                // TODO: pass data through here and trigger opening of drug list
                ArrayList<String> ndcCodes = new ArrayList<>();

                for (String key : drugsFoundSoFar.keySet()) {
                    String upToNCharacters = key.substring(0, Math.min(key.length(), 9));
                    ndcCodes.add(upToNCharacters);
                }

                // first Activity

//                Intent intent = new Intent(this, Page.class);
//                intent.putExtra("arg", getText()); // getText() SHOULD NOT be static!!!
//                startActivity(intent);

                Intent intent = new Intent(ocrCaptureActivity, DrugActivity.class);
                intent.putExtra("ndcCodes", ndcCodes);
                ocrCaptureActivity.setResult(RESULT_OK, intent);
                ocrCaptureActivity.startActivity(intent);
                ocrCaptureActivity.finish();
                }
            }
        }, 1000);
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
