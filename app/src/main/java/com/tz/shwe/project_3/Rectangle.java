package com.tz.shwe.project_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by blo on 3/24/16.
 */
public class Rectangle extends Shape {
    protected Rectangle(Context cnxt) {
        super(cnxt);
    }
    protected void onDraw(Canvas cnv) {
        set_value();
        pnt.setColor(Color.rgb(x % 255, y % 255, r % 255));
        if (x < sz)
            x += sz;
        if (y < sz)
            y += sz;
        cnv.drawRect(x, y, x + r, y + r,pnt);
    }
    protected ShapeType getShapeType() {
        return ShapeType.Rectangle;
    }
}
