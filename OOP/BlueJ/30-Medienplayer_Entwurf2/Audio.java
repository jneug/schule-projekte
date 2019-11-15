
/**
 * Klasse für ein Medium, das vom Medienplayer abgespielt werden kann.
 * 
 * Mögliche Medien sind Audio, Video und Bild.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Audio
{
    // Pfad zur Mediendatei auf der Festplatte
    private String datei;
    
    // Titel des Mediums (Dateiname bei Bildern)
    private String titel;
    
    // Künstler einer Audiodatei
    private String kuenstler;
    
    // Länge eines Videos/einer Audiodatei in Sekunden
    private int laenge;
    

    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public Audio( String pDatei, String pTitel, String pKuenstler, 
            int pLaenge )
    {
        datei = pDatei;
        titel = pTitel;
        kuenstler = pKuenstler;
        laenge = pLaenge;
    }
    
    public String getDatei()
    {
        return datei;
    }

    public String getTitel()
    {
        return titel;
    }
    
    public String getKuenstler()
    {
        return kuenstler;
    }
    
    public int getLaenge()
    {
        return laenge;
    }

    /**
     * Spielt eine Video- oder Audiodatei ab.
     */
    public void abspielen() {
    }
    
    /**
     * Kürzt die Audiodatei auf eine neue Länge.
     * @param pLaengeNeu Die neue Länge (muss kürzer sein als die aktuelle Länge).
     */
    public void kuerzen( int pLaengeNeu )
    {
        // TODO: Implement
    }
}
