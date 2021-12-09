import java.awt.*;
import java.util.ArrayList;

public final class Shape2DEbene extends Ebene {

    protected Color strokeColor;

    protected Color fillColor;

    protected double strokeWeight;

    protected int konturArt = DURCHGEZOGEN;

    private ArrayList<java.awt.Shape> shapes;

    private boolean sofortZeichnen = false;

    public Shape2DEbene() {
        super();
        shapes = new ArrayList<java.awt.Shape>(32);
    }

    public Shape2DEbene(boolean pSofortZeichnen) {
        super();
        shapes = new ArrayList<java.awt.Shape>(32);
        sofortZeichnen = pSofortZeichnen;
    }

    public Shape2DEbene(int pWidth, int pHeight) {
        super(pWidth, pHeight);
        shapes = new ArrayList<java.awt.Shape>(32);
    }

    public Shape2DEbene(int pWidth, int pHeight, boolean pSofortZeichnen ) {
        super(pWidth, pHeight);
        shapes = new ArrayList<java.awt.Shape>(32);
        sofortZeichnen = pSofortZeichnen;
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
        leinwand.setColor(pColor);
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
        leinwand.setColor(pColor);
    }

    public void setStrokeWeight( double pWeight ) {
        strokeWeight = pWeight;
        leinwand.setStroke(createStroke());
    }

    protected Stroke createStroke() {
        switch(konturArt) {
            case GEPUNKTET:
                return new BasicStroke(
                    (float) strokeWeight,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND,
                    10.0f, new float[]{1.0f, 5.0f}, 0.0f);
            case GESTRICHELT:
                return new BasicStroke(
                    (float) strokeWeight,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND,
                    10.0f, new float[]{5.0f}, 0.0f);
            default:
                return new BasicStroke(
                    (float) strokeWeight,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND);
        }
    }

    public int getKonturArt() {
        return konturArt;
    }

    public void setKonturArt(int konturArt) {
        this.konturArt = konturArt;
    }

    public void leeren() {
        leinwand.setBackground(new Color(0, 0, 0, 0));
        leinwand.clearRect(0, 0, puffer.getWidth(), puffer.getHeight());
    }

    public java.util.List<java.awt.Shape> getShapes() {
        return shapes;
    }

    public void add(java.awt.Shape s) {
        shapes.add(s);

        if( sofortZeichnen ) {
            leinwand.setColor(fillColor);
            leinwand.fill(s);

            leinwand.setColor(strokeColor);
            leinwand.draw(s);
        }
    }

    @Override
    public void zeichnen(Graphics2D pGraphics) {
        if( !sofortZeichnen ) {
            for (Shape shape : shapes) {
                leinwand.setColor(fillColor);
                leinwand.fill(shape);

                leinwand.setColor(strokeColor);
                leinwand.draw(shape);
            }
        }

        super.zeichnen(pGraphics);
    }

}
