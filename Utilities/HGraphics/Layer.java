import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Layer {

    protected BufferedImage canvas;
    protected Graphics2D graphics;

    protected Color strokeColor;

    protected Color fillColor;

    protected double strokeWeight;

    protected boolean isVisible = true;

    public Layer() {
        this(100, 100);
    }

    public Layer( int pWidth, int pHeight ) {
        createCanvas(pWidth, pHeight);

        strokeColor = Color.BLACK;
        fillColor = Color.WHITE;
        strokeWeight = 1.0;
    }

    public void setSize( int pWidth, int pHeight ) {
        if( canvas != null ) {
            recreateCanvas(pWidth, pHeight);
        } else {
            createCanvas(pWidth, pHeight);
        }
    }

    private void createCanvas( int pWidth, int pHeight ) {
        canvas = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_ARGB);
        graphics = canvas.createGraphics();

        // add antialiasing
        RenderingHints hints = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        hints.put(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY
        );
        graphics.addRenderingHints(hints);
    }

    private void recreateCanvas( int pWidth, int pHeight ) {
        BufferedImage oldCanvas = canvas;
        createCanvas(pWidth, pHeight);
        graphics.drawImage(oldCanvas, 0, 0, null);
    }

    public abstract void clear();

    public void draw( Graphics2D pGraphics ) {
        if( isVisible ) {
            pGraphics.drawImage(canvas, 0, 0, null);
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void hide() {
        isVisible = false;
    }

    public void show() {
        isVisible = true;
    }

    public void toggle() {
        isVisible = !isVisible;
    }

    public Color getColor() {
        return fillColor;
    }

    public void noFill() {
        fillColor = null;
    }

    public void setColor(int gray) {
        setColor(gray, gray, gray, 255);
    }

    public void setColor(int gray, int alpha) {
        setColor(gray, gray, gray, alpha);
    }

    public void setColor(int red, int green, int blue) {
        setColor(red, green, blue, 255);
    }

    public void setColor(int red, int green, int blue, int alpha ) {
        if (red   < 0 || red   >= 256) throw new IllegalArgumentException("red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("green must be between 0 and 255");
        if (blue  < 0 || blue  >= 256) throw new IllegalArgumentException("blue must be between 0 and 255");
        if (alpha  < 0 || alpha  >= 256) throw new IllegalArgumentException("alpha must be between 0 and 255");

        setColor(new Color(red, green, blue, alpha));
    }

    public void setColor(Color pColor) {
        fillColor = pColor;
        graphics.setColor(pColor);
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void noStroke() {
        strokeColor = null;
    }

    public void setStrokeColor(int gray) {
        setStrokeColor(gray, gray, gray, 255);
    }

    public void setStrokeColor(int gray, int alpha) {
        setStrokeColor(gray, gray, gray, alpha);
    }

    public void setStrokeColor(int red, int green, int blue) {
        if (red   < 0 || red   >= 256) throw new IllegalArgumentException("red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("green must be between 0 and 255");
        if (blue  < 0 || blue  >= 256) throw new IllegalArgumentException("blue must be between 0 and 255");
        setStrokeColor(red, green, blue, 255);
    }

    public void setStrokeColor(int red, int green, int blue, int alpha ) {
        if (red   < 0 || red   >= 256) throw new IllegalArgumentException("red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("green must be between 0 and 255");
        if (blue  < 0 || blue  >= 256) throw new IllegalArgumentException("blue must be between 0 and 255");
        if (alpha  < 0 || alpha  >= 256) throw new IllegalArgumentException("alpha must be between 0 and 255");

        setStrokeColor(new Color(red, green, blue, alpha));
    }

    public void setStrokeColor(Color pColor) {
        strokeColor = pColor;
        graphics.setColor(pColor);
    }

    public void setStrokeWeight( double pWeight ) {
        strokeWeight = pWeight;
        graphics.setStroke(new BasicStroke((float) pWeight, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

}
