
public class Datensatz
{
    public String vorhersage;
    public String temperatur;
    public String feuchtigkeit;
    public String wind;

    /**
     * Konstruktor für Objekte der Klasse Datensatz
     */
    public Datensatz( String pVorhersage, String pTemperatur, 
            String pFeuchtigkeit, String pWind )
    {
        vorhersage = pVorhersage;
        temperatur = pTemperatur;
        feuchtigkeit = pFeuchtigkeit;
        wind = pWind;
    }
}
