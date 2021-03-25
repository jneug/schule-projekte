
/**
 * Klasse für ein Video, das vom Medienplayer abgespielt werden kann.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Video extends Mediendatei
{
    
    // Länge des Videos in Sekunden
    private int laenge;
    
    // Breite des Videos in Pixeln
    private int breite;
    
    // Breite des Videos in Pixeln
    private int hoehe;
    

    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public Video( String pDatei, String pTitel,
            int pLaenge, int pBreite, int pHoehe )
    {
        super(pDatei, pTitel);
        breite = pBreite;
        hoehe = pHoehe;
        laenge = pLaenge;
    }
    
    public int getBreite()
    {
        return breite;
    }
    
    public int getHoehe()
    {
        return hoehe;
    }
    
    public int getLaenge()
    {
        return laenge;
    }
    
    public void ausgabe()
    {
        System.out.println("Video: ");
        System.out.println("  Titel: "+titel);
        System.out.println("  Datei:" + datei);
        System.out.printf("  Länge: %d Sekunden", laenge);
        System.out.printf("  Format: %dx%d", breite, hoehe);
    }
    
    /**
     * Kürzt das Video auf eine neue Länge.
     * @param pLaengeNeu Die neue Länge (muss kürzer sein als die aktuelle Länge).
     */
    public void kuerzen( int pLaengeNeu )
    {
        if( pLaengeNeu < laenge )
        {
            laenge = pLaengeNeu;
        }
    }
}
