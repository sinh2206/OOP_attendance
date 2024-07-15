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