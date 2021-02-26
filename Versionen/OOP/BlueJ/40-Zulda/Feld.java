import ea.*;

/**
 * Ein Feld (eng. Tile) stellt den Grundbaustein einer {@link Karte} dar. Sie
 * sind jeweils quadratische "Fliesen" der Größe 48x48 Pixel. Jede Fliese ist
 * einfach ein Bild, das den Untergrund der Karte darstellt. Der Prototyp
 * bietet vier verschiedene Untergründe. Der Typ wird dem Konstruktor als String
 * übergeben: "gras", "stein", "sand" oder "wasser".
 *
 * Felder sollten möglichst einfach gehalten sein und nicht zu viel Logik enthalten.
 * Allerdings könnte man z.B. auch eine Falle als Feld implemeniteren.
 */
public class Feld extends Bild {

    protected boolean istPassierbar;

    /**
     * Konstruktor des Feldes.
     * @param x x-Position
     * @param y y-Position
     * @param typ Typ des Untergrundes
     */
    public Feld( float x, float y, String typ ) {
        super(x, y, "images/feld_"+typ.toLowerCase()+".gif");
        istPassierbar = (typ.equals("gras") || typ.equals("sand"));
    }

    public boolean istPassierbar() {
        return istPassierbar;
    }

    public void setPassierbar( boolean pPassierbar ) {
        istPassierbar = pPassierbar;
    }

}
