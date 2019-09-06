
public class Entscheider
{
    private BinaryTree<Entscheidung> baum;
    
    public Entscheider()
    {
        /*
         * Hier den Entscheidungsbaum aufbauen
         * von den Blättern zur Wurzel!
         */
        // z.B.
        BinaryTree<Entscheidung> b1 = new BinaryTree<Entscheidung>();
        b1.setContent(new EntscheidungJa());
        
        BinaryTree<Entscheidung> b2 = new BinaryTree<Entscheidung>();
        b2.setContent(new EntscheidungTemperatur());
        b2.setLeftTree(b1);
        
        // usw ...
        
        // bis zur Wurzel, z.B.
        baum = new BinaryTree<Entscheidung>();
        baum.setContent(new EntscheidungVorhersage1());
        baum.setRightTree(b2);
    }

    
    public String testeDatensatz(Datensatz d)
    {
        // Bei der Wurzel anfangen
        BinaryTree<Entscheidung> knoten = baum;

        String antwort = "";
        while( !antwort.equals("") && !antwort.equals("links")
                && !antwort.equals("rechts") ) {
            Entscheidung e = knoten.getContent();
            antwort = e.entscheide(d);
            
            if( antwort.equals("links") ) {
                // Im linken Teilbaum weiter
                // knoten = ...
            } else if( antwort.equals("rechts") ) {
                // Im linken Teilbaum weiter
                // knoten = ...
            }
        }
        return antwort;
    }
}
