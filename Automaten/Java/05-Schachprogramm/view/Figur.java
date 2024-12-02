package view;

import schule.ngb.zm.Color;
import schule.ngb.zm.shapes.Picture;

public class Figur extends Picture {

    private String typ;

    public Figur( String pTyp ) {
        super("images/" + pTyp.toLowerCase() + ".png");
        this.typ = pTyp.toUpperCase();
    }

    public Figur( int x, int y, String pTyp ) {
        super(x, y, "images/" + pTyp.toLowerCase() + ".png");
        this.typ = pTyp.toUpperCase();
    }

    public Figur( double x, double y, String pTyp ) {
        super(x, y, "images/" + pTyp.toLowerCase() + ".png");
        this.typ = pTyp.toUpperCase();
    }

    public String getTyp() {
        return typ;
    }

    public void setColor( Color pColor ) {
        for( int i = 0; i < 6; i++ ) {
            this.tint(pColor);
        }
    }

}
