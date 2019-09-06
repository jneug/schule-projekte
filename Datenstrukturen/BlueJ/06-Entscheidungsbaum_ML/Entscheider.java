
public class Entscheider
{
    private BinaryTree<Entscheidung> baum;
    
    public Entscheider()
    {
        BinaryTree<Entscheidung> blattJa = new BinaryTree<Entscheidung>();
        blattJa.setContent(new EntscheidungJa());
        BinaryTree<Entscheidung> blattNein = new BinaryTree<Entscheidung>();
        blattNein.setContent(new EntscheidungNein());
        
        BinaryTree<Entscheidung> knotenWind = new BinaryTree<Entscheidung>();
        knotenWind.setContent(new EntscheidungWind());
        knotenWind.setLeftTree(blattNein);
        knotenWind.setRightTree(blattJa);
        
        BinaryTree<Entscheidung> knotenFeuchte = new BinaryTree<Entscheidung>();
        knotenFeuchte.setContent(new EntscheidungFeuchtigkeit());
        knotenFeuchte.setLeftTree(blattNein);
        knotenFeuchte.setRightTree(blattJa);
        
        BinaryTree<Entscheidung> knotenVorher2 = new BinaryTree<Entscheidung>();
        knotenVorher2.setContent(new EntscheidungVorhersage2());
        knotenVorher2.setLeftTree(knotenWind);
        knotenVorher2.setRightTree(blattJa);
        
        baum = new BinaryTree<Entscheidung>();
        baum.setContent(new EntscheidungVorhersage1());
        baum.setLeftTree(knotenFeuchte);
        baum.setRightTree(knotenVorher2);
    }

    
    public String testeDatensatz(Datensatz d)
    {
        // Bei der Wurzel anfangen
        BinaryTree<Entscheidung> knoten = baum;

        String antwort = "";
        while( antwort.equals("") || antwort.equals("links")
                || antwort.equals("rechts") ) {
            Entscheidung e = knoten.getContent();
            antwort = e.entscheide(d);
            
            if( antwort.equals("links") ) {
                knoten = knoten.getLeftTree();
            } else if( antwort.equals("rechts") ) {
                knoten = knoten.getRightTree();
            }
        }
        return antwort;
    }
    
    public Datensatz[] getTestdaten() {
        int i = 0;
        Datensatz[] testdaten = new Datensatz[14];       
        testdaten[i++] = new Datensatz("sonnig", "heiß", "hoch", "schwach");
        testdaten[i++] = new Datensatz("sonnig", "heiß", "hoch", "stark");
        testdaten[i++] = new Datensatz("bewölkt", "heiß", "hoch", "schwach");
        testdaten[i++] = new Datensatz("regnerisch", "mild", "hoch", "schwach");
        testdaten[i++] = new Datensatz("regnerisch", "kalt", "normal", "schwach");
        testdaten[i++] = new Datensatz("regnerisch", "kalt", "normal", "stark");
        testdaten[i++] = new Datensatz("bewölkt", "mild", "hoch", "stark");
        testdaten[i++] = new Datensatz("sonnig", "mild", "hoch", "schwach");
        testdaten[i++] = new Datensatz("sonnig", "kalt", "normal", "schwach");
        testdaten[i++] = new Datensatz("regnerisch", "mild", "normal", "schwach");
        testdaten[i++] = new Datensatz("sonnig", "mild", "normal", "stark");
        testdaten[i++] = new Datensatz("bewölkt", "heiß", "normal", "schwach");
        testdaten[i++] = new Datensatz("bewölkt", "kalt", "normal", "stark");
        testdaten[i++] = new Datensatz("regnerisch", "mild", "hoch", "stark");
        return testdaten;
    }
    
    public void testeTestdaten() {
        Datensatz[] testdaten = getTestdaten();
        for( int i = 0; i < testdaten.length; i++ ) {
            Datensatz d = testdaten[i];
            String play = testeDatensatz(d);
            System.out.printf("(%s, %s, %s, %s) = %s", d.vorhersage, d.temperatur, d.feuchtigkeit, d.wind, play);
            System.out.println();
        }
    }

}
