

public class ID3 {

    private int gesamt_0 = 0, gesamt_1 = 0;

    private java.util.HashMap<String, Integer> anzahlen;

    public ID3() {
        anzahlen = new java.util.HashMap<String, Integer>();
    }

    /**
     * Zählt die Attribute des übergebenen Passagiers.
     * @param p
     */
    public void zaehlen( Passagier p ) {
        zaehlen(p.survived);
        zaehlen("clazz", p.clazz, p.survived);
        zaehlen("sex", p.sex, p.survived);
        zaehlen("age", p.age, p.survived);
        zaehlen("sibsp", p.sibsp, p.survived);
        zaehlen("parch", p.parch, p.survived);
        zaehlen("embarked", p.embarked, p.survived);
    }

    /**
     * Erhöht den Zähler für Überlebende / Verstobene Passagiere im
     * gesamten Datensatz.
     * @param ziel 1 - überlebt, 0 - verstorben
     */
    public void zaehlen( int ziel ) {
        if( ziel == 0 ) {
            gesamt_0 += 1;
        } else {
            gesamt_1 += 1;
        }
    }

    /**
     * Erhöht den Zähler für die Kombination aus Attribut, Wert
     * und Zielwert um Eins.
     * @param attribut Name des Attributs
     * @param wert Konkreter Wert des Attributs
     * @param ziel 1 - überlebt, 0 - verstorben
     */
    public void zaehlen( String attribut, int wert, int ziel ) {
        zaehlen(attribut, String.valueOf(wert), ziel);
    }

    /**
     * Erhöht den Zähler für die Kombination aus Attribut, Wert
     * und Zielwert um Eins.
     * @param attribut Name des Attributs
     * @param wert Konkreter Wert des Attributs
     * @param ziel 1 - überlebt, 0 - verstorben
     */
    public void zaehlen( String attribut, String wert, int ziel ) {
        String name = getKey(attribut, wert, ziel);
        anzahlen.put(name, anzahlen.getOrDefault(name, 0) + 1);
    }

    /**
     * @return Anzahl an bisher gezählten Datensätzen zurück.
     */
    public int gesamt() {
        return gesamt_0 + gesamt_1;
    }

    /**
     * Ermittelt die Anzahl an bisher gezählten Datensätzen, die den
     * angegebenen Zielwert haben.
     * @param ziel 1 - überlebt, 0 - verstorben
     * @return Anzahl an passenden Datensätzen.
     */
    public int gesamt( int ziel ) {
        if( ziel == 0 ) {
            return gesamt_0;
        } else {
            return gesamt_1;
        }
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert Kombination.
     * @param attribut
     * @param wert
     * @return
     */
    public int anzahl( String attribut, int wert ) {
        return anzahl(attribut, String.valueOf(wert));
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert Kombination.
     * @param attribut
     * @param wert
     * @return
     */
    public int anzahl( String attribut, String wert ) {
        int anzahl_0 = anzahl(attribut, wert, 0);
        int anzahl_1 = anzahl(attribut, wert, 1);
        return anzahl_0 + anzahl_1;
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert/Zielwert Kombination.
     * @param attribut
     * @param wert
     * @param ziel 1 - überlebt, 0 - verstorben
     * @return
     */
    public int anzahl( String attribut, int wert, int ziel ) {
        return anzahl(attribut, String.valueOf(wert), ziel);
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert/Zielwert Kombination.
     * @param attribut
     * @param wert
     * @param ziel 1 - überlebt, 0 - verstorben
     * @return
     */
    public int anzahl( String attribut, String wert, int ziel ) {
        String name = getKey(attribut, wert, ziel);
        return anzahlen.getOrDefault(name, 0);
    }

    /**
     * Anteil des Zielwertes am Gesamtdatensatz.
     * @param ziel 1 - überlebt, 0 - verstorben
     * @return
     */
    public double anteil( int ziel ) {
        return (double) gesamt(ziel) / (double) gesamt();
    }

    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert
     * Kombination am Gesamtdatensatz,
     * @param attribut
     * @param wert
     * @return
     */
    public double anteil( String attribut, int wert ) {
        return anteil(attribut, String.valueOf(wert));
    }

    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert
     * Kombination am Gesamtdatensatz,
     * @param attribut
     * @param wert
     * @return
     */
    public double anteil( String attribut, String wert ) {
        return (double) anzahl(attribut, wert) / (double) gesamt();
    }

    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert/Zielwert
     * Kombination am Gesamtdatensatz,
     * @param attribut
     * @param wert
     * @return
     */
    public double anteil( String attribut, int wert, int ziel ) {
        return anteil(attribut, String.valueOf(wert), ziel);
    }


    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert/Zielwert
     * Kombination am Gesamtdatensatz,
     * @param attribut
     * @param wert
     * @return
     */
    public double anteil( String attribut, String wert, int ziel ) {
        return (double) anzahl(attribut, wert, ziel) / (double) anzahl(attribut, wert);
    }

    /**
     * Entropie des Gesamtdatensatzes.
     * @return
     */
    public double entropie() {
        double anteil_0 = anteil(0);
        double anteil_1 = anteil(1);
        return -1 * anteil_0 * log2(anteil_0) - anteil_1 * log2(anteil_1);
    }

    /**
     * Entropie der Attribut/Wert Kombination.
     * @return
     */
    public double entropie( String attribut, int wert ) {
        return entropie(attribut, String.valueOf(wert));
    }

    /**
     * Entropie der Attribut/Wert Kombination.
     * @return
     */
    public double entropie( String attribut, String wert ) {
        double anteil_0 = anteil(attribut, wert, 0);
        double anteil_1 = anteil(attribut, wert, 1);
        return -1 * anteil_0 * log2(anteil_0) - anteil_1 * log2(anteil_1);
    }

    /**
     * Informationsgewinn eines Attributs.
     * @param attribut
     * @return
     */
    public double informationsgewinn( String attribut ) {
        double ig = entropie();

        String[] werte = Passagier.getWerte(attribut);
        for( int j = 0; j < werte.length; j++ ) {
            String wert = werte[j];
            if( anzahl(attribut, wert) > 0 ) {
                double a = anteil(attribut, wert);
                double e = entropie(attribut, wert);
                ig -= (a * e);
            }
        }

        return ig;
    }

    /**
     * Hilfsfunktion zur Berechnung des Logarithmus zu Basis 2.
     * @param x
     * @return
     */
    private double log2( double x ) {
        return Math.log10(x) / Math.log10(2);
    }

    /**
     * Hilfsfunktion um aus einer Attribut/Wert/Zielwert Kombination
     * einen Hash-Key zu genereieren.
     * @param attribut
     * @param wert
     * @param ziel
     * @return
     */
    private String getKey( String attribut, String wert, int ziel ) {
        return attribut + ":" + wert + ":" + ziel;
    }

}