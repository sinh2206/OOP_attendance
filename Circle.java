import java.awt.*;

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
