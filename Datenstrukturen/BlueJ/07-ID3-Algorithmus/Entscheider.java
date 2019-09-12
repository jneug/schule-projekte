

public class Entscheider {

    public static final String TRAININGSDATEN = "titanic50.csv";
    //public static final String TRAININGSDATEN = "titanic300.csv";
    //public static final String TRAININGSDATEN = "titanic800.csv";
    public static final String TESTDATEN = "titanicTest.csv";

    private int maximaleTiefe;

    private double minimalerInformationsgewinn;

    private BinaryTree<Knoten> baum;

    public Entscheider() {
        this(3, 0.25);
    }

    public Entscheider( int pMaximaleTiefe ) {
        this(pMaximaleTiefe, 0.0);
    }

    public Entscheider( double pMinimalerInformationsgewinn ) {
    		this(Integer.MAX_VALUE, pMinimalerInformationsgewinn);
    }

    public Entscheider( int pMaximaleTiefe, double pMinimalerInformationsgewinn ) {
    		maximaleTiefe = pMaximaleTiefe;
    		minimalerInformationsgewinn = pMinimalerInformationsgewinn;
    }

    public void ladeDatenUndErstelleBaum() {
        List<Passagier> trainingsdaten = ladeTrainingsdaten();
        System.out.println("Fertig: Daten geladen");
        baum = erstelleBaum(trainingsdaten);
        System.out.println("Fertig: Entschiedungsbaum erstellt");
    }

    public void baumAusgeben() {
        if( baum == null ) {
            System.out.println("Fehler: Noch kein Baum erstellt!");
        } else {
            baumAusgeben(baum, 0);
        }
    }

    /*
     * Gibt den Baum auf der Konsole aus.
     * Der Baum wird von der Wurzel rekursiv druchlaufen.
     * Zuerst wird der aktuelle Knoten ausgegeben, dann die
     * Knoten des linken Teilbaumes und schließlich die
     * Knoten des rechten Teilbaumes.
     */
    private void baumAusgeben( BinaryTree<Knoten> pBaum, int pTiefe ) {
        Knoten k = pBaum.getContent();

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

        //Knoten k = pBaum.getContent();
        if( k != null ) {
            System.out.print(tabs);
            System.out.println(k.toString());
        }

        if( pBaum.getLeftTree() != null ) {
            baumAusgeben(pBaum.getLeftTree(), pTiefe+1);
        }
        if( pBaum.getRightTree() != null ) {
            baumAusgeben(pBaum.getRightTree(), pTiefe+1);
        }
    }

    public void pruefeTestdaten() {
        if( baum == null ) {
            System.out.println("Fehler: Noch kein Baum erstellt!");
        } else {
            List<Passagier> testdaten = ladeTestdaten();
            int fehler = 0, gesamt = 0;

            testdaten.toFirst();
            while( testdaten.hasAccess() ) {
                Passagier p = testdaten.getContent();
                String klasse = pruefePassagier(p);
                String erwartet = "überlebt";
                if( p.survived == 0 ) {
                    erwartet = "verstorben";
                }

                System.out.printf("%s %s ", p.name, klasse);

                if( !klasse.equals(erwartet) ) {
                    System.out.println("(Fehler)");
                    fehler++;
                } else {
                    System.out.println("(Korrekt)");
                }

                gesamt++;
                testdaten.next();
            }

            System.out.printf("\nTest fertig: %s von %s Fehler (%s%%)",
                    fehler, gesamt, Math.round(fehler/gesamt*100));
        }
    }

    private String pruefePassagier( Passagier pPassagier ) {
        if( baum == null ) {
            System.out.println("Fehler: Noch kein Baum erstellt!");
            return "- FEHLER -";
        }

        // Bei der Wurzel anfangen
        BinaryTree<Knoten> wurzel = baum;

        String antwort = "";
        while( antwort.equals("") || antwort.equals("links")
                || antwort.equals("rechts") ) {
            Knoten e = wurzel.getContent();
            antwort = e.entscheide(pPassagier);

            if( antwort.equals("links") ) {
                wurzel = wurzel.getLeftTree();
            } else if( antwort.equals("rechts") ) {
                wurzel = wurzel.getRightTree();
            }
        }
        return antwort;
    }

    public BinaryTree<Knoten> erstelleBaum( List<Passagier> trainingsdaten ) {
        return erstelleBaum(trainingsdaten, 0);
    }

