
public class Datensatz
{
    public String vorhersage;
    public String temperatur;
    public String feuchtigkeit;
    public String wind;

    public Datensatz() {
        this("sonnig", "heiß", "hoch", "schwach");
    }
    public Datensatz( String pVorhersage, String pTemperatur, 
            String pFeuchtigkeit, String pWind )
    {
        vorhersage = pVorhersage;
        temperatur = pTemperatur;
        feuchtigkeit = pFeuchtigkeit;
        wind = pWind;
    }
}
