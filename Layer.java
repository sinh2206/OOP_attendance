import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class Layer extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Random random = new Random();

    public Layer() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'c':
                        addRandomCircle();
                        break;
                    case 'r':
                        addRandomRectangle();
                        break;
                    case 't':
                        addRandomTriangle();
                        break;
                    case 'd':
                        removeCircles();
                        break;
                    case 'x':
                        removeDuplicates();
                        break;
                }
            }
        });

        Timer timer = new Timer(50, e -> {
            for (Shape shape : shapes) {
                shape.move();
                checkCollision(shape);
            }
            repaint();
        });
        timer.start();
    }

    private void addRandomCircle() {
        int x = random.nextInt(700);
        int y = random.nextInt(500);
        int radius = random.nextInt(50) + 10;
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        shapes.add(new Circle(x, y, radius, color, true, random.nextInt(5) + 1, random.nextInt(5) + 1));
    }

    private void addRandomRectangle() {
        int x = random.nextInt(700);
        int y = random.nextInt(500);
        int width = random.nextInt(50) + 20;
        int height = random.nextInt(50) + 20;
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        shapes.add(new Rectangle(x, y, width, height, color, true, random.nextInt(5) + 1, random.nextInt(5) + 1));
    }

    private void addRandomTriangle() {
        int[] xPoints = {random.nextInt(700), random.nextInt(700), random.nextInt(700)};
        int[] yPoints = {random.nextInt(500), random.nextInt(500), random.nextInt(500)};
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        shapes.add(new Triangle(xPoints, yPoints, color, true, random.nextInt(5) + 1, random.nextInt(5) + 1));
    }

    private void checkCollision(Shape shape) {
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            if (circle.x < 0 || circle.x + circle.radius * 2 > getWidth()) {
                circle.velocityX = -circle.velocityX;
            }
            if (circle.y < 0 || circle.y + circle.radius * 2 > getHeight()) {
                circle.velocityY = -circle.velocityY;
            }
        } else if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            if (rect.x < 0 || rect.x + rect.width > getWidth()) {
                rect.velocityX = -rect.velocityX;
            }
            if (rect.y < 0 || rect.y + rect.height > getHeight()) {
                rect.velocityY = -rect.velocityY;
            }
        } else if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            for (int i = 0; i < triangle.xPoints.length; i++) {
                if (triangle.xPoints[i] < 0 || triangle.xPoints[i] > getWidth() || triangle.yPoints[i] < 0 || triangle.yPoints[i] > getHeight()) {
                    triangle.velocityX = -triangle.velocityX;
                    triangle.velocityY = -triangle.velocityY;
                    break;
                }
            }
        }
    }

    private void removeCircles() {
        shapes.removeIf(shape -> shape instanceof Circle);
    }

    private void removeDuplicates() {
        ArrayList<Shape> uniqueShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            boolean isDuplicate = false;
            for (Shape uniqueShape : uniqueShapes) {
                if (shape.getClass() == uniqueShape.getClass() && shape.equals(uniqueShape)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueShapes.add(shape);
            }
        }
        shapes = uniqueShapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
}
