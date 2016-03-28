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
        cnv.drawRect(x, y, x + r, y + r,pnt);
    }
    protected ShapeType getShapeType() {
        return ShapeType.Rectangle;
    }
}
