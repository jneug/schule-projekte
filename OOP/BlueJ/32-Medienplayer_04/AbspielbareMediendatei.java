
/**
 * Klasse für ein Medium, das vom Medienplayer abgespielt werden kann.
 * 
 * Mögliche Medien sind Audio, Video und Bild.
 * 
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class AbspielbareMediendatei extends Mediendatei
{
    
    // Länge eines Videos/einer Audiodatei in Sekunden
    protected int laenge;

    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public AbspielbareMediendatei( String pDatei, String pTitel, int pLaenge )
    {
        super(pDatei, pTitel);
        laenge = pLaenge;
    }
    
    public int getLaenge()
    {
        return laenge;
    }

    /**
     * Spielt eine Video- oder Audiodatei ab.
     */
    public void abspielen() {
        super.abspielen();
        System.out.printf("  Audio-/Videodatei mit Länge %d.\n", laenge);
    }
    
}
