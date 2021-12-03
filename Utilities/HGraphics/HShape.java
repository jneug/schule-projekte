import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class HShape {

    private double x;
    private double y;

    public HShape() {
        x = 0.0;
        y = 0.0;
    }

    public HShape(double pX, double pY) {
        x = pX;
        y = pY;
    }

    public double getX() {
        return x;
    }

    public void setX(double pX) {
        x = pX;
    }

    public double getY() {
        return y;
    }

    public void setY(double pY) {
        y = pY;
    }

    public abstract void draw( Graphics2D pGraphics );

}
