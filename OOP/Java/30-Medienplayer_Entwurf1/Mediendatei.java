
/**
 * Klasse für eine Mediendatei, die vom Medienplayer 
 * abgespielt werden kann.
 * 
 * Mögliche Medien sind Audio, Video und Bild.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Mediendatei
{
    // Pfad zur Mediendatei auf der Festplatte
    private String datei;
    
    // Titel des Mediums (Dateiname bei Bildern)
    private String titel;
    
    // Künstler einer Audiodatei
    private String kuenstler;
    
    // Länge eines Videos/einer Audiodatei in Sekunden
    private int laenge;
    
    // Breite eines Bildes/Videos in Pixeln
    private int breite;
    
    // Breite eines Bildes/Videos in Pixeln
    private int hoehe;
    
    // Farbtiefe eines Bildes
    private int farbtiefe;
    
    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public Mediendatei( String pDatei, String pTitel, String pKuenstler, 
            int pLaenge, int pBreite, int pHoehe, int pFarbtiefe )
    {
        datei = pDatei;
        titel = pTitel;
        kuenstler = pKuenstler;
        laenge = pLaenge;
        breite = pBreite;
        hoehe = pHoehe;
        farbtiefe = pFarbtiefe;
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
    
    public int getBreite()
    {
        return breite;
    }
    
    public int getHoehe()
    {
        return hoehe;
    }
    
    public int getFarbtiefe()
    {
        return farbtiefe;
    }
    
    /**
     * Kürzt ein Video oder eine Audiodatei auf eine neue Länge.
     * @param pLaengeNeu Die neue Länge (muss kürzer sein als die aktuelle Länge).
     */
    public void kuerzen( int pLaengeNeu )
    {
        // TODO: Implement
    }
    
    /**
     * Ändert die Größe eines Bildes.
     * @param pBreiteNeu Die neue Breite des Bildes.
     * @param pHoeheNeu Die neue Höhe des Bildes.
     */
    public void groesseAendern( int pBreiteNeu, int pHoeheNeu )
    {
        // TODO: Implement
    }
}
