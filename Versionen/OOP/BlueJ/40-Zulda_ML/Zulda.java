import ea.*;

/**
 * Hauptklasse des Spiels. Hier werden die Objekte erstellt und alles
 * gestartet. Die Klasse reagiert auf Eingaben des Nutzers und gibt diese
 * zum Beispiel an die {@link Lunk Spielfigur} oder die {@link Welt} weiter.
 */
public class Zulda extends Game {

    private Welt welt;

    private Lunk lunk;

    public Zulda() {
        super(960, 720, "Zulda the Game");


        lunk = new Lunk();

        welt = new Welt(lunk);
        wurzel.add(welt);

        Sound musik = new Sound("sounds/And-the-Machines-Came-at-Midnight.mp3");
        musik.loop();
    }

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
            } else {
                lunk.aktionSetzen("slash_left");
            }
        }
    }

    public static void main(String[] args) {
        new Zulda();
    }

}
