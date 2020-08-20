
/**
 * Klasse für einen Motorroller
 *
 * Ein Motorroller wird von einem Besitzer "besessen" und ist
 * diesem per Referenz zugeordnet.
 *
 * @author J. Neugebauer
 * @version 1.0
 */
public class Motorroller
{
    // Attribute deklarieren
    private int kilometerstand;

    private double tankgroesse;

    private double tankinhalt;

    /**
     * Konstruktor für Objekte der Klasse Motorroller
     */
    public Motorroller( int pKilometerstand, double pTankgroesse, double pTankinhalt )
    {
        // Attribute initialisieren
        kilometerstand = Math.max(0,pKilometerstand);
        tankgroesse = pTankgroesse;
        tankinhalt = pTankinhalt;

        // TODO:
        // - Erstellt ein Objekt mit negativem Kilometerstand und Tankgroesse.
        // - Warum ist dieses Objekt nicht sinnvoll?
        // - Verhinder bei der Initialisierung der Attribute die Speicherung
        //      unsinniger Werte.
    }

    public int getKilometerstand()
    {
        return kilometerstand;
    }

    public double getTankgroesse()
    {
        return tankgroesse;
    }

    public double getTankinhalt()
    {
        return tankinhalt;
    }

    /**
     * Tankt den Roller um die übergebene Menge (in Litern) auf. Dabei kann
     * der Tank nicht voller werden, als durch die Tankgroesse
     * festgelegt ist. Das zuviel getankte Benzin verfällt einfach.
     */
    public void tanke( double pMenge )
    {
        tankinhalt += pMenge;
        if( tankinhalt > tankgroesse ) {
            tankinhalt = tankgroesse;
        } else if( tankinhalt < 0 ) {
            tankinhalt = 0;
        }
    }

    /**
     * Fährt den Roller eine angegebene Strecke (in km). Der Roller verbraucht
     * pro Kilometer 0.625 Liter Benzin. Ist im Tank nicht genug Benzin für die
     * volle Strecke, dann fährt der Roller nur so weit, wie er mit dem verbleibenden
     * Benzin kommt. Der Kilometerstand wird um die gefahrene Strecke hochgezählt.
     */
    public void fahre( int pStrecke ) {
        if( pStrecke * 0.625 > tankinhalt ) {
            pStrecke = (int) (tankinhalt / 0.625);
        }

        double verbrauch = pStrecke * 0.625;
        kilometerstand += pStrecke;
        tankinhalt -= verbrauch;
    }
}