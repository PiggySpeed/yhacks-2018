package com.hacks.yale.yhacks_2018.ocr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.android.gms.vision.text.TextBlock;
import com.hacks.yale.yhacks_2018.camera.GraphicOverlay;

public class OCRGraphic extends GraphicOverlay.Graphic {

    private int id;

    private static final int TEXT_COLOR = Color.GREEN;

    private static Paint rectPaint;
    private static Paint textPaint;
    private final TextBlock text;

    OCRGraphic(GraphicOverlay overlay, TextBlock text) {
        super(overlay);

        this.text = text;

        if (rectPaint == null) {
            rectPaint = new Paint();
            rectPaint.setColor(TEXT_COLOR);
            rectPaint.setStyle(Paint.Style.STROKE);
            rectPaint.setStrokeWidth(4.0f);
        }

        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setColor(TEXT_COLOR);
            textPaint.setTextSize(52.0f);
        }
        // Redraw the overlay, as this graphic has been added.
        postInvalidate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TextBlock getTextBlock() {
        return text;
    }

    /**
     * Checks whether a point is within the bounding box of this graphic.
     * The provided point should be relative to this graphic's containing overlay.
     * @param x An x parameter in the relative context of the canvas.
     * @param y A y parameter in the relative context of the canvas.
     * @return True if the provided point is contained within this graphic's bounding box.
     */
    public boolean contains(float x, float y) {
        // TODO: Check if this graphic's text contains this point.
        return false;
    }

    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        // TODO: Draw the text onto the canvas.
        if (text == null) {
            return;
        }

        // Draws the bounding box around the TextBlock.
        RectF rect = new RectF(text.getBoundingBox());
        rect = translateRect(rect);
        canvas.drawRect(rect, rectPaint);

        // Render the text at the bottom of the box.
        canvas.drawText(text.getValue(), rect.left, rect.bottom, textPaint);
    }
}
