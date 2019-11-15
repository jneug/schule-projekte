
/**
 * Klasse für ein Bild, das vom Medienplayer angezeigt werden kann.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Bild
{
    // Pfad zur Bilddatei auf der Festplatte
    private String datei;
    
    // Dateiname des Bildes
    private String titel;
    
    // Breite des Bildes
    private int breite;
    
    // Breite des Bildes
    private int hoehe;
    
    // Farbtiefe des Bildes
    private int farbtiefe;
    

    /**
     * Konstruktor für Objekte der Klasse Bild
     */
    public Bild( String pDatei, String pTitel, int pBreite, int pHoehe, int pFarbtiefe )
    {
        datei = pDatei;
        titel = pTitel;
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
     * Ändert die Größe eines Bildes.
     * @param pBreiteNeu Die neue Breite des Bildes.
     * @param pHoeheNeu Die neue Höhe des Bildes.
     */
    public void groesseAendern( int pBreiteNeu, int pHoeheNeu )
    {
        // TODO: Implement
    }
}
