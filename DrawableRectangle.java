import java.awt.Color;

public class DrawableRectangle extends DrawableShape {
    private Double width;
    private Double height;

    public DrawableRectangle(Double x, Double y, Color color, Double width, Double height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(this.shapeColor);
        Double halfWidth = this.width / 2;
        Double halfHeight = this.height / 2;
        StdDraw.filledRectangle(this.x, this.y, halfWidth, halfHeight);
    }

    public static void main(String[] args) {
        DrawableRectangle c = new DrawableRectangle(0.25, 0.25, Color.RED, 0.2, 0.1);
        c.draw();
        System.out.println(c.area());

        DrawableShape s = new DrawableRectangle(0.75, 0.60, Color.BLUE, 0.2, 0.2);
        s.draw();
        System.out.println(s.area());
    }

}