import ea.*;

import java.util.Random;

/**
 * Ein Ork ist ein prototypischer Gegner, der sich durch die Welt bewegt. Im
 * Momeent allerdings ohne Interaktionen mit dem Spieler.
 *
 * Das Verhalten des orks wird in der methode "tick()" implementiert.
 */
public class Ork extends Gegner implements Ticker {

    public Ork( Karte pKarte) {
        super(800, 30, 10, pKarte, "images/monster_1.gif");
    }

    @Override
    public void tick() {
        /*aufg*
        // TODO: Implemenitere ein Verhalten für den Ork. Er könnte sich zum
        Beispiel zufällig durch die Welt bewegen. (Nutze z.B. die Klasse
        Random dafür: https://link.ngb.schule/zufallszahlen).
        *aufg*/
        //ml*
        Random rand = new Random();
        int direction = rand.nextInt(4);
        switch( direction ) {
            case 1:
                karte.bewegeLinks(this);
                break;
            case 2:
                karte.bewegeHoch(this);
                break;
            case 3:
                karte.bewegeRunter(this);
                break;
            default:
                karte.bewegeRechts(this);
                break;
        }
        //*ml
    }

    /**
     * Startet das Verhalten des Gegners.
     */
    public void start() {
        // Anmelden, sodass die tick()-Methode alle 250 ms ausgeführt wird
        Manager.standard.anmelden(this, 250);
    }

    /**
     * Stoppt das Verhalten des Gegners.
     */
    public void stopp() {
        // Abmelden, sodass der Ork sich nicht mehr bewegt
        Manager.standard.abmelden(this);
    }

}
