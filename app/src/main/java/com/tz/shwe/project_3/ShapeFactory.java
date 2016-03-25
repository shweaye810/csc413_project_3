package com.tz.shwe.project_3;

import android.content.Context;

/**
 * Created by blo on 3/24/16.
 */
public class ShapeFactory {

    public Shape getShape(Context cnxt, String shape) {
        if (shape == null)
            return null;
        if (shape.equalsIgnoreCase("CIRCLE"))
            return new Circle(cnxt);
        else if (shape.equalsIgnoreCase("RECTANGLE"))
            return new Rectangle(cnxt);

        return null;
    }
}
