package view;

import schule.ngb.zm.Color;
import schule.ngb.zm.Constants;
import schule.ngb.zm.shapes.Rectangle;
import schule.ngb.zm.shapes.ShapeGroup;
import schule.ngb.zm.shapes.Text;

public class Kiste extends ShapeGroup {

    public static int WIDTH = Lager.LAGERPLATZ_BREITE - 10, HEIGHT = Lager.LAGERPLATZ_HOEHE - 20;

    private String code;

    public Kiste( String pCode, double x, double y) {
        super(x, y);
        this.setAnchor(CENTER);

        code = pCode;

        Rectangle r = new Rectangle(0, 0, WIDTH, HEIGHT);
        r.setFillColor(Constants.randomNiceColor());
        r.setStrokeColor(0);
//        r.setAnchor(CENTER);
        add(r);

        Text t = new Text(WIDTH/2,HEIGHT/2, code);
        t.setAnchor(CENTER);
        add(t);
    }

}
