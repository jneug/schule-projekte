import java.util.Random;


/**
 * Ein Container mit Inhalt, Gewicht, Start- und Zielort und einem Besitzer.
 *
 * Die Daten können per Getter-Methoden abgerufen werden.
 *
 * !!! Die ist eine Daten-Klasse und muss nicht verändert werden!
 */
public class Container {

    // Objetkvariablen für die Eigenschaften eines Containers
    private String inhalt;
    private double gewicht;
    private String zielort;
    private String startort;
    private String besitzer;

    /**
     * Erstellt ein Container-Objekt mit zufällig gewählten Daten.
     */
    public Container() {
        Random rand = new Random();
        // Zufällige Daten erzeugen
        startort = ORTE[rand.nextInt(ORTE.length)];
        zielort = ORTE[rand.nextInt(ORTE.length)];
        inhalt = INHALTE[rand.nextInt(INHALTE.length)];
        besitzer = BESITZER[rand.nextInt(BESITZER.length)];
        gewicht = (double)Math.round(rand.nextDouble()* 3000.0) / 100.0;
    }

    /*+
     * Erstellt ein Container-Objekt mit den angegeben Daten.
     */
    public Container( String pInhalt, double pGewicht, String pZielort, String pStartort, String pBesitzer ) {
        inhalt = pInhalt;
        gewicht = pGewicht;
        zielort = pZielort;
        startort = pStartort;
        besitzer = pBesitzer;
    }

    public String getInhalt() {
        return inhalt;
    }

    public double getGewicht() {
        return gewicht;
    }

    public String getZielort() {
        return zielort;
    }

    public String getStartort() {
        return startort;
    }

    public String getBesitzer() {
        return besitzer;
    }

    /**
     * Gibt den Container als String zurück. Kann zur Ausgabe genutzt werden.
     * @return
     */
    @Override
    public String toString() {
        return "Container" + '[' +
            "inhalt='" + inhalt + '\'' +
            ",gewicht=" + gewicht +
            ",zielort='" + zielort + '\'' +
            ",startort='" + startort + '\'' +
            ",besitzer='" + besitzer + '\'' +
            ']';
    }



    // Speicher für die Zufallsdaten der Container.
    // Hier können bei Bedarf weitere Daten eingefügt werden.

    private static final String[] ORTE = new String[]{
        "Berlin", "Hannover", "Amsterdam", "Bielefeld", "Herford",
        "Singapur", "Abu Dhabi", "Hong Kong", "New York"
    };

    private static final String[] BESITZER = new String[]{
        "Haapag Lloyd", "Greenline", "Samsung", "Apple", "Panasonic",
        "Tesla", "BMW", "Kik", "Rossmann", "Dr. Oetker"
    };

    private static final String[] INHALTE = new String[]{
        "Autos", "Computer", "Smartphones", "Tablets", "Windeln",
        "Kosmetika", "Bananen", "Kleidung", "Hilfsgüter", "Backpulver",
        "Tiefkühlpizza", "Batterien", "Mineralien", "Unbekannt"
    };
}
