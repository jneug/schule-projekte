import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Ebene extends Konstanten {

    protected BufferedImage puffer;
    protected Graphics2D leinwand;

    protected boolean sichtbar = true;

    public Ebene() {
        this(100, 100);
    }

    public Ebene(int pWidth, int pHeight ) {
        createCanvas(pWidth, pHeight);
    }

    public int getWidth() {
        return puffer.getWidth();
    }

    public int getHeight() {
        return puffer.getHeight();
    }

    public void setSize( int pWidth, int pHeight ) {
        if( puffer != null ) {
            recreateCanvas(pWidth, pHeight);
        } else {
            createCanvas(pWidth, pHeight);
        }
    }

    private void createCanvas( int pWidth, int pHeight ) {
        puffer = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_ARGB);
        leinwand = puffer.createGraphics();

        // add antialiasing
        RenderingHints hints = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        hints.put(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY
        );
        leinwand.addRenderingHints(hints);
    }

    private void recreateCanvas( int pWidth, int pHeight ) {
        BufferedImage oldCanvas = puffer;
        createCanvas(pWidth, pHeight);
        leinwand.drawImage(oldCanvas, 0, 0, null);
    }

    public abstract void leeren();

    public void zeichnen(Graphics2D pGraphics ) {
        if(sichtbar) {
            pGraphics.drawImage(puffer, 0, 0, null);
        }
    }

    public void aktualisieren() {}

    public boolean isSichtbar() {
        return sichtbar;
    }

    public void verstecken() {
        sichtbar = false;
    }

    public void anzeigen() {
        sichtbar = true;
    }

    public void umschalten() {
        sichtbar = !sichtbar;
    }

}
