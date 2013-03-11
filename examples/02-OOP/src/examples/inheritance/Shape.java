package examples.inheritance;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author blecherl
 */
public abstract class Shape {

    protected Color line = Color.BLACK;
    protected Color fill = Color.WHITE;

    protected Shape() {
    }

    public Shape(Color line, Color fill) {
        this.line = line;
        this.fill = fill;
    }

    public void draw() {
        System.out.println("Not drawing anything - Shape is an abstract class");
    }

    abstract public boolean isPointInside(Point p);
}
