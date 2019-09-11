

public class Zaehler {

    private int gesamt_0 = 0, gesamt_1 = 0;

    private java.util.HashMap<String, Integer> zahlen;

    public Zaehler() {
        zahlen = new java.util.HashMap<String, Integer>();
    }

    public void zaehlen( Passagier p ) {
    zaehlen(p.survived);
        zaehlen("clazz", p.clazz, p.survived);
        zaehlen("sex", p.sex, p.survived);
        zaehlen("age", p.age, p.survived);
        zaehlen("sibsp", p.sibsp, p.survived);
        zaehlen("parch", p.parch, p.survived);
        zaehlen("embarked", p.embarked, p.survived);
    }

    public void zaehlen( int ziel ) {
        if( ziel == 0 ) {
            gesamt_0 += 1;
        } else {
            gesamt_1 += 1;
        }
    }
    
    public void zaehlen( String attribut, int wert, int ziel ) {
        zaehlen(attribut, String.valueOf(wert), ziel);
    }

    public void zaehlen( String attribut, String wert, int ziel ) {
        String name = getName(attribut, wert, ziel);
        zahlen.put(name, zahlen.getOrDefault(name,0)+1);
    }

    public int gesamt() {
        return gesamt_0 + gesamt_1;
    }

    public int gesamt( int ziel ) {
        if( ziel == 0 ) {
            return gesamt_0;
        } else {
            return gesamt_1;
        }
    }

    public int anzahl( String attribut, String wert ) {
        int anzahl_0 = anzahl(attribut, wert, 0);
        int anzahl_1 = anzahl(attribut, wert, 1);
        return anzahl_0 + anzahl_1;
    }

    public int anzahl( String attribut, String wert, int ziel ) {
        String name = getName(attribut, wert, ziel);
        return zahlen.getOrDefault(name, 0);
    }

    public int anzahl( String attribut, int ziel ) {
        int anzahl = 0;
        java.util.Iterator<String> keys = zahlen.keySet().iterator();
        while( keys.hasNext() ) {
            String key = keys.next();
            String[] teile = key.split(":", 3);
            if( teile[0].equals(attribut) && teile[2].equals(String.valueOf(ziel)) ) {
                anzahl += 1;
            }
        }
        return anzahl;
    }

    public double anteil( int ziel ) {
        return (double)gesamt(ziel) / (double)gesamt();
    }

    public double anteil( String attribut, String wert ) {
        return (double)anzahl(attribut, wert) / (double)gesamt();
    }

    public double anteil( String attribut, String wert, int ziel ) {
        return (double)anzahl(attribut, wert, ziel) / (double)anzahl(attribut, wert);
    }

    public double entropie() {
        double anteil_0 = anteil(0);
        double anteil_1 = anteil(1);
        return -1 * anteil_0 * log2(anteil_0) - anteil_1 * log2(anteil_1);
    }

    public double entropie( String attribut, String wert ) {
        double anteil_0 = anteil(attribut, wert, 0);
        double anteil_1 = anteil(attribut, wert, 1);
        return -1 * anteil_0 * log2(anteil_0) - anteil_1 * log2(anteil_1);
    }

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

    private double log2( double x ) {
        return Math.log10(x) / Math.log10(2); 
    }

    private String getName(String attribut, String wert, int ziel) {
        return attribut+":"+wert+":"+ziel;
    }

}