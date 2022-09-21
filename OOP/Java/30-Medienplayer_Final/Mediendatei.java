
/**
 * Klasse für eine Mediendatei, die vom Medienplayer
 * abgespielt/angezeigt werden kann.
 *
 * Mögliche Medien sind Audio, Video und Bild.
 *
 * @author J. Neugebauer <schule@neugebauer.cc>
 * @version 2018-05-17
 */
public abstract class Mediendatei
{
    // Pfad zur Mediendatei auf der Festplatte
    protected String datei;

    // Titel des Mediums (Dateiname bei Bildern)
    protected String titel;

    /**
     * Konstruktor für Objekte der Klasse Medium
     */
    public Mediendatei( String pDatei, String pTitel )
    {
        datei = pDatei;
        titel = pTitel;
    }

    public String getDatei()
    {
        return datei;
    }

    public String getTitel()
    {
        return titel;
    }

    public void abspielen()
    {
        System.out.printf("Abspielen von Datei %s.\n", datei);
    }

    public void stoppen()
    {
        System.out.printf("Wiedergabe von Datei %s gestoppt.\n", datei);
    }


    public String toString()
    {
        return "Mediendatei(" + titel + "," + datei + ")";
    }
}
