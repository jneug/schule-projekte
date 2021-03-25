import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Eine Sammlung von Klassenmethoden, um wiederkehrende
 * Aufgaben mit binären Suchbäumen zu vereinfachen.
 * @version 0.1 (2019-09-13)
 * @author J. Neugebauer <schule@neugebauer.cc>
 */
public class SearchTrees {

    public static <T extends ComparableContent<T>> int getMaxDepth( BinarySearchTree<T> pRoot ) {
        if( pRoot.isEmpty() ) {
            return 0;
        }
        return 1 + Math.max(
            getMaxDepth( pRoot.getLeftTree() ),
            getMaxDepth( pRoot.getRightTree() )
        );
    }

    public static <T extends ComparableContent<T>> int countNodes( BinarySearchTree<T> pRoot ) {
        if( pRoot.isEmpty() ) {
            return 0;
        }
        return 1 + countNodes( pRoot.getLeftTree() ) + countNodes( pRoot.getRightTree() );
    }

    public static <T extends ComparableContent<T>> int countLeaves( BinarySearchTree<T> pRoot ) {
        if( pRoot.isEmpty() ) {
            return 0;
        } else if( pRoot.getLeftTree().isEmpty() && pRoot.getRightTree().isEmpty() ) {
            return 1;
        }
        return countLeaves( pRoot.getLeftTree() ) + countLeaves( pRoot.getRightTree() );
    }

    public static <T extends ComparableContent<T>> void printPretty( BinarySearchTree<T> pRoot ) {
        printPretty(pRoot, System.out);
    }

    public static <T extends ComparableContent<T>> void printPretty( BinarySearchTree<T> pRoot, PrintStream pOut ) {
        pOut.print(printPretty(pRoot, new StringBuilder(), true, new StringBuilder()).toString());
    }

    // Adapted from https://stackoverflow.com/a/27153988/10921408
    private static <T extends ComparableContent<T>> StringBuilder printPretty( BinarySearchTree<T> pRoot, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if( !pRoot.getRightTree().isEmpty() ) {
            printPretty(pRoot.getRightTree(), new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(pRoot.getContent().toString()).append("\n");
        if( !pRoot.getLeftTree().isEmpty() ) {
            printPretty(pRoot.getLeftTree(), new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    public static <T extends ComparableContent<T>> void printPreorder( BinarySearchTree<T> pRoot ) {
        printPreorder(pRoot, System.out);
    }

    public static <T extends ComparableContent<T>> void printPreorder( BinarySearchTree<T> pRoot, PrintStream pOut ) {
        if( pRoot.getContent() == null ) {
            return;
        }

        pOut.print(pRoot.getContent().toString() + ",");
        printPreorder(pRoot.getLeftTree(), pOut);
        printPreorder(pRoot.getRightTree(), pOut);
    }

    public static <T extends ComparableContent<T>> void printPostorder( BinarySearchTree<T> pRoot ) {
        printPostorder(pRoot, System.out);
    }

    public static <T extends ComparableContent<T>> void printPostorder( BinarySearchTree<T> pRoot, PrintStream pOut ) {
        if( pRoot.getContent() == null ) {
            return;
        }

        pOut.print(pRoot.getContent().toString() + ",");
        printPostorder(pRoot.getLeftTree(), pOut);
        printPostorder(pRoot.getRightTree(), pOut);
    }

    public static <T extends ComparableContent<T>> void printInorder( BinarySearchTree<T> pRoot ) {
        printInorder(pRoot, System.out);
    }

    public static <T extends ComparableContent<T>> void printInorder( BinarySearchTree<T> pRoot, PrintStream pOut ) {
        if( pRoot.getContent() == null ) {
            return;
        }

        pOut.print(pRoot.getContent().toString() + ",");
        printInorder(pRoot.getLeftTree(), pOut);
        printInorder(pRoot.getRightTree(), pOut);
    }
    

    public static <T extends ComparableContent<T>> BinarySearchTree<T> generateTree( int pNodeCount, Callable<T> pGenerator, double pWeight, double pUncertainty ) {
        BinarySearchTree<T> pRoot = new BinarySearchTree<T>();
        
        while( pNodeCount > 0 ) {
            try {
                pRoot.insert(pGenerator.call());
                pNodeCount -= 1;
            } catch( Exception ex ) {
            }
        }
        
        return pRoot;
    }

}
