
/**
 * Klasse für ein Medium, das vom Medienplayer abgespielt werden kann.
 * 
 * Mögliche Medien sind Audio, Video und Bild.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Video
{
    // Pfad zur Mediendatei auf der Festplatte
    private String datei;
    
    // Titel des Mediums (Dateiname bei Bildern)
    private String titel;
    
    // Länge eines Videos/einer Audiodatei in Sekunden
    private int laenge;
    
    // Breite eines Bildes/Videos in Pixeln
    private int breite;
    
    // Breite eines Bildes/Videos in Pixeln
    private int hoehe;
    

    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public Video( String pDatei, String pTitel,
            int pLaenge, int pBreite, int pHoehe )
    {
        datei = pDatei;
        titel = pTitel;
        laenge = pLaenge;
        breite = pBreite;
        hoehe = pHoehe;
    }
    
    public String getDatei()
    {
        return datei;
    }

    public String getTitel()
    {
        return titel;
    }
    
    public int getLaenge()
    {
        return laenge;
    }
    
    public int getBreite()
    {
        return breite;
    }
    
    public int getHoehe()
    {
        return hoehe;
    }
    
    /**
     * Kürzt das Video auf eine neue Länge.
     * @param pLaengeNeu Die neue Länge (muss kürzer sein als die aktuelle Länge).
     */
    public void kuerzen( int pLaengeNeu )
    {
        // TODO: Implement
    }
}
