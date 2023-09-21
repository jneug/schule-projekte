

/**
 * Implementiere den Stammbaum von Seite 145
 * im Buch.
 */
public class Morsecode {
    
    private BinaryTree<Character> root;
    
    public Morsecode() {
        String[] alph = {
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."
        };
        
        root = new BinaryTree<>('#');        
        for( int j = 0; j < alph.length; j++ ) {
            String a = alph[j];
            
            BinaryTree<Character> tree = root;
            for( int i = 0; i < a.length(); i++ ) {
                char c = a.charAt(i);
                if( c == '.' ) {
                    if( tree.getLeftTree().isEmpty() ) {
                        tree.setLeftTree(new BinaryTree<>('#'));
                    }
                    tree = tree.getLeftTree();
                } else if( c == '-' ) {
                    if( tree.getRightTree().isEmpty() ) {
                        tree.setRightTree(new BinaryTree<>('#'));
                    }
                    tree = tree.getRightTree();
                }
            }
            char c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(j);
            tree.setContent(c);
        }
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
    
    public String buchstabenCodieren( BinaryTree<Character> pRoot, String pCode, char pChar ) {
        if( pRoot.getContent().charValue() == pChar ) {
            return pCode;
        }
        String code = "";
        if( !pRoot.getLeftTree().isEmpty() ) {
            code = buchstabenCodieren(pRoot.getLeftTree(), pCode+".", pChar);
        }
        if( code.isEmpty() && !pRoot.getRightTree().isEmpty() ) {
            code = buchstabenCodieren(pRoot.getRightTree(), pCode+"-", pChar);
        }
        return code;
    }
    
}
