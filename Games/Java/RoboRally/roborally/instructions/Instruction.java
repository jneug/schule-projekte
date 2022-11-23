package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Basisklasse für eine Anweisung, die ein Roboter ausführt.
 * <p>
 * Eine Anweisung lässt einen {@link Robot Roboter} eine festgelegte, kurze
 * Aktion ausführen (ein Feld vorwärts, 90 Grad nach links drehen), wenn sie dem
 * Roboter einprogrammiert wurde. Die Ausführung findet in der
 * {@link #execute(Robot)} Methode für den der Methode als Parameter angegebenen
 * Roboter statt.
 * <p>
 * Anweisung werden im Spiel als Spielkarte dargestellt, die mit der
 * {@link #draw(int, int, DrawingLayer)} Methode dargestellt wird.
 */
public abstract class Instruction extends Constants {

    /**
     * Konfiguration: Breite einer Anweisung als Spielkarte.
     */
    public static final int CARD_WIDTH = 70;

    /**
     * Konfiguration: Höhe einer Anweisung als Spielkarte.
     */
    public static final int CARD_HEIGHT = 100;

    /**
     * Führt die Anweisung für den angegebenen Roboter aus.
     *
     * @param robot Der Roboter, der die Anweisung ausführt.
     */
    public abstract void execute( Robot robot );

    /**
     * Zeichnet die Anweisung als Spielkarte auf die Zeichenfläche.
     * <p>
     * Die Standardimplementierung zeichnet die Karte als Rechteck mit
     * abgerundeten Ecken. Die Implemeniterung muss sicherstellen, dass die
     * Darstellung die vorgesehene {@link #CARD_WIDTH Breite} und
     * {@link #CARD_HEIGHT Höhe} einer Karte nicht überschreitet.
     * <p>
     * Die <em>Pixelkoordinaten</em>, an der die obere linke Ecke der Karte
     * liegen muss, werden der Methode als Parameter übergeben.
     *
     * @param pPixelX Die x-Koordinate auf der Zeichenfläche.
     * @param pPixelY Die y-Koordinate auf der Zeichenfläche.
     * @param drawing Die Zeichenfläche.
     */
    public void draw( int pPixelX, int pPixelY, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(LIGHTGRAY);
        drawing.roundedRect(pPixelX, pPixelY, CARD_WIDTH, CARD_HEIGHT, 4, NORTHWEST);
    }

}
