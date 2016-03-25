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
        x = (int) (Math.random() * getWidth() + 1);
        y = (int) (Math.random() * getHeight() + 1);
        r = (int) Math.sqrt(getWidth() * getHeight() / 50);
        r = (int) (Math.random() * r + 50);
        pnt = new Paint();
        pnt.setColor(Color.rgb(x % 255, y % 255, r % 255));
        cnv.drawRect(x,y,x+r,y+r,pnt);
    }
    protected ShapeType getShapeType() {
        return null;
    }
}
