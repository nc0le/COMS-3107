import java.awt.Color;

public abstract class DrawableShape {
    protected double x;
    protected double y;
    protected Color shapeColor;

    public DrawableShape(Double x, Double y, Color color) {
        this.x = x;
        this.y = y;
        this.shapeColor = color;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Color getColor() {
        return this.shapeColor;
    }

    public abstract double area();
    public abstract void draw();

}
