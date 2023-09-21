

/**
 * Implementiere den Stammbaum von Seite 145
 * im Buch.
 */
public class Morsecode {
    
    private BinaryTree<Character> root;
    
    public Morsecode() {
        
        BinaryTree<Character> b = new BinaryTree<>('B');
        BinaryTree<Character> c = new BinaryTree<>('C');
        BinaryTree<Character> j = new BinaryTree<>('J');
        BinaryTree<Character> p = new BinaryTree<>('P');
        BinaryTree<Character> o = new BinaryTree<>('O');
        BinaryTree<Character> l = new BinaryTree<>('L');
        BinaryTree<Character> f = new BinaryTree<>('F');
        BinaryTree<Character> h = new BinaryTree<>('H');
        BinaryTree<Character> v = new BinaryTree<>('V');
        BinaryTree<Character> x = new BinaryTree<>('X');
        BinaryTree<Character> y = new BinaryTree<>('Y');
        BinaryTree<Character> z = new BinaryTree<>('Z');
        BinaryTree<Character> q = new BinaryTree<>('Q');
        
        BinaryTree<Character> s = new BinaryTree<>('S',h,v);
        BinaryTree<Character> u = new BinaryTree<>('U',f,null);
        BinaryTree<Character> r = new BinaryTree<>('R',l,null);
        BinaryTree<Character> w = new BinaryTree<>('W',p,j);
        BinaryTree<Character> d = new BinaryTree<>('D',b,x);
        BinaryTree<Character> k = new BinaryTree<>('K',c,y);
        BinaryTree<Character> g = new BinaryTree<>('G',z,q);
        
        BinaryTree<Character> i = new BinaryTree<>('I',s,u);
        BinaryTree<Character> a = new BinaryTree<>('A',r,w);  
        BinaryTree<Character> n = new BinaryTree<>('N',d,k);
        BinaryTree<Character> m = new BinaryTree<>('M',g,o);
        
        BinaryTree<Character> e = new BinaryTree<>('E',i,a);
        BinaryTree<Character> t = new BinaryTree<>('T',n,m);
        
        root = new BinaryTree<>(' ',e,t);          
        
    }
    
    public void printTree() {
        Trees.printPretty(root);
    }
    
    public String morsecodeDecodieren( String pCode ) {
        String[] buchstaben = pCode.split(" ");
        String decoded = "";
        for( String b: buchstaben ) {
            decoded += buchstabenDecodieren(b);
        }
        return decoded;
    }
    
    public char buchstabenDecodieren( String pCode ) {
        return buchstabenDecodieren(root, pCode);
    }
    
    public char buchstabenDecodieren( BinaryTree<Character> pRoot, String pCode ) {
        if( pCode.isEmpty() ) {
            return pRoot.getContent();
        }
        if( pCode.charAt(0) == '.' ) {
            return buchstabenDecodieren(pRoot.getLeftTree(), pCode.substring(1));
        } else {
            return buchstabenDecodieren(pRoot.getRightTree(), pCode.substring(1));
        }
    }
    
    public String wortCodieren( String pWort ) {
        pWort = pWort.toUpperCase();
        String[] code = new String[pWort.length()];
        for( int i = 0; i < pWort.length(); i++ ) {
            code[i] = buchstabenCodieren(pWort.charAt(i));
        }
        return String.join(" ", code);
    }
    
    public String buchstabenCodieren( char pChar ) {
        return buchstabenCodieren(root, "", pChar);
    }
    
    /**
     * Traversierung des Baumes, bis der Buchstabe gefunden wurde.
     * pRoot ist die Wurzel des aktuellen Teilbaums, pCode enthält den Pfad
     * von der Wurzel bis zu pRoot als Morsecode ausgedrückt und pChar
     * ist der gesuchte Buchstabe.
     */
    public String buchstabenCodieren( BinaryTree<Character> pRoot, String pCode, char pChar ) {
        if( pRoot.getContent().charValue() == pChar ) {
            // Buchstaben im Baum gefunden
            // gib den Pfad bis hierher zurück
            return pCode;
        }
        // Buchstaben noch nicht gefunden, traversiere den Baum weiter
        // Falls nichts gefunden wird, gib einen leeren String zurück
        String code = "";
        // Linken TB durchsuchen
        if( !pRoot.getLeftTree().isEmpty() ) {
            // Rekurion im linken TB, füge "." an den bisherigen Pfad an
            code = buchstabenCodieren(pRoot.getLeftTree(), pCode+".", pChar);
        }
        // Rechten TB durchsuchen (wenn im linken TB noch nichts gefunden,
        // also wenn code == "").
        if( code.isEmpty() && !pRoot.getRightTree().isEmpty() ) {
            // Rekurion im linken TB, füge "-" an den bisherigen Pfad an
            code = buchstabenCodieren(pRoot.getRightTree(), pCode+"-", pChar);
        }
        return code;
    }
    
}
