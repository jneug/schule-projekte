/**
 * Klasse für ein Medium, das vom Medienplayer abgespielt werden kann.
 * <p>
 * Mögliche Medien sind Audio, Video und Bild.
 *
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public class Bild extends Mediendatei {

    // Breite eines Bildes/Videos in Pixeln
    private int breite;

    // Breite eines Bildes/Videos in Pixeln
    private int hoehe;


    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public Bild( String pDatei, String pTitel, int pBreite, int pHoehe ) {
        super(pDatei, pTitel);
        breite = pBreite;
        hoehe = pHoehe;
    }

    public int getBreite() {
        return breite;
    }

    public int getHoehe() {
        return hoehe;
    }

    /**
     * Zeigt eine Bilddatei an.
     */
    public void abspielen() {
        System.out.printf("Abspielen von Bilddatei %s.\n", datei);
    }

    public String toString() {
        return "Bild(" + titel + "," + datei + ")";
    }

}
