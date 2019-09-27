
public class DecisionTreeBuilder {

    public static final String TRAININGDATA = "titanic50.csv";
    //public static final String TRAININGDATA = "titanic300.csv";
    //public static final String TRAININGDATA = "titanic800.csv";
    public static final String TESTDATA = "titanicTest.csv";

    private int maxDepth;

    private double minInformationgain;

    private double minEntropie;

    private BinaryTree<DecisionNode> tree;

    public DecisionTreeBuilder() {
        this(3, 0.25, 0.5);
    }

    public DecisionTreeBuilder( int pMaxDepth ) {
        this(pMaxDepth, 0.0, 0.0);
    }

    public DecisionTreeBuilder( double pMinInformationgain ) {
        this(Integer.MAX_VALUE, pMinInformationgain, 0.0);
    }

    public DecisionTreeBuilder( double pMinInformationgain, double pMinEntropie ) {
        this(Integer.MAX_VALUE, pMinInformationgain, pMinEntropie);
    }

    public DecisionTreeBuilder( int pMaxDepth, double pMinInformationgain, double pMinEntropie ) {
        maxDepth = pMaxDepth;
        minInformationgain = pMinInformationgain;
        minEntropie = pMinEntropie;
    }

    public void buildTree() {
        buildTree(TRAININGDATA);
    }

    public void buildTree( String pFilename ) {
        List<Passenger> trainingsdaten = loadData(pFilename);
        System.out.println("Fertig: Daten geladen");
        tree = buildTree(trainingsdaten);
        System.out.println("Fertig: Entschiedungsbaum erstellt");
    }

    /**
     * Gibt den Baum als Text auf der Kommandozeile aus.
     */
    public void printTree() {
        if( tree == null ) {
            System.out.println("Fehler: Noch kein Baum erstellt!");
        } else {
            printTree(tree, 0);
        }
    }

