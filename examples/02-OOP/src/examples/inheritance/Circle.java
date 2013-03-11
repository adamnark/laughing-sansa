package examples.inheritance;

import java.awt.Point;

/**
 *
 * @author blecherl
 */
public class Circle extends Shape {

    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void draw() {
        //if I wanted to call the draw method from Shape, I could use:
        //super.draw();
        System.out.println("Drawing a Circle");
    }

    public boolean isPointInside(Point p) {
        return (p.distance(center) < radius);
    }
}
