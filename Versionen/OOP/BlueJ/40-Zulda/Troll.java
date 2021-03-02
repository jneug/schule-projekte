import ea.*;

import java.util.Random;

/**
 * Ein Ork ist ein prototypischer Gegner, der sich durch die Welt bewegt. Im
 * Momeent allerdings ohne Interaktionen mit dem Spieler.
 *
 * Das Verhalten des orks wird in der methode "tick()" implementiert.
 */
public class Troll extends Gegner implements Ticker {

    public Troll( Karte pKarte) {
        super(300, 30, 10, pKarte, "images/monster_1.gif");
    }

    @Override
    public void tick() {
        Lunk lunk = karte.getWelt().getSpieler();

        // Richtung zu Lunk bestimmen
        Vektor richtung = new Vektor(zentrum(), lunk.zentrum());
        // Länge des Vektors auf 24 setzen
        Vektor bewegung = richtung.normiert().multiplizieren(24);
        // In Richung Lunk bewegen.
        verschieben(bewegung);

        // Schneiden sich das Bild des Trolls und das von Lunk?
        if( this.schneidet(lunk) ) {
            // TODO: Berechne den Schaden, den Lunk nimmt
            lunk.addHitpoints((int) ((this.getAttack() - lunk.getDefense()) * -0.3) );
            lunk.aktionSetzen("hit_right");
        }
    }

    /**
     * Startet das Verhalten des Gegners.
     */
    @Override
    public void start() {
        // Anmelden, sodass die tick()-Methode alle 250 ms ausgeführt wird
        Manager.standard.anmelden(this, 250);
    }

    /**
     * Stoppt das Verhalten des Gegners.
     */
    @Override
    public void stopp() {
        // Abmelden, sodass der Ork sich nicht mehr bewegt
        Manager.standard.abmelden(this);
    }

}
