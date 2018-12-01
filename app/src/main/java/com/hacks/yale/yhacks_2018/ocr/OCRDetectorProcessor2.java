package com.hacks.yale.yhacks_2018.ocr;

import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.hacks.yale.yhacks_2018.camera.GraphicOverlay;

import static android.app.Activity.RESULT_OK;

public class OCRDetectorProcessor2 implements Detector.Processor<TextBlock> {
    private GraphicOverlay<OCRGraphic> graphicOverlay;
    private OCRParser parser;
    private OCRCaptureActivity ocrCaptureActivity;

    public OCRDetectorProcessor2(GraphicOverlay<OCRGraphic> ocrGraphicOverlay, OCRCaptureActivity ocrCaptureActivity1) {
        graphicOverlay = ocrGraphicOverlay;
        ocrCaptureActivity = ocrCaptureActivity1;
        parser = new OCRParser();
    }

    /* Receives TextBlocks from the TextRecognizer as they become available */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        boolean isParseSuccess = parser.parse(items);

        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                // TODO: if the item contains a NDC, extract that NDC and highlight it
                Log.d("Processor", "Text detected! " + item.getValue());
                OCRGraphic graphic = new OCRGraphic(graphicOverlay, item);
                graphicOverlay.add(graphic);
            }
        }

        if (isParseSuccess) {
            Intent intent = new Intent();
            ocrCaptureActivity.setResult(RESULT_OK, intent);
            ocrCaptureActivity.finish();
        }
    }

    /* Cleanly get rid of resources when TextRecognizer is disposed of */
    @Override
    public void release() {
        graphicOverlay.clear();
    }
}
