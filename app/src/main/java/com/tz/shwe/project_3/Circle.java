package com.tz.shwe.project_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by blo on 3/24/16.
 */
public class Circle extends Shape {
    protected Circle(Context cnxt) {
        super(cnxt);
    }
    protected void onDraw(Canvas cnv) {
        pnt.setColor(Color.rgb((int) (x) % 255,(int) (y) % 255,
                               (int) (r) % 255));
        cnv.drawCircle(x, y, r, pnt);
    }
    protected ShapeType getShapeType() {
        return ShapeType.Circle;
    }
}

