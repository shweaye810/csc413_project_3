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
    protected int x, y, r, t;
    final int sz;
    protected Paint pnt;

    protected Shape(Context context) {
        super(context);
        pnt = new Paint();
        sz = 50;
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
        t = (int) Math.sqrt(getWidth() * getHeight() / 50);
        x = (int) (Math.random() * getWidth());
        y = (int) (Math.random() * getHeight());
        r = (int) (Math.random() * t + 50);
        if (x + r > getWidth() - sz)
            x = x - r - sz;
        if (y > getWidth() - sz)
            y = y - r - sz;
    }
}
