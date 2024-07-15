import java.awt.*;

abstract class Shape {
    protected Color color;
    protected boolean filled;
    protected int velocityX;
    protected int velocityY;

    public Shape(Color color, boolean filled, int velocityX, int velocityY) {
        this.color = color;
        this.filled = filled;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public abstract void draw(Graphics g);
    public abstract void move();
    public abstract boolean contains(int x, int y);
}

class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, Color color, boolean filled, int velocityX, int velocityY) {
        super(color, filled, velocityX, velocityY);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillOval(x, y, radius * 2, radius * 2);
        } else {
            g.drawOval(x, y, radius * 2, radius * 2);
        }
    }

    @Override
    public void move() {
        x += velocityX;
        y += velocityY;
    }

    @Override
    public boolean contains(int x, int y) {
        return Math.pow((x - (this.x + radius)), 2) + Math.pow((y - (this.y + radius)), 2) <= Math.pow(radius, 2);
    }
}

class Rectangle extends Shape {
    private int x, y, width, height;

    public Rectangle(int x, int y, int width, int height, Color color, boolean filled, int velocityX, int velocityY) {
        super(color, filled, velocityX, velocityY);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }

    @Override
    public void move() {
        x += velocityX;
        y += velocityY;
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }
}

class Triangle extends Shape {
    private int[] xPoints, yPoints;

    public Triangle(int[] xPoints, int[] yPoints, Color color, boolean filled, int velocityX, int velocityY) {
        super(color, filled, velocityX, velocityY);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public void move() {
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] += velocityX;
            yPoints[i] += velocityY;
        }
    }

    @Override
    public boolean contains(int x, int y) {
        Polygon polygon = new Polygon(xPoints, yPoints, 3);
        return polygon.contains(x, y);
    }
}
