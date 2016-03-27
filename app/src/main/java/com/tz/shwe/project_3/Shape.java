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
    protected float x, y, r, a, rad, width, height;
    final int sz;
    protected Paint pnt;

    protected Shape(Context context) {
        super(context);
        pnt = new Paint();
        sz = 100;
        rad = MainActivity.div;
        x = (float) Math.random();
        y = (float) Math.random();
        r = (float) Math.random() / rad;
        width = MainActivity.width - sz;
        height = MainActivity.height - sz;
        set_value();
    }
    protected void setShapeAlpha(float alpha) {
        this.setAlpha(alpha);
    }
    protected float getShapeAlpha() {
        return getAlpha();
    }

    protected void removeShape() {
        this.setVisibility(View.GONE);
    }

    protected abstract ShapeType getShapeType();

    @Override
    protected abstract void onDraw(Canvas cnv);

    protected void set_value() {
        a = (float) Math.sqrt(width * height / 50);
        x = (x * width + sz);
        y = (y * height + sz);
        r = (r * a + MainActivity.min);
    }
}
