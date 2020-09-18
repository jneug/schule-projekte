/**
 * Enthält einen Entscheidungsbaum und kann {@link Dataset Datensätze} damit
 * klassifizieren.
 */
public class DecisionTreeBuilder {

    // Wurzel des Entscheidungsbaums
    private BinaryTree<DecisionNode> decisionTree;

    public DecisionTreeBuilder() {
        // Hier den Entscheidungsbaum aufbauen.
        // Vorbereiten der Klassifikationen:
        Classification classYes = new Classification("ja");
        Classification classNo = new Classification("nein");

        // Von der Wurzel zu den Blättern!
        decisionTree = new BinaryTree<DecisionNode>(
            new Decision("vorhersage", "regnerisch") // Inhalt der Wurzel / erste Entschiedung
        );

        decisionTree.getLeftTree().setContent(
            new Decision("feuchtigkeit", "hoch")
        );
        decisionTree.getLeftTree().getLeftTree().setContent(classNo);
        decisionTree.getLeftTree().getRightTree().setContent(classYes);

        decisionTree.getRightTree().setContent(
            new Decision("vorhersage", "sonnig")
        );
        decisionTree.getRightTree().getLeftTree().setContent(
            new Decision("wind", "stark")
        );
        decisionTree.getRightTree().getRightTree().setContent(classYes);

        decisionTree.getRightTree().getLeftTree().getLeftTree().setContent(classNo);
        decisionTree.getRightTree().getLeftTree().getRightTree().setContent(classYes);
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
     * Schickt einen Datensatz durch den Entscheidungsbaum und gibt die
     * Klasse als String zurück.
     *
     * @param pDataset Der zu testende Datensatz.
     * @return Die Klasse, der der Datensatz zugeordnet wurde.
     */
    public String testDataset( Dataset pDataset ) {
        BinaryTree<DecisionNode> node = decisionTree;

        String result = "";
        while( result.equals("") || result.equals("left")
            || result.equals("right") ) {
            DecisionNode e = node.getContent();
            result = e.decide(pDataset);

            if( result.equals("left") ) {
                // Im linken Teilbaum weiter
                node = node.getLeftTree();
            } else if( result.equals("right") ) {
                // Im rechten Teilbaum weiter
                node = node.getRightTree();
            }
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