    private BinaryTree<Knoten> erstelleBaum( List<Passagier> trainingsdaten , int pTiefe ) {
        String bestesAttribut = bestesAttribut(trainingsdaten);
        String[] werte = Passagier.getWerte(bestesAttribut);

        if( bestesAttribut.equals("") || pTiefe >= maximaleTiefe ) {
            ID3 anteile = zaehlen(trainingsdaten);

            BinaryTree<Knoten> klasse = new BinaryTree<Knoten>();
            if( anteile.anteil(1) >= anteile.anteil(0) ) {
                klasse.setContent(new Klassifikation("überlebt"));
            } else {
                klasse.setContent(new Klassifikation("verstorben"));
            }
            return klasse;
        } else {
            Entscheidung e = new Entscheidung(bestesAttribut, werte[0]);
            BinaryTree<Knoten> eBaum = new BinaryTree<Knoten>(e);

            eBaum.setLeftTree( erstelleBaum( filtereDaten(bestesAttribut, werte[0], trainingsdaten), pTiefe+1 ) );
            if( werte.length == 2 ) {
                eBaum.setRightTree( erstelleBaum( filtereDaten(bestesAttribut, werte[1], trainingsdaten), pTiefe+1 ) );
            } else {
                BinaryTree<Knoten> tmpTree = new BinaryTree<Knoten>();
                tmpTree.setContent(new Entscheidung(bestesAttribut, werte[1]));
                eBaum.setRightTree( tmpTree );

                tmpTree.setLeftTree( erstelleBaum( filtereDaten(bestesAttribut, werte[1], trainingsdaten), pTiefe+1 ) );
                tmpTree.setRightTree( erstelleBaum( filtereDaten(bestesAttribut, werte[2], trainingsdaten), pTiefe+1 ) );
            }

            return eBaum;
        }
    }

    private String bestesAttribut( List<Passagier> trainingsdaten ) {
        ID3 anteile = zaehlen(trainingsdaten);

        String[] attribute = Passagier.getAttribute();
        String bestesAttribut = "";
        double maxInformation = 0;
        for( int i = 0; i < attribute.length; i++ ) {
            double ig = anteile.informationsgewinn(attribute[i]);
            if( ig > maxInformation && ig > minimalerInformationsgewinn ) {
                bestesAttribut = attribute[i];
                maxInformation = ig;
            }
        }

        return bestesAttribut;
    }

    private ID3 zaehlen( List<Passagier> trainingsdaten  ) {
        ID3 zaehler = new ID3();

        trainingsdaten.toFirst();
        while( trainingsdaten.hasAccess() ) {
            zaehler.zaehlen(trainingsdaten.getContent());
            trainingsdaten.next();
        }

        return zaehler;
    }

    /**
     * Filtert die Liste von Passagieren nach einer Attribut/Wert Kombination.
     * @param pAttribut
     * @param pWert
     * @param trainingsdaten
     * @return
     */
    private List<Passagier> filtereDaten( String pAttribut, String pWert, List<Passagier> trainingsdaten ) {
        List<Passagier> neueTestdaten = new List<Passagier>();

        trainingsdaten.toFirst();
        while( trainingsdaten.hasAccess() ) {
            Passagier p = trainingsdaten.getContent();
            if( p.get(pAttribut).equals(pWert) ) {
                neueTestdaten.append(p);
            }
            trainingsdaten.next();
        }
        return neueTestdaten;
    }

    private List<Passagier> ladeTrainingsdaten() {
        return ladeDaten(TRAININGSDATEN);
    }

    private List<Passagier> ladeTestdaten() {
        return ladeDaten(TESTDATEN);
    }

    private List<Passagier> ladeDaten( String pDatei ) {
        List<Passagier> daten = new List<Passagier>();

        List<String> lines = FileSystem.readResource(pDatei);
        lines.toFirst();
        while( lines.hasAccess() ) {
            String[] lineData = lines.getContent().trim().split(";");
            if(lineData.length < 9) {
                System.out.println(lines.getContent());
            }

            Passagier p = new Passagier( lineData[3],
                    lineData[5], lineData[4], lineData[8],
                    Integer.valueOf(lineData[1].trim()),
                    Integer.valueOf(lineData[6].trim()),
                    Integer.valueOf(lineData[7].trim()),
                    Integer.valueOf(lineData[2].trim()) );
            daten.append(p);

            lines.next();
        }

        return daten;
    }

    public void ladeDatenUndZaehle() {
        List<Passagier> trainingsdaten = ladeTrainingsdaten();
        System.out.println("Fertig: Daten geladen");
        ID3 anteile = zaehlen(trainingsdaten);

        System.out.printf("E_gesamt = %s\n", anteile.entropie());
        String[] attrs = Passagier.getAttribute();
        for( int i = 0; i < attrs.length; i++ ) {
            String attr = attrs[i];
            String[] werte = Passagier.getWerte(attr);
            for( int j = 0; j < werte.length; j++ ) {
                String wert = werte[j];
                System.out.printf("E_%s(%s) = %s\n", attr, wert, anteile.entropie(attr, wert));
            }
        }

        for( int i = 0; i < attrs.length; i++ ) {
            String attr = attrs[i];
            System.out.printf("I_%s = %s\n", attr, anteile.informationsgewinn(attr));
        }
    }

}
