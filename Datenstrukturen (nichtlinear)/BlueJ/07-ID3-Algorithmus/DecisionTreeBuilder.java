public class DecisionTreeBuilder {

    public static final String TRAININGDATA = "titanic50.csv";
    //public static final String TRAININGDATA = "titanic300.csv";
    //public static final String TRAININGDATA = "titanic800.csv";
    public static final String TESTDATA = "titanicTest.csv";

    private int maxDepth;

    private double minInformationgain;

    private double minEntropy;

    private BinaryTree<DecisionNode> decisionTree;

    /**
     * Setzt die maximale Tiefe auf <code>3</code>.
     */
    public DecisionTreeBuilder() {
        //this(3, 0.25, 0.5);
        this(3);
    }

    public DecisionTreeBuilder( int pMaxDepth ) {
        maxDepth = pMaxDepth;
        minInformationgain = 0.0;
        minEntropy = 0.0;
    }

    /*
    public DecisionTreeBuilder( double pMinInformationgain ) {
        this(Integer.MAX_VALUE, pMinInformationgain, 0.0);
    }

    public DecisionTreeBuilder( double pMinInformationgain, double pMinEntropy ) {
        this(Integer.MAX_VALUE, pMinInformationgain, pMinEntropy);
    }

    public DecisionTreeBuilder( int pMaxDepth, double pMinInformationgain, double pMinEntropy ) {
        maxDepth = pMaxDepth;
        minInformationgain = pMinInformationgain;
        minEntropy = pMinEntropy;
    }
    */

    /**
     * Erstellt einen Entscheidungsbaum aus den {@link #TRAININGDATA Trainingsdaten}.
     */
    public void buildTree() {
        buildTree(TRAININGDATA);
    }

    /**
     * Liest die Trainingsdaten aus der Datei <var>pFilename</var> und erstellt
     * den Entscheidungsbaum.
     *
     * @param pFilename CSV-Datei im Projektordner
     */
    public void buildTree( String pFilename ) {
        List<Passenger> trainingdata = loadData(pFilename);
        System.out.println("Fertig: Daten geladen");
        decisionTree = buildTree(trainingdata);
        System.out.println("Fertig: Entschiedungsbaum erstellt");
    }

    /**
     * Gibt den Baum als Text auf der Kommandozeile aus.
     */
    public void printTree() {
        if( decisionTree == null ) {
            System.out.println("Fehler: Noch kein Baum erstellt!");
        } else {
            printTree(decisionTree, 0);
        }
    }

    /**
     *
     */
    /**
     * Gibt den Baum auf der {@link System#out Kommandozeile} aus.
     * <p>
     * Der Baum wird von der Wurzel rekursiv druchlaufen.
     * Zuerst wird der aktuelle Knoten ausgegeben, dann die
     * Knoten des linken Teilbaumes und schließlich die
     * Knoten des rechten Teilbaumes.
     * </p>
     *
     * @param pTree  Der aktuelle Teilbaum
     * @param pDepth Die aktuelle Tiefe
     */
    private void printTree( BinaryTree<DecisionNode> pTree, int pDepth ) {
        // Aktueller Inhalt der Wurzel des Teilbaumes
        DecisionNode k = pTree.getContent();

        /*aufg*
        // TODO: Ausgabe des Baumes implementieren

        // Mit k.toString() kann der Inhalt
        // des Knotens als String geholt und mit
        // System.out.println() ausgeben werden.
        // Zur besseren Übersicht kann pDepth benutzt werden,
        // um die Ausgaben einzurücken (z.B. " ".repeat(pDepth)).

        // Beispielhafter Aufruf für die Rekursion
        // im linken Teilbaum.
        // printTree(pTree.getLeftTree(), pDepth+1);
         *aufg*/
        //ml*
        String tabs = "";
        if( pDepth > 0 ) {
            tabs = new String(new char[pDepth]).replace("\0", "  ");
        }

        if( k != null ) {
            System.out.print(tabs);
            System.out.println(k.toString());
        }

        if( pTree.getLeftTree() != null ) {
            printTree(pTree.getLeftTree(), pDepth + 1);
        }
        if( pTree.getRightTree() != null ) {
            printTree(pTree.getRightTree(), pDepth + 1);
        }
        //*ml
    }

    /**
     * Klassifiziert die {@link #TESTDATA Testdaten} im Baum und
     * vergleicht das Ergebnis mit dem tatsächlich in den Testdaten
     * vorhandenen.
     */
    public void classifyTestdata() {
        classifyTestdata(TESTDATA);
    }

    /**
     * Klassifiziert die Testdaten in der angegebenen Datei im Baum und
     * vergleicht das Ergebnis mit dem tatsächlich in den Testdaten
     * vorhandenen.
     */
    public void classifyTestdata( String pFilename ) {
        if( decisionTree == null ) {
            System.out.println("Fehler: Noch kein Baum erstellt!");
        } else {
            List<Passenger> testdata = loadData(pFilename);
            double errors = 0, total = 0;

            testdata.toFirst();
            while( testdata.hasAccess() ) {
                Passenger p = testdata.getContent();
                String classification = classifyPassenger(p, decisionTree);
                String expected = "überlebt";
                if( p.survived == 0 ) {
                    expected = "verstorben";
                }

                System.out.printf("%s %s ", p.name, classification);

                if( !classification.equals(expected) ) {
                    System.out.println("(Fehler)");
                    errors++;
                } else {
                    System.out.println("(Korrekt)");
                }

                total++;
                testdata.next();
            }

            System.out.printf("\nTest fertig: %s von %s Fehler (%s%%)",
                errors, total, Math.round(errors / total * 100));
        }
    }

    /**
     * Klassifiziert einen Passagier im Baum <var>pTree</var> und gibt das Ergebnis zurück.
     *
     * @param pPassenger Der zu prüfende Passagier
     * @param pTree      Der (Teil-)Baum, in dem klassifiziert werden soll.
     * @return "verstorben" oder "überlebt"
     */
    private String classifyPassenger( Passenger pPassenger, BinaryTree<DecisionNode> pTree ) {
        String answer = pTree.getContent().decide(pPassenger);
        if( answer.equals("left") ) {
            return classifyPassenger(pPassenger, pTree.getLeftTree());
        } else if( answer.equals("right") ) {
            return classifyPassenger(pPassenger, pTree.getRightTree());
        } else {
            return answer;
        }
    }

    /**
     * Erstellt einen Entschedungsbaum zu den angegebenen Trainingsdaten mittels des
     * ID3-Algorithmus.
     *
     * <p>
     * Es wird immer ein binärer Entscheidungsbaum erstellt. Das bedeutet, Attribute
     * mit drei möglcihen Ausprägungen <code>w1</code>, <code>w2</code> und <code>w3</code>
     * werden in zwei Entschiedungen aufgeteilt, die zunächst unter <code>==w1</code> und
     * <code>!=w1</code> unterscheiden und dann in <code>==w2</code> und <code>==w3</code>.
     * </p>
     *
     * @param trainingdata Die Liste mit Passagieren, die für das Trining genutzt werden soll.
     * @return Der Entschiedungsbaum.
     * @see #loadData(String)
     */
    public BinaryTree<DecisionNode> buildTree( List<Passenger> trainingdata ) {
        return buildTree(trainingdata, 0);
    }

    private BinaryTree<DecisionNode> buildTree( List<Passenger> trainingdata, int pDepth ) {
        ID3 id3 = count(trainingdata);
        String bestAttribute = bestAttribute(id3);
        String[] attributeValues = Passenger.getValues(bestAttribute);

        if( id3.entropie() < minEntropy || bestAttribute.equals("") || pDepth >= maxDepth ) {
            BinaryTree<DecisionNode> klasse = new BinaryTree<>();
            if( id3.getRatio(1) >= id3.getRatio(0) ) {
                klasse.setContent(new Classification("überlebt"));
            } else {
                klasse.setContent(new Classification("verstorben"));
            }
            return klasse;
        } else {
            Decision e = new Decision(bestAttribute, attributeValues[0]);
            BinaryTree<DecisionNode> eBaum = new BinaryTree<>(e);

            eBaum.setLeftTree(buildTree(filterData(bestAttribute, attributeValues[0], trainingdata), pDepth + 1));
            if( attributeValues.length == 2 ) {
                eBaum.setRightTree(buildTree(filterData(bestAttribute, attributeValues[1], trainingdata), pDepth + 1));
            } else {
                BinaryTree<DecisionNode> tmpTree = new BinaryTree<>();
                tmpTree.setContent(new Decision(bestAttribute, attributeValues[1]));
                eBaum.setRightTree(tmpTree);

                tmpTree.setLeftTree(buildTree(filterData(bestAttribute, attributeValues[1], trainingdata), pDepth + 1));
                tmpTree.setRightTree(buildTree(filterData(bestAttribute, attributeValues[2], trainingdata), pDepth + 1));
            }

            return eBaum;
        }
    }

    private String bestAttribute( ID3 id3Counts ) {
        String[] attributes = Passenger.getAttribute();
        String bestAttribut = "";
        double maxInformation = 0;
        for( int i = 0; i < attributes.length; i++ ) {
            double ig = id3Counts.informationgain(attributes[i]);
            if( ig > maxInformation && ig > minInformationgain ) {
                bestAttribut = attributes[i];
                maxInformation = ig;
            }
        }

        return bestAttribut;
    }

    private ID3 count( List<Passenger> trainingdata ) {
        ID3 id3Counter = new ID3();

        trainingdata.toFirst();
        while( trainingdata.hasAccess() ) {
            id3Counter.count(trainingdata.getContent());
            trainingdata.next();
        }

        return id3Counter;
    }

    /**
     * Filtert die Liste von Passagieren nach einer Attribut/Wert Kombination.
     *
     * @param pAttribute Attributname, nach dem gefiltert wird
     * @param pValue Attributwert, nach dem gefiltert wird
     * @param trainingdata Zu filternde Datensätze
     * @return
     */
    private List<Passenger> filterData( String pAttribute, String pValue, List<Passenger> trainingdata ) {
        List<Passenger> filteredData = new List<Passenger>();

        trainingdata.toFirst();
        while( trainingdata.hasAccess() ) {
            Passenger p = trainingdata.getContent();
            if( p.getValue(pAttribute).equals(pValue) ) {
                filteredData.append(p);
            }
            trainingdata.next();
        }
        return filteredData;
    }

    /**
     * Lädt die angegebene Textdatei in eine Liste von {@link Passenger Passagieren}.
     * Die Datei muss eine durch ";" getrennte CSV-Datei sein, deren Spalten so aufgebaut sind:
     *
     * <pre>
     *     ID;class;survived;name,sex;age;sibsp;parch;embarked
     * </pre>
     * <p>
     * Die Attribute <code>class</code>, <code>survived</code>, <code>sibsp</code>
     * und <code>parch</code> werden als Integer geparsed.
     *
     * @param pDatei
     * @return
     */
    private List<Passenger> loadData( String pDatei ) {
        List<Passenger> data = new List<Passenger>();

        List<String> lines = FileSystem.getFileContents(pDatei);
        lines.toFirst();
        while( lines.hasAccess() ) {
            String[] lineData = lines.getContent().trim().split(";");
            if( lineData.length < 9 ) {
                System.err.println(lines.getContent());
            }

            Passenger p = new Passenger(lineData[3],
                lineData[5], lineData[4], lineData[8],
                Integer.valueOf(lineData[1].trim()),
                Integer.valueOf(lineData[6].trim()),
                Integer.valueOf(lineData[7].trim()),
                Integer.valueOf(lineData[2].trim()));
            data.append(p);

            lines.next();
        }

        return data;
    }

    /**
     * Lädt die {@link #TRAININGDATA Trainingsdaten} und berechnet die
     * Kenngrößen des ID3-Algorithmus: Entropien und Inforamtionsgewinne.
     *
     * @see ID3
     */
    public void calculateEntropy() {
        calculateEntropy(TRAININGDATA);
    }

    /**
     * Lädt die Daten aus der angegebenen Datei und berechnet die
     * Kenngrößen des ID3-Algorithmus: Entropien und Inforamtionsgewinne.
     *
     * @see ID3
     * @see #loadData(String)
     */
    public void calculateEntropy( String pFilename ) {
        List<Passenger> trainingdata = loadData(pFilename);
        System.out.println("Fertig: Daten geladen");
        ID3 id3 = count(trainingdata);

        System.out.printf("E_gesamt = %s\n", id3.entropie());
        String[] attrs = Passenger.getAttribute();
        for( int i = 0; i < attrs.length; i++ ) {
            String attr = attrs[i];
            String[] values = Passenger.getValues(attr);
            for( int j = 0; j < values.length; j++ ) {
                String value = values[j];
                System.out.printf("E_%s(%s) = %s\n", attr, value, id3.entropie(attr, value));
            }
        }

        for( int i = 0; i < attrs.length; i++ ) {
            String attr = attrs[i];
            System.out.printf("I_%s = %s\n", attr, id3.informationgain(attr));
        }
    }

}
