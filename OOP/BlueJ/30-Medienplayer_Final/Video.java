
/**
 * Klasse für ein Medium, das vom Medienplayer abgespielt werden kann.
 * 
 * Mögliche Medien sind Audio, Video und Bild.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Video extends AbspielbareMediendatei
{
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
        super(pDatei, pTitel, pLaenge);
        breite = pBreite;
        hoehe = pHoehe;
    }
    
    public int getBreite()
    {
        return breite;
    }
    
    public int getHoehe()
    {
        return hoehe;
    }
    
    public String toString()
    {
        return "Video(" + titel + "," + datei + ")";
    }
}
