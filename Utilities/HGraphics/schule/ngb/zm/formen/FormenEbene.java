package formen;

import java.awt.*;
import java.util.ArrayList;

public class FormenEbene extends Ebene {

    private ArrayList<Form> formen;

    public FormenEbene() {
        super();
        formen = new ArrayList<Form>(32);
    }

    public FormenEbene(int pWidth, int pHeight) {
        super(pWidth, pHeight);
        formen = new ArrayList<Form>(32);
    }

    public void hinzu( Form pForm ) {
        formen.add(pForm);
    }

    public void leeren() {
        Color currentColor = leinwand.getBackground();
        leinwand.setBackground(new Color(0,0,0,0));
        leinwand.clearRect(0, 0, puffer.getWidth(), puffer.getHeight());
        leinwand.setBackground(currentColor);
    }

    public java.util.List<Form> getShapes() {
        return formen;
    }

    @Override
    public void zeichnen(Graphics2D pGraphics) {
        for (Form form : formen) {
            if( form.isSichtbar() ) {
                form.zeichnen(leinwand);
            }
        }

        super.zeichnen(pGraphics);
    }
}
