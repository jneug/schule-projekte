
/**
 * Klasse für ein Medium, das vom Medienplayer abgespielt werden kann.
 * 
 * Mögliche Medien sind Audio, Video und Bild.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Audio extends AbspielbareMediendatei
{
    
    // Künstler einer Audiodatei
    private String kuenstler;
    

    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public Audio( String pDatei, String pTitel, String pKuenstler, 
            int pLaenge )
    {
        super(pDatei, pTitel, pLaenge);
        kuenstler = pKuenstler;
    }
    
    public String getKuenstler()
    {
        return kuenstler;
    }
    
    public String toString()
    {
        return "Audio(" + titel + "," + datei + ")";
    }
}
