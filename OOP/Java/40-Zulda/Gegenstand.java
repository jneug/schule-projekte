import ea.*;

/**
 * Abstrakte Basisklasse für einen Gegenstand. Jeder Gegenstand kennt die Karte,
 * auf der er sich befindet. Die Karte kann sich ändern, wenn der Spieler den
 * Gegenstand einsammelet und auf einer anderen Karte wieder ablegt.
 */
public abstract class Gegenstand extends Knoten {

    protected Karte karte;

    public Gegenstand( Karte pKarte ) {
        karte = pKarte;
    }

    public Karte getKarte() {
        return karte;
    }

    public void setKarte( Karte pKarte ) {
        karte = pKarte;
    }

    /**
     * Wird aufgerufen, wenn dieser Gegenstand vom Spieler benutzt wird.
     * @param pLunk
     */
    public void benutzen( Lunk pLunk ) {}

    /**
     * Wird aufgerufen, wenn der Spieler diesen Gegenstand auf einen Gegner anwendet.
     * @param pLunk
     * @param pGegner
     */
    public void anwenden( Lunk pLunk, Gegner pGegner ) {}

    /**
     * Wird aufgerufen, wenn dieser Gegenstand vom Spieler eingesammelt wird.
     * @param pLunk
     */
    public abstract void einsammeln( Lunk pLunk );

    /**
     * Wird aufgerufen, wenn dieser Gegenstand vom Spieler eingesammelt wird.
     * @param pLunk
     * @param pKarteNeu
     */
    public abstract void ablegen( Lunk pLunk, Karte pKarteNeu );

    /**
     * Wird aufgerufen, wenn der Gegenstand zerstoert wird.
     */
    public abstract void zerstoeren();

}
