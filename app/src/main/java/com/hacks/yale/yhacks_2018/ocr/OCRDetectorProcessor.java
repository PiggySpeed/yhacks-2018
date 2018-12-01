package com.hacks.yale.yhacks_2018.ocr;

import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.hacks.yale.yhacks_2018.camera.GraphicOverlay;

public class OCRDetectorProcessor implements Detector.Processor<TextBlock> {
    private GraphicOverlay<OCRGraphic> graphicOverlay;
    private OCRParser parser;

    public OCRDetectorProcessor(GraphicOverlay<OCRGraphic> ocrGraphicOverlay) {
        graphicOverlay = ocrGraphicOverlay;
        parser = new OCRParser();
    }

    // TODO: don't know why we do receiveDetections in OCRDetectorProcessor2 as well
    /* Receives TextBlocks from the TextRecognizer as they become available */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                OCRGraphic graphic = new OCRGraphic(graphicOverlay, item, false);
                graphicOverlay.add(graphic);
            }
        }
    }

    /* Cleanly get rid of resources when TextRecognizer is disposed of */
    @Override
    public void release() {
        graphicOverlay.clear();
    }
}
