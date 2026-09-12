import java.awt.Color;

public class DrawableCircle extends DrawableShape { 
    private Double radius;

    public DrawableCircle(Double x, Double y, Color color, Double radius) {
        super(x, y, color);
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(this.shapeColor);
        StdDraw.filledCircle(this.x, this.y, this.radius);
    }

    public static void main(String[] args) {
        DrawableCircle c = new DrawableCircle(0.25, 0.25, Color.RED, 0.1);
        c.draw();
        System.out.println(c.area());
        
        DrawableShape s = new DrawableCircle(0.75, 0.60, Color.BLUE, 0.2);
        s.draw();
        System.out.println(s.area());
    }

}