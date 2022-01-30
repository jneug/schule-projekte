import ea.*;

/**
 * Hauptklasse des Spiels. Hier werden die Objekte erstellt und alles
 * gestartet. Die Klasse reagiert auf Eingaben des Nutzers und gibt diese
 * zum Beispiel an die {@link Lunk Spielfigur} oder die {@link Welt} weiter.
 */
public class Zulda extends Game {

    // Konfigruationsvariablen
    public static int WIDTH = 960;
    public static int HEIGHT = 720;

    public static int WORLD_WIDTH = 4;
    public static int WORLD_HEIGHT = 3;

    public static int MAP_WIDTH = 20;
    public static int MAP_HEIGHT = 15;

    public static int TILE_SIZE = 48;


    private Welt welt;

    private Lunk lunk;

    public Zulda() {
        super(WIDTH, HEIGHT, "Zulda the Game");


        lunk = new Lunk();

        welt = new Welt(lunk);
        wurzel.add(welt);

        Sound musik = new Sound("sounds/And-the-Machines-Came-at-Midnight.mp3");
        musik.loop();
    }

    /**
     * Diese Methode verarbeitet alle Tasteneingaben während des Spiels.
     * @param tastencode
     */
    @Override
    public void tasteReagieren(int tastencode) {
        if( tastencode == Taste.LINKS ) {
            welt.bewegeLinks();
        } else if( tastencode == Taste.RECHTS ) {
            welt.bewegeRechts();
        } else if( tastencode == Taste.OBEN ) {
            welt.bewegeHoch();
        } else if( tastencode == Taste.UNTEN ) {
            welt.bewegeRunter();
        } else if( tastencode == Taste.LEERTASTE ) {
            if( lunk.aktuellesVerhalten().endsWith("right") ) {
                lunk.aktionSetzen("slash_right");
                welt.attackeRechts();
            } else {
                lunk.aktionSetzen("slash_left");
                welt.attackeLinks();
            }
        }
    }

    public static void main(String[] args) {
        new Zulda();
    }

}
