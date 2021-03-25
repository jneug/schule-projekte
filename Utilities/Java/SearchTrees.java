import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Eine Sammlung von Klassenmethoden, um wiederkehrende
 * Aufgaben mit binären Suchbäumen zu vereinfachen.
 * @version 0.2 (2020-12-09)
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
        if( !pRoot.isEmpty() ) {
            pOut.print(pRoot.getContent().toString() + ",");
            printPreorder(pRoot.getLeftTree(), pOut);
            printPreorder(pRoot.getRightTree(), pOut);
        }
    }

    public static <T extends ComparableContent<T>> void printPostorder( BinarySearchTree<T> pRoot ) {
        printPostorder(pRoot, System.out);
    }

    public static <T extends ComparableContent<T>> void printPostorder( BinarySearchTree<T> pRoot, PrintStream pOut ) {
        if( !pRoot.isEmpty() ) {
            printPreorder(pRoot.getLeftTree(), pOut);
            printPreorder(pRoot.getRightTree(), pOut);
            pOut.print(pRoot.getContent().toString() + ",");
        }
    }

    public static <T extends ComparableContent<T>> void printInorder( BinarySearchTree<T> pRoot ) {
        printInorder(pRoot, System.out);
    }

    public static <T extends ComparableContent<T>> void printInorder( BinarySearchTree<T> pRoot, PrintStream pOut ) {
        if( !pRoot.isEmpty() ) {
            printPreorder(pRoot.getLeftTree(), pOut);
            pOut.print(pRoot.getContent().toString() + ",");
            printPreorder(pRoot.getRightTree(), pOut);
        }
    }

    /**
     * A class to wrap a {@link Comparable} class inside a {@link ComparableContent}
     * container.
     * @param <T>
     */
    public static class ComparableWrapper<T extends Comparable<T>> implements ComparableContent<ComparableWrapper<T>> {
        private T content;

        public ComparableWrapper( T pContent ) {
            content = pContent;
        }

        public T getContent() {
            return content;
        }

        public String toString() {
            return content.toString();
        }

        public boolean isGreater(ComparableWrapper<T> pOther) { return content.compareTo(pOther.getContent()) > 0; }
        public boolean isEqual(ComparableWrapper<T> pOther) { return content.compareTo(pOther.getContent()) == 0; }
        public boolean isLess(ComparableWrapper<T> pOther) { return content.compareTo(pOther.getContent()) < 0; }
    }

    public static BinarySearchTree<ComparableWrapper<Integer>> generateIntegerTree( int pNodeCount ) {
        return generateIntegerTree(pNodeCount, 0, 100);
    }

    public static BinarySearchTree<ComparableWrapper<Integer>> generateIntegerTree( int pNodeCount, int pMinValue, int pMaxValue ) {
        BinarySearchTree<ComparableWrapper<Integer>> tree = new BinarySearchTree<ComparableWrapper<Integer>>();
        final Random rand = new Random();
        for( int i = 0; i < pNodeCount; i++ ) {
            Integer in = new Integer(rand.nextInt(pMaxValue-pMinValue)+pMinValue);
            ComparableWrapper<Integer> content = new ComparableWrapper<Integer>(in);
            tree.insert(content);
        }
        return tree;
    }

    public static BinarySearchTree<ComparableWrapper<String>> generateStringTree( int pNodeCount ) {
        return generateStringTree(pNodeCount, new String[]{"Jonas", "Martin", "Harald", "Sabine", "Feline", "Kathrin"});
    }

    public static BinarySearchTree<ComparableWrapper<String>> generateStringTree( int pNodeCount, final String[] pWords) {
        BinarySearchTree<ComparableWrapper<String>> tree = new BinarySearchTree<ComparableWrapper<String>>();
        final Random rand = new Random();
        for( int i = 0; i < pNodeCount; i++ ) {
            String word = pWords[rand.nextInt(pWords.length)];
            ComparableWrapper<String> content = new ComparableWrapper<String>(word);
            tree.insert(content);
        }
        return tree;
    }

    public static <T extends ComparableContent<T>> BinarySearchTree<T> generateTree( int pNodeCount, Callable<T> pGenerator ) {
        BinarySearchTree<T> tree = new BinarySearchTree<T>();
        for( int i = 0; i < pNodeCount; i++ ) {
            try {
                tree.insert(pGenerator.call());
            } catch( Exception ex ) {
                System.err.println("Error: Could not generate binary searchtree content item.");
            }
        }
        return tree;
    }

}
