package com.tz.shwe.project_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.tz.shwe.project_3.ShapeType;
/**
 * Created by blo on 3/24/16.
 */
public abstract class Shape extends View {
    protected float x, y, r, a, rad, width, height, o_y, v;
    final int sz;
    protected Paint pnt;

    protected Shape(Context context) {
        super(context);
        pnt = new Paint();
        sz = 50;
        rad = MainActivity.div;
        x = (float) Math.random();
        y = (float) Math.random();
        r = (float) Math.random() / rad;
        width = MainActivity.width - sz * 2;
        height = MainActivity.height - sz * 2;
        set_value();
        v = 10;
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
        pnt.setColor(Color.rgb((int) (x) % 255, (int) (y) % 255, (int) (r) % 255));
        o_y = y;
    }
    protected void add_gravity() {
        if (y < height) {
            y += v;
            v += 10;
            if (y + r > height)
                y = height - (r * 9 / 10);
            invalidate();
        }
    }
    protected void remove_gravity() {
        if (y > o_y) {
            y -= v;
            v -= 10;
            invalidate();
        }
    }
}
