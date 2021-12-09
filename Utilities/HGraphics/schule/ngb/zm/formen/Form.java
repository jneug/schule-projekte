package formen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Form extends Fuellform {

    public static final byte CENTER     = 0;
    public static final byte NORTH      = 1 << 0;
    public static final byte EAST       = 1 << 2;
    public static final byte SOUTH      = 1 << 3;
    public static final byte WEST       = 1 << 4;

    public static final byte NORTHEAST  = NORTH | EAST;
    public static final byte SOUTHEAST  = SOUTH | EAST;
    public static final byte NORTHWEST  = NORTH | WEST;
    public static final byte SOUTHWEST  = SOUTH | WEST;



    public double x;

    public double y;

    public double drehwinkel = 0.0;

    public double skalierung = 1.0;

    protected boolean sichtbar = true;

    protected Point2D.Double anker = new Point2D.Double();


    public Form() {
        this(0.0,0.0);
    }

    public Form(double pX, double pY) {
        x = pX;
        y = pY;
    }

    public boolean isSichtbar() {
        return sichtbar;
    }

    public void verstecken() {
        sichtbar = false;
    }

    public void zeigen() {
        sichtbar = true;
    }

    public void umschalten() {
        sichtbar = !sichtbar;
    }

    public Point2D.Double getAnkerpunkt() {
        return anker;
    }

    public void setAnkerpunkt( byte pAnker ) {
        Rectangle2D bounds = getShape().getBounds2D();
        if( pAnker == CENTER ) {
            anker.x = bounds.getHeight() / 2.0;
            anker.y = bounds.getWidth() / 2.0;
        }
        if( (pAnker & SOUTH)  == SOUTH ) {
            anker.y = bounds.getHeight();
        }
        if( (pAnker & EAST)  == EAST ) {
            anker.x = bounds.getWidth();
        }
    }

    public void setAnkerpunkt( Point2D.Double pAnker ) {
        anker.x = pAnker.x;
        anker.y = pAnker.y;
    }

    public void kopiere( Form pForm ) {
        verschiebeNach(pForm);
        setAnkerpunkt(pForm.getAnkerpunkt());
        setFuellFarbe(pForm.getFuellFarbe());
        setKonturFarbe(pForm.getKonturFarbe());
        setKonturDicke(pForm.getKonturDicke());
        setKonturArt(pForm.getKonturArt());
        sichtbar = pForm.isSichtbar();
        drehwinkel = pForm.drehwinkel;
        skalierung = pForm.skalierung;
    }

    public Rechteck getBegrenzung() {
        return new Rechteck(this);
    }

    public void verschieben( double dx, double dy ) {
        x += dx;
        y += dy;
    }

    public void verschiebeNach( double pX, double pY ) {
        x = pX;
        y = pY;
    }

    public void verschiebeNach( Form pForm ) {
        x = pForm.x;
        y = pForm.y;
    }
    public void skalieren( double pFaktor ) {
        skalierung = pFaktor;
    }

    public void skalierenUm( double pFaktor ) {
        skalierung *= pFaktor;
    }

    public void drehen( double pWinkel ) {
        drehwinkel += pWinkel % 360;
    }

    public void drehenNach( double pWinkel ) {
        drehwinkel = pWinkel % 360;
    }

    /*public void scheren( double dx, double dy ) {
        verzerrung.shear(dx, dy);
    }*/

    public AffineTransform getVerzerrung() {
        AffineTransform verzerrung = new AffineTransform();
        verzerrung.translate(x, y);
        verzerrung.scale(skalierung, skalierung);
        verzerrung.rotate(Math.toRadians(drehwinkel));
        verzerrung.translate(-anker.x, -anker.y);
        return verzerrung;
    }

    public void zeichnen( Graphics2D graphics ) {
        if( !sichtbar ) {
            return;
        }

        Shape shape = getShape();

        Color currentColor = graphics.getColor();
        if (fuellFarbe != null && fuellFarbe.getAlpha() > 0.0) {
            graphics.setColor(fuellFarbe);
            graphics.fill(shape);
        }
        if (konturFarbe != null && konturFarbe.getAlpha() > 0.0
            && konturDicke > 0.0) {
            graphics.setColor(konturFarbe);
            graphics.setStroke(createStroke());
            graphics.draw(shape);
        }
        graphics.setColor(currentColor);
    }

    public abstract Shape getShape();

}
