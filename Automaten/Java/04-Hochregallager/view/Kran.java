package view;

import schule.ngb.zm.Updatable;
import schule.ngb.zm.shapes.Rectangle;
import schule.ngb.zm.shapes.ShapeGroup;

public class Kran extends ShapeGroup implements Updatable {

    public static final class Kommando {

        public static final int LINKS = 10;
        public static final int RECHTS = 20;
        public static final int HOCH = 30;
        public static final int RUNTER = 40;
        public static final int AUFLADEN = 50;
        public static final int ABLADEN = 60;
        public static final int BACK = 70;


        public final int typ;

        public final double wert;

        public final Kiste kiste;

        public Kommando( int typ ) {
            this.typ = typ;
            wert = -1.0;
            kiste = null;
        }

        public Kommando( int pTyp, double pWert) {
            typ = pTyp;
            wert = pWert;
            kiste = null;
        }

        public Kommando( int pTyp, Kiste pKiste) {
            typ = pTyp;
            wert = -1.0;
            kiste = pKiste;
        }

    }

    public static int WIDTH = 40, HEIGHT = 30;

    private Kiste kiste = null;

    private Rectangle mast;

    public Kran( double x, double y ) {
        super(x, y);
        this.setAnchor(SOUTHWEST);

        Rectangle bottom = new Rectangle(0,0, WIDTH, HEIGHT * .2);
        bottom.setAnchor(NORTH);
        bottom.setFillColor(128);
        this.add(bottom);

        mast = new Rectangle(0,0, HEIGHT * .2, HEIGHT);
        mast.setAnchor(SOUTH);
        mast.setFillColor(128);
        this.add(mast);
    }

    public Kiste getKiste() {
        return kiste;
    }

    public void setKiste( Kiste pKiste ) {
        if( pKiste != null ) {
            this.kiste = pKiste;
            kiste.moveTo(this.getX(), this.getY() - mast.getHeight() );
        } else {
//            kiste.moveTo(this.getX(), this.getY() - Lager.LAGERPLATZ_HOEHE / 2);
            this.kiste = null;
        }
    }

    public double getSize() {
        return mast.getHeight() - HEIGHT;
    }

    public void setSize( double pSize ) {
        mast.setHeight(HEIGHT + (int)pSize);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void update( double v ) {
        if( kiste != null ) {
            kiste.moveTo(this.getX(), this.getY() - mast.getHeight() );
        }
    }

}
