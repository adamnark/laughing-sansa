package examples.inheritance;

import java.awt.Point;

/**
 *
 * @author blecherl
 */
public class Square extends Shape {

    private Point topLeft;
    private int edge;

    public Square(Point topLeft, int edge) {
        this.topLeft = topLeft;
        this.edge = edge;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }

    @Override
    public boolean isPointInside(Point p) {
        return  p.x >= topLeft.x && p.x <= topLeft.x+edge &&
                p.y >= topLeft.y && p.y <= topLeft.y+edge;
    }
}
