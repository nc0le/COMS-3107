import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Color;

public class DrawShapesFromFile {
    public static DrawableShape[] readFile(String filename){

        try {
            Scanner scanner = new Scanner(new File(filename));

            int size = scanner.nextInt();
            scanner.nextLine();
            DrawableShape[] result = new DrawableShape[size];

            for (int i = 0; i < size; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");

                String shapeType = parts[0];
                Double x = Double.parseDouble(parts[1]);
                Double y = Double.parseDouble(parts[2]);
                String color = parts[3];

                Color shapeColor;

                if (color.equals("red")) {
                    shapeColor = Color.RED;
                } else if (color.equals("blue")) {
                    shapeColor = Color.BLUE;
                } else {
                    shapeColor = Color.GREEN;
                }

                if (shapeType.equals("c")) {
                    double radius = Double.parseDouble(parts[4]);
                    result[i] = new DrawableCircle(x, y, shapeColor, radius);

                } else if (shapeType.equals("r")) {
                    double width = Double.parseDouble(parts[4]);
                    double height = Double.parseDouble(parts[5]);
                    result[i] = new DrawableRectangle(x, y, shapeColor, width, height);
                }
                
            }

            scanner.close();
            return result;

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
            return new DrawableShape[0];
        }

    }

    public static void main(String[] args) {
        DrawableShape[] shapes = readFile(args[0]);

        for (DrawableShape shape : shapes) {
            shape.draw();
        }

    }
}