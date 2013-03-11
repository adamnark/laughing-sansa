package examples.inheritance;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author blecherl
 */
public class ShapesMain {

    public static void main(String[] args) {

        Shape[] shapes = {
            new Circle(new Point(5, 5), 6), 
            new Square(new Point (0, 0), 10),
            new Shape(Color.RED, Color.WHITE){

                @Override
                public boolean isPointInside(Point p) {
                    return false;
                }

            @Override
            public void draw() {
                System.out.println("Abstract Anonnymous Shape is drawn");
            }
                
                
            }
        };
        drawAllShapes(shapes);
    }

    public static void drawAllShapes(Shape[] shapes) {
        if (shapes == null) {
            return;
        }

        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}
