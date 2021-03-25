/**
 * Berechnung der Kenngrößen für den ID3-Algorithmus. Nachdem alle
 * Datensätze gezählten wurden können Entropie und Informationsgewinn
 * der Attribute und des Gesamtdatensatzes abgerufen werden.
 */
public class ID3 {

    private int total_0 = 0, total_1 = 0;

    private java.util.HashMap<String, Integer> counts;

    public ID3() {
        counts = new java.util.HashMap<String, Integer>();
    }

    /**
     * Zählt die Attribute des übergebenen Passagiers.
     *
     * @param pPassenger Ein Passagier-Datensatz
     */
    public void count( Passenger pPassenger ) {
        count(pPassenger.survived);
        count("clazz", pPassenger.clazz, pPassenger.survived);
        count("sex", pPassenger.sex, pPassenger.survived);
        count("age", pPassenger.age, pPassenger.survived);
        count("sibsp", pPassenger.sibsp, pPassenger.survived);
        count("parch", pPassenger.parch, pPassenger.survived);
        count("embarked", pPassenger.embarked, pPassenger.survived);
    }

    /**
     * Erhöht den Zähler für Überlebende / Verstobene Passagiere im
     * gesamten Datensatz.
     *
     * @param pSurvived 1 - überlebt, 0 - verstorben
     */
    public void count( int pSurvived ) {
        if( pSurvived == 0 ) {
            total_0 += 1;
        } else {
            total_1 += 1;
        }
    }

    /**
     * Erhöht den Zähler für die Kombination aus Attribut, Wert
     * und Zielwert um Eins.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @param pSurvived  1 - überlebt, 0 - verstorben
     */
    public void count( String pAttribute, int pValue, int pSurvived ) {
        count(pAttribute, String.valueOf(pValue), pSurvived);
    }

    /**
     * Erhöht den Zähler für die Kombination aus Attribut, Wert
     * und Zielwert um Eins.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @param pSurvived  1 - überlebt, 0 - verstorben
     */
    public void count( String pAttribute, String pValue, int pSurvived ) {
        String name = getKey(pAttribute, pValue, pSurvived);
        counts.put(name, counts.getOrDefault(name, 0) + 1);
    }

    /**
     * @return Anzahl an bisher gezählten Datensätzen.
     */
    public int getTotal() {
        return total_0 + total_1;
    }

    /**
     * Ermittelt die Anzahl an bisher gezählten Datensätzen, die den
     * angegebenen Zielwert haben.
     *
     * @param pSurvived 1 - überlebt, 0 - verstorben
     * @return Anzahl an passenden Datensätzen.
     */
    public int getTotal( int pSurvived ) {
        if( pSurvived == 0 ) {
            return total_0;
        } else {
            return total_1;
        }
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert Kombination.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public int getCount( String pAttribute, int pValue ) {
        return getCount(pAttribute, String.valueOf(pValue));
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert Kombination.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public int getCount( String pAttribute, String pValue ) {
        int anzahl_0 = getCount(pAttribute, pValue, 0);
        int anzahl_1 = getCount(pAttribute, pValue, 1);
        return anzahl_0 + anzahl_1;
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert/Zielwert Kombination.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @param pSurvived  1 - überlebt, 0 - verstorben
     * @return
     */
    public int getCount( String pAttribute, int pValue, int pSurvived ) {
        return getCount(pAttribute, String.valueOf(pValue), pSurvived);
    }

    /**
     * Anzahl bisher gezählter Datensätze mit der angegebenen
     * Attribut/Wert/Zielwert Kombination.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @param pSurvived  1 - überlebt, 0 - verstorben
     * @return
     */
    public int getCount( String pAttribute, String pValue, int pSurvived ) {
        String name = getKey(pAttribute, pValue, pSurvived);
        return counts.getOrDefault(name, 0);
    }

    /**
     * Anteil des Zielwertes am Gesamtdatensatz.
     *
     * @param pSurvived 1 - überlebt, 0 - verstorben
     * @return
     */
    public double getRatio( int pSurvived ) {
        return (double) getTotal(pSurvived) / (double) getTotal();
    }

    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert
     * Kombination am Gesamtdatensatz.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public double getRatio( String pAttribute, int pValue ) {
        return getRatio(pAttribute, String.valueOf(pValue));
    }

    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert
     * Kombination am Gesamtdatensatz.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public double getRatio( String pAttribute, String pValue ) {
        return (double) getCount(pAttribute, pValue) / (double) getTotal();
    }

    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert/Zielwert
     * Kombination am Gesamtdatensatz.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public double getRatio( String pAttribute, int pValue, int pSurvived ) {
        return getRatio(pAttribute, String.valueOf(pValue), pSurvived);
    }


    /**
     * Anteil der Datensätze mit der übergebenen Attribut/Wert/Zielwert
     * Kombination am Gesamtdatensatz.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public double getRatio( String pAttribute, String pValue, int pSurvived ) {
        return (double) getCount(pAttribute, pValue, pSurvived) / (double) getCount(pAttribute, pValue);
    }

    /**
     * Entropie des Gesamtdatensatzes.
     *
     * @return
     */
    public double entropie() {
        double ratio_0 = getRatio(0);
        double ratio_1 = getRatio(1);
        return -1 * ratio_0 * log2(ratio_0) - ratio_1 * log2(ratio_1);
    }

    /**
     * Entropie der Attribut/Wert Kombination.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public double entropie( String pAttribute, int pValue ) {
        return entropie(pAttribute, String.valueOf(pValue));
    }

    /**
     * Entropie der Attribut/Wert Kombination.
     *
     * @param pAttribute Name des Attributs
     * @param pValue     Konkreter Wert des Attributs
     * @return
     */
    public double entropie( String pAttribute, String pValue ) {
        double ratio_0 = getRatio(pAttribute, pValue, 0);
        double ratio_1 = getRatio(pAttribute, pValue, 1);
        return -1 * ratio_0 * log2(ratio_0) - ratio_1 * log2(ratio_1);
    }

    /**
     * Informationsgewinn eines Attributs.
     *
     * @param pAttribute
     * @return
     */
    public double informationgain( String pAttribute ) {
        double ig = entropie();

        String[] values = Passenger.getValues(pAttribute);
        for( int j = 0; j < values.length; j++ ) {
            String value = values[j];
            if( getCount(pAttribute, value) > 0 ) {
                double a = getRatio(pAttribute, value);
                double e = entropie(pAttribute, value);
                ig -= (a * e);
            }
        }

        return ig;
    }

    /**
     * Hilfsfunktion zur Berechnung des Logarithmus zu Basis 2.
     *
     * @param x
     * @return
     */
    private double log2( double x ) {
        return Math.log(x) / Math.log(2);
    }

    /**
     * Hilfsfunktion um aus einer Attribut/Wert/Zielwert Kombination
     * einen Hash-Key zu genereieren.
     *
     * @param attribut
     * @param wert
     * @param ziel
     * @return
     */
    private String getKey( String attribut, String wert, int ziel ) {
        return attribut + ":" + wert + ":" + ziel;
    }

}