    /**
     * Gibt den Baum auf der {@link System#out Kommandozeile} aus.
     *
     * <p>
     * Der Baum wird von der Wurzel rekursiv druchlaufen.
     * Zuerst wird der aktuelle Knoten ausgegeben, dann die
     * Knoten des linken Teilbaumes und schließlich die
     * Knoten des rechten Teilbaumes.
     * </p>
     */
    private void printTree( BinaryTree<DecisionNode> pBaum, int pTiefe ) {
        DecisionNode k = pBaum.getContent();

        // Sie können sich mit k.toString() den Inhalt
        // des Knotens als String holen und mit
        // System.out.println() ausgeben.

        // Beispielhafter Aufruf für die Rekursion
        // im linken Teilbaum.
        // baumAusgeben(pBaum.getLeftTree(), pTiefe+1);

        String tabs = "";
        if( pTiefe > 0 ) {
            tabs = new String(new char[pTiefe]).replace("\0", "  ");
        }

        // Knoten k = pBaum.getContent();
        if( k != null ) {
            System.out.print(tabs);
            System.out.println(k.toString());
        }

        if( pBaum.getLeftTree() != null ) {
            printTree(pBaum.getLeftTree(), pTiefe + 1);
        }
        if( pBaum.getRightTree() != null ) {
            printTree(pBaum.getRightTree(), pTiefe + 1);
        }
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
    public void classifyTestdata( String pDatei ) {
        if( tree == null ) {
            System.out.println("Fehler: Noch kein Baum erstellt!");
        } else {
            List<Passenger> testdata = loadData(pDatei);
            int errors = 0, total = 0;

            testdata.toFirst();
            while( testdata.hasAccess() ) {
                Passenger p = testdata.getContent();
                String classification = classifyPassenger(p, tree);
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
     * @param pPassenger Der zu prüfende Passagier
     * @param pTree Der (Teil-)Baum, in dem klassifiziert werden soll.
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
     *     Es wird immer ein binärer Entscheidungsbaum erstellt. Das bedeutet, Attribute
     *     mit drei möglcihen Ausprägungen <code>w1</code>, <code>w2</code> und <code>w3</code>
     *     werden in zwei Entschiedungen aufgeteilt, die zunächst unter <code>==w1</code> und
     *     <code>!=w1</code> unterscheiden und dann in <code>==w2</code> und <code>==w3</code>.
     * </p>
     * @param trainingsdaten Die Liste mit Passagieren, die für das Trining genutzt werden soll.
     * @return Der Entschiedungsbaum.
     * @see #loadData(String)
     */
    public BinaryTree<DecisionNode> buildTree( List<Passenger> trainingsdaten ) {
        return buildTree(trainingsdaten, 0);
    }

    private BinaryTree<DecisionNode> buildTree( List<Passenger> trainingsdaten, int pTiefe ) {
        ID3 id3 = zaehlen(trainingsdaten);
        String bestesAttribut = bestesAttribut(id3);
        String[] werte = Passenger.getValues(bestesAttribut);

        if( id3.entropie() < minEntropie || bestesAttribut.equals("") || pTiefe >= maxDepth ) {
            BinaryTree<DecisionNode> klasse = new BinaryTree<DecisionNode>();
            if( id3.getRatio(1) >= id3.getRatio(0) ) {
                klasse.setContent(new Classification("überlebt"));
            } else {
                klasse.setContent(new Classification("verstorben"));
            }
            return klasse;
        } else {
            Decision e = new Decision(bestesAttribut, werte[0]);
            BinaryTree<DecisionNode> eBaum = new BinaryTree<DecisionNode>(e);

            eBaum.setLeftTree(buildTree(filtereDaten(bestesAttribut, werte[0], trainingsdaten), pTiefe + 1));
            if( werte.length == 2 ) {
                eBaum.setRightTree(buildTree(filtereDaten(bestesAttribut, werte[1], trainingsdaten), pTiefe + 1));
            } else {
                BinaryTree<DecisionNode> tmpTree = new BinaryTree<DecisionNode>();
                tmpTree.setContent(new Decision(bestesAttribut, werte[1]));
                eBaum.setRightTree(tmpTree);

                tmpTree.setLeftTree(buildTree(filtereDaten(bestesAttribut, werte[1], trainingsdaten), pTiefe + 1));
                tmpTree.setRightTree(buildTree(filtereDaten(bestesAttribut, werte[2], trainingsdaten), pTiefe + 1));
            }

            return eBaum;
        }
    }

    private String bestesAttribut( ID3 anteile ) {
        String[] attribute = Passenger.getAttribute();
        String bestesAttribut = "";
        double maxInformation = 0;
        for( int i = 0; i < attribute.length; i++ ) {
            double ig = anteile.informationgain(attribute[i]);
            if( ig > maxInformation && ig > minInformationgain ) {
                bestesAttribut = attribute[i];
                maxInformation = ig;
            }
        }

        return bestesAttribut;
    }

    private ID3 zaehlen( List<Passenger> trainingsdaten ) {
        ID3 zaehler = new ID3();

        trainingsdaten.toFirst();
        while( trainingsdaten.hasAccess() ) {
            zaehler.count(trainingsdaten.getContent());
            trainingsdaten.next();
        }

        return zaehler;
    }

    /**
     * Filtert die Liste von Passagieren nach einer Attribut/Wert Kombination.
     *
     * @param pAttribut
     * @param pWert
     * @param trainingsdaten
     * @return
     */
    private List<Passenger> filtereDaten( String pAttribut, String pWert, List<Passenger> trainingsdaten ) {
        List<Passenger> neueTestdaten = new List<Passenger>();

        trainingsdaten.toFirst();
        while( trainingsdaten.hasAccess() ) {
            Passenger p = trainingsdaten.getContent();
            if( p.getValue(pAttribut).equals(pWert) ) {
                neueTestdaten.append(p);
            }
            trainingsdaten.next();
        }
        return neueTestdaten;
    }

    /**
     * Lädt die angegebene Textdatei in eine Liste von {@link Passenger Passagieren}.
     * Die Datei muss eine durcg ";" getrennte CSV-Datei sein, deren Spalten so aufgebaut sind:
     *
     * <pre>
     *     ID;class;survived;name,sex;age;sibsp;parch;embarked
     * </pre>
     *
     * Die Attribute <code>class</code>, <code>survived</code>, <code>sibsp</code>
     * und <code>parch</code> werden als Integer geparsed.
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
                System.out.println(lines.getContent());
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
     * @see ID3
     */
    public void calculateEntropie() {
        calculateEntropie(TRAININGDATA);
    }

    /**
     * Lädt die Daten aus der angegebenen Datei und berechnet die
     * Kenngrößen des ID3-Algorithmus: Entropien und Inforamtionsgewinne.
     * @see ID3
     * @see #loadData(String)
     */
    public void calculateEntropie( String pFile ) {
        List<Passenger> trainingdata = loadData(pFile);
        System.out.println("Fertig: Daten geladen");
        ID3 id3 = zaehlen(trainingdata);

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
