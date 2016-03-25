package com.tz.shwe.project_3;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.tz.shwe.project_3.ShapeType;
/**
 * Created by blo on 3/24/16.
 */
public abstract class Shape extends View {
    protected Shape(Context context) {
        super(context);
    }
    protected void setShapeAlpha(float alpha) {
        if (alpha > 0.0f)
            alpha -= 0.1f;
    }
    protected float getShapeAlpha() {
        return getAlpha();
    }
    protected void removeShape() {

    }

    protected abstract ShapeType getShapeType();

    @Override
    protected abstract void onDraw(Canvas cnv);
}
