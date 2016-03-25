package com.tz.shwe.project_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.tz.shwe.project_3.ShapeType;
/**
 * Created by blo on 3/24/16.
 */
public abstract class Shape extends View {
    protected int x, y, r;
    protected Paint pnt;
    protected Shape(Context context) {
        super(context);
    }
    protected void setShapeAlpha(float alpha) {
        alpha = alpha;
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
