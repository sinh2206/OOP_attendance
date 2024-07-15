import java.awt.*;

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