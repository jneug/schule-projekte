
public class Entscheider
{
    private BinaryTree<Knoten> baum;

    public Entscheider()
    {
        /*
         * Hier den Entscheidungsbaum aufbauen
         * von den Blättern zur Wurzel!
         */
        // z.B.
        BinaryTree<Knoten> klasseNein = new BinaryTree<Knoten>();
        klasseNein.setContent(new Klassifikation("nein"));

        BinaryTree<Knoten> eWind = new BinaryTree<Knoten>();
        eWind.setContent(new Entscheidung("wind", "schwach"));
        eWind.setLeftTree(klasseNein);

        // usw ...

        // bis zur Wurzel.
        // Die Wurzel des Baumes muss in der Objektvariablen
        // baum gepeichert werden!
        baum = new BinaryTree<Knoten>();
        baum.setContent(new Entscheidung("vorhersage", "sonnig"));
        baum.setRightTree(eWind);
    }


    public String testeDatensatz(Datensatz d)
    {
        // Bei der Wurzel anfangen
        BinaryTree<Knoten> knoten = baum;

        String antwort = "";
        while( antwort.equals("") || antwort.equals("links")
                || antwort.equals("rechts") ) {
            Knoten e = knoten.getContent();
            antwort = e.entscheide(d);

            if( antwort.equals("links") ) {
                // Im linken Teilbaum weiter
                // knoten = ...
            } else if( antwort.equals("rechts") ) {
                // Im rechten Teilbaum weiter
                // knoten = ...
            }
        }
        return antwort;
    }

    public Datensatz[] getTestdaten() {
        String[][] testdaten = new String[][]{
            new String[]{"sonnig", "heiß", "hoch", "schwach"},
            new String[]{"sonnig", "heiß", "hoch", "stark"},
            new String[]{"bewölkt", "heiß", "hoch", "schwach"},
            new String[]{"regnerisch", "mild", "hoch", "schwach"},
            new String[]{"regnerisch", "kalt", "normal", "schwach"},
            new String[]{"regnerisch", "kalt", "normal", "stark"},
            new String[]{"bewölkt", "mild", "hoch", "stark"},
            new String[]{"sonnig", "mild", "hoch", "schwach"},
            new String[]{"sonnig", "kalt", "normal", "schwach"},
            new String[]{"regnerisch", "mild", "normal", "schwach"},
            new String[]{"sonnig", "mild", "normal", "stark"},
            new String[]{"bewölkt", "heiß", "normal", "schwach"},
            new String[]{"bewölkt", "kalt", "normal", "stark"},
            new String[]{"regnerisch", "mild", "hoch", "stark"}};

        Datensatz[] daten = new Datensatz[testdaten.length];
        for( int i = 0; i < testdaten.length; i++ ) {
            daten[i] = new Datensatz();
            daten[i].set("vorhersage", testdaten[i][0]);
            daten[i].set("temperatur", testdaten[i][1]);
            daten[i].set("feuchtigkeit", testdaten[i][2]);
            daten[i].set("wind", testdaten[i][3]);
        }

        return daten;
    }

    public void testeTestdaten() {
        Datensatz[] testdaten = getTestdaten();
        for( int i = 0; i < testdaten.length; i++ ) {
            Datensatz d = testdaten[i];
            String play = testeDatensatz(d);
            System.out.printf("(%s, %s, %s, %s) = %s",
                d.get("vorhersage"), d.get("temperatur"),
                d.get("feuchtigkeit"), d.get("wind"),
                play);
            System.out.println();
        }
    }
}
