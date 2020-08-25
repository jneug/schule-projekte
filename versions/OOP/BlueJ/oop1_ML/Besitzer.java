
/**
 * Klasse für einen Besitzer
 *
 * Ein Besitzer hat immer einen Namen und genau einen Motorroller.
 * Er kann Geld bekommen und weiß, ob er einen Führerschein hat.
 *
 * @author J. Neugebauer
 * @version 1.1
 */
public class Besitzer {

    // Objektvariablen deklarieren
    private String name;

    private double geld;

    private boolean fuehrerschein;

    private Motorroller meinRoller;

    /**
     * Konstruktor für Objekte der Klasse Besitzer
     */
    public Besitzer(String pName, double pGeld, boolean pFuehrerschein, Motorroller pMeinRoller) {
        // Objektvariablen initialisieren
        name = pName;
        geld = pGeld;
        fuehrerschein = pFuehrerschein;
        meinRoller = pMeinRoller;
    }

    public String getName() {
        return name;
    }

    public double getGeld() {
        return geld;
    }

    public boolean hatFuehrerschein() {
        return fuehrerschein;
    }

    /**
     * Gibt die Referenz auf das Objekt vom Typ "Motorroller" zurück.
     */
    public Motorroller getMeinenRoller() {
        return meinRoller;
    }

    /**
     * Ändert die Motorroller-Referenz auf ein neues Objekt vom Typ
     * "Motorroller".
     */
    public void setMeinenRoller( Motorroller pNeuerRoller ) {
        meinRoller = pNeuerRoller;
    }

    public void taschengeldBekommen( double pBetrag ) {
        if( pBetrag > 0 ) {
            geld = geld + pBetrag;
        }
    }

    /**
     * Tankt den zugeordneten Roller um die angegebene Menge (in Litern) auf.
     * Dabei wird zunächst geprüft wie viel Platz noch im Tank ist
     * und nur die Menge getankt, die auch noch in den Tank passt.
     * Für die getankte Menge müssen pro Liter 1.5 Euro gezahlt werden, die
     * vom vorhandenen Geld abgezogen werden.
     */
    public void tankeRoller( double pMenge ) {
        if( 1.5 * pMenge > geld ) {
            pMenge = geld/1.5;
        }
        if( meinRoller.getTankgroesse() < meinRoller.getTankinhalt() + pMenge ) {
            pMenge = meinRoller.getTankgroesse() - meinRoller.getTankinhalt();
        }
        meinRoller.tanke(pMenge);
        geld = geld - 1.5*pMenge;
    }

    /**
     * Fährt den Roller um die angegeben Strecke (in km). Der Besitzer
     * prüft zunächst, ob genug Benzin im Tank ist für die Strecke, und fährt
     * nur dann los. Er prüft dies, indem er ungefähr den Verbrauch des Rollers
     * abschätzt.
     */
    public void fahreRoller( int pStrecke ) {
        if( meinRoller.getTankinhalt() >= pStrecke*0.625 ) {
            meinRoller.fahre(pStrecke);
        }
    }

}
