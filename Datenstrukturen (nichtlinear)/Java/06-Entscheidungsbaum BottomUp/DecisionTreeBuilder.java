/**
 * Enthält einen Entscheidungsbaum und kann {@link Dataset Datensätze} damit
 * klassifizieren.
 */
public class DecisionTreeBuilder {

    // Wurzel des Entscheidungsbaums
    private BinaryTree<DecisionNode> decisionTree;

    public DecisionTreeBuilder() {
        // Vorbereiten der Klassifikationen (Blätter)
        BinaryTree<DecisionNode> classYes = new BinaryTree<>(
            new Classification("ja")
        );
        BinaryTree<DecisionNode> classNo = new BinaryTree<>(
            new Classification("nein")
        );


        /*aufg*
        // TODO: Hier den Entscheidungsbaum aufbauen.
        //
        // Der Baum wird von den Blättern nach "oben" zur Wurzel aufgebaut.
        // z.B.
        // BinaryTree<DecisionNode> wind = new BinaryTree<>(
        //     new Decision("wind", "stark"),     // Entscheide Merkmal "wind", gehe links bei "stark"
        //     classNo,                           // Linker Teilbaum
        //     classYes                           // rechter Teilbaum
        // );
        // usw...
        *aufg*/
        //ml*
        BinaryTree<DecisionNode> wind = new BinaryTree<>(
            new Decision("wind", "stark"),
            classNo,
            classYes
        );
        BinaryTree<DecisionNode> vorhersage = new BinaryTree<>(
            new Decision("vorhersage", "sonnig"),
            wind,
            classYes
        );
        BinaryTree<DecisionNode> feuchtigkeit = new BinaryTree<>(
            new Decision("feuchtigkeit", "hoch"),
            classNo,
            classYes
        );
        //*ml
        // Die Wurzel zuletzt
        decisionTree = new BinaryTree<>(
            new Decision("vorhersage", "regnerisch"),
            feuchtigkeit,
            vorhersage
        );
    }

    /**
     * Schickt die {@link #getTestdata() Testdaten} durch den Entscheidungsbaum
     * und gibt die Entscheidungen auf der Kommandozeile aus.
     *
     * @see #testDataset(Dataset)
     */
    public void testTestdata() {
        Dataset[] testdata = getTestdata();
        for( int i = 0; i < testdata.length; i++ ) {
            Dataset d = testdata[i];
            String result = testDataset(d);
            System.out.printf("(%s, %s, %s, %s) = %s",
                d.get("vorhersage"), d.get("temperatur"),
                d.get("feuchtigkeit"), d.get("wind"),
                result);
            System.out.println();
        }
    }

    /**
     * Schickt einen Datensatz durch den Entscheidungsbaum und gibt die Klasse
     * als String zurück.
     *
     * @param pDataset Der zu testende Datensatz.
     * @return Die Klasse, der der Datensatz zugeordnet wurde.
     */
    public String testDataset( Dataset pDataset ) {
        BinaryTree<DecisionNode> node = decisionTree;

        String result = "";
        while( result.equals("") || result.equals("left")
            || result.equals("right") ) {
		    /*aufg* 1
            // TODO: Impementiere den Durchlauf durch den Entschiedungsbaum,
            // indem bei jedem inneren Knoten die Entscheidung getroffen wird,
            // ob links oder rechts weitergemacht wird.
            *aufg*/
		    /*aufg* 2
            // TODO: Impementiere den Durchlauf durch den Entschiedungsbaum,
            // indem bei jedem inneren Knoten die Entscheidung getroffen wird,
            // ob links oder rechts weitergemacht wird.

            DecisionNode e = null;
			result = "???";

			if( result.equals("left") ) {
                // ...
			} else if( result.equals("right") ) {
				// ...
			}
            *aufg*/
            //ml*
            DecisionNode e = node.getContent();
            result = e.decide(pDataset);

            if( result.equals("left") ) {
                // Im linken Teilbaum weiter
                node = node.getLeftTree();
            } else if( result.equals("right") ) {
                // Im rechten Teilbaum weiter
                node = node.getRightTree();
            }
            //*ml
        }
        return result;
    }

    /**
     * Erstellt ein Array mit Test-Datensätzen für den Entscheidungsbaum.
     *
     * @return
     */
    public Dataset[] getTestdata() {
        String[][] testdata = {
            {"sonnig", "heiß", "hoch", "schwach"},
            {"sonnig", "heiß", "hoch", "stark"},
            {"bewölkt", "heiß", "hoch", "schwach"},
            {"regnerisch", "mild", "hoch", "schwach"},
            {"regnerisch", "kalt", "normal", "schwach"},
            {"regnerisch", "kalt", "normal", "stark"},
            {"bewölkt", "mild", "hoch", "stark"},
            {"sonnig", "mild", "hoch", "schwach"},
            {"sonnig", "kalt", "normal", "schwach"},
            {"regnerisch", "mild", "normal", "schwach"},
            {"sonnig", "mild", "normal", "stark"},
            {"bewölkt", "heiß", "normal", "schwach"},
            {"bewölkt", "kalt", "normal", "stark"},
            {"regnerisch", "mild", "hoch", "stark"}
        };

        Dataset[] data = new Dataset[testdata.length];
        for( int i = 0; i < testdata.length; i++ ) {
            data[i] = new Dataset();
            data[i].set("vorhersage", testdata[i][0]);
            data[i].set("temperatur", testdata[i][1]);
            data[i].set("feuchtigkeit", testdata[i][2]);
            data[i].set("wind", testdata[i][3]);
        }

        return data;
    }

}
