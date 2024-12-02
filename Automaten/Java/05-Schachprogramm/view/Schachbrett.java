package view;

import schule.ngb.zm.Color;
import schule.ngb.zm.Vector;
import schule.ngb.zm.Zeichenmaschine;
import schule.ngb.zm.anim.Animations;
import schule.ngb.zm.anim.MoveAnimation;

public class Schachbrett extends Zeichenmaschine {


    public static final int ABSTAND = 32;

    public static final int FELD_BREITE = 64;

    private final Figur[][] figuren;

    public Schachbrett() {
        super(2 * ABSTAND + 8 * FELD_BREITE, 2 * ABSTAND + 8 * FELD_BREITE, "Schach", false);
        figuren = new Figur[8][8];
    }

    @Override
    public void setup() {
        background.setColor(180);

        drawing.setAnchor(NORTHWEST);

        drawing.noFill();
        drawing.setStrokeColor(20);
        drawing.setStrokeWeight(8);
        drawing.square(ABSTAND, ABSTAND, 8 * FELD_BREITE);

        drawing.setStrokeColor(33);
        drawing.setStrokeWeight(1);

        for( int row = 0; row < 8; row++ ) {
            for( int col = 0; col < 8; col++ ) {
                if( istSchwarzesFeld(col, row) ) {
                    // drawing.setFillColor(50);
                    // drawing.square(ABSTAND + col * FELD_BREITE, ABSTAND + row * FELD_BREITE, FELD_BREITE);
                    fillBlackSquare(row, col);
                } else {
                    drawing.setFillColor(200);
                    drawing.square(ABSTAND + col * FELD_BREITE, ABSTAND + row * FELD_BREITE, FELD_BREITE);
                }
            }
        }
    }

    private void fillBlackSquare( int row, int col ) {
        drawing.setFillColor(200);
        drawing.square(ABSTAND + col * FELD_BREITE, ABSTAND + row * FELD_BREITE, FELD_BREITE);

        for( int i = 0; i < FELD_BREITE * 2; i += 5 ) {
            if( i < FELD_BREITE ) {
                drawing.line(
                    ABSTAND + col * FELD_BREITE,
                    ABSTAND + row * FELD_BREITE + i,
                    ABSTAND + col * FELD_BREITE + i,
                    ABSTAND + row * FELD_BREITE
                );
            } else {
                drawing.line(
                    ABSTAND + col * FELD_BREITE + (i % FELD_BREITE),
                    ABSTAND + row * FELD_BREITE + FELD_BREITE,
                    ABSTAND + col * FELD_BREITE + FELD_BREITE,
                    ABSTAND + row * FELD_BREITE + (i % FELD_BREITE)
                );
            }
        }
    }

    public Vector getFeldCoords( int linie, int reihe ) {
        return new Vector(
            ABSTAND + ((linie - 1) + .5) * FELD_BREITE,
            canvasHeight - ABSTAND - ((reihe - 1) + .5) * FELD_BREITE
        );
    }

    public boolean istSchwarzesFeld( int linie, int reihe ) {
        return (linie + reihe) % 2 == 0;
    }

    public Figur getFigurAuf( int linie, int reihe ) {
        return figuren[linie - 1][reihe - 1];
    }

    public void setFigurAuf( int linie, int reihe, Figur pFigur ) {
        figuren[linie - 1][reihe - 1] = pFigur;
    }

    public Figur entferneFigurVon( int linie, int reihe ) {
        return entferneFigurVon(linie, reihe, false);
    }

    public Figur entferneFigurVon( int linie, int reihe, boolean keepShape ) {
        Figur fig = getFigurAuf(linie, reihe);
        setFigurAuf(linie, reihe, null);
        if( !keepShape ) {
            shapes.remove(fig);
        }
        return fig;
    }

    public void neueFigurAuf( int linie, int reihe, String pTyp, Color pColor ) {
        Vector pos = getFeldCoords(linie, reihe);

        Figur fig = new Figur(pos.x, pos.y, pTyp);
        fig.scale(.8);
        fig.setColor(pColor);

        setFigurAuf(linie, reihe, fig);
        shapes.add(fig);
    }

    public void neueFigurSchwarz( int linie, int reihe, String pTyp ) {
        neueFigurAuf(linie, reihe, pTyp, BLACK);
    }

    public void neueFigurWeiss( int linie, int reihe, String pTyp ) {
        neueFigurAuf(linie, reihe, pTyp, WHITE);
    }

    public void bewegeFigur( int vonLinie, int vonReihe, int nachLinie, int nachReihe ) {
        Vector pos = getFeldCoords(nachLinie, nachReihe);

        Figur fig = entferneFigurVon(vonLinie, vonReihe, true);
        Animations.playAndWait(new MoveAnimation(fig, pos.x, pos.y, (int) (pos.length()), DEFAULT_EASING));
        setFigurAuf(nachLinie, nachReihe, fig);
    }

}
