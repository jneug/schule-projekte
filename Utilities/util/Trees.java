package util;

import com.sun.source.tree.BinaryTree;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Eine Sammlung von Klassenmethoden, um wiederkehrende
 * Aufgaben mit binären Bäumen zu vereinfachen.
 * @version 0.1 (2019-09-13)
 * @author J. Neugebauer <schule@neugebauer.cc>
 */
public class Trees {

    public static <T> int getMaxDepth( BinaryTree<T> pRoot ) {
        if( pRoot.isEmpty() ) {
            return 0;
        }
        return 1 + Math.max(
            getMaxDepth( pRoot.getLeftTree() ),
            getMaxDepth( pRoot.getRightTree() )
        );
    }

    public static <T> int countNodes( BinaryTree<T> pRoot ) {
        if( pRoot.isEmpty() ) {
            return 0;
        }
        return 1 + countNodes( pRoot.getLeftTree() ) + countNodes( pRoot.getRightTree() );
    }

    public static <T> int countLeaves( BinaryTree<T> pRoot ) {
        if( pRoot.isEmpty() ) {
            return 0;
        } else if( pRoot.getLeftTree().isEmpty() && pRoot.getRightTree().isEmpty() ) {
            return 1;
        }
        return countLeaves( pRoot.getLeftTree() ) + countLeaves( pRoot.getRightTree() );
    }

    public static <T> void printPretty( BinaryTree<T> pRoot ) {
        printPretty(pRoot, System.out);
    }

    public static <T> void printPretty( BinaryTree<T> pRoot, PrintStream pOut ) {
        pOut.print(printPretty(pRoot, new StringBuilder(), true, new StringBuilder()).toString());
    }

    // Adapted from https://stackoverflow.com/a/27153988/10921408
    private static <T> StringBuilder printPretty( BinaryTree<T> pRoot, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if( !pRoot.getRightTree().isEmpty() ) {
            printPretty(pRoot.getRightTree(), new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(pRoot.getContent().toString()).append("\n");
        if( !pRoot.getLeftTree().isEmpty() ) {
            printPretty(pRoot.getLeftTree(), new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    public static <T> void printPreorder( BinaryTree<T> pRoot ) {
        printPreorder(pRoot, System.out);
    }

    public static <T> void printPreorder( BinaryTree<T> pRoot, PrintStream pOut ) {
        if( pRoot.getContent() == null ) {
            return;
        }

        pOut.print(pRoot.getContent().toString() + ",");
        printPreorder(pRoot.getLeftTree(), pOut);
        printPreorder(pRoot.getRightTree(), pOut);
    }

    public static <T> void printPostorder( BinaryTree<T> pRoot ) {
        printPostorder(pRoot, System.out);
    }

    public static <T> void printPostorder( BinaryTree<T> pRoot, PrintStream pOut ) {
        if( pRoot.getContent() == null ) {
            return;
        }

        pOut.print(pRoot.getContent().toString() + ",");
        printPostorder(pRoot.getLeftTree(), pOut);
        printPostorder(pRoot.getRightTree(), pOut);
    }

    public static <T> void printInorder( BinaryTree<T> pRoot ) {
        printInorder(pRoot, System.out);
    }

    public static <T> void printInorder( BinaryTree<T> pRoot, PrintStream pOut ) {
        if( pRoot.getContent() == null ) {
            return;
        }

        pOut.print(pRoot.getContent().toString() + ",");
        printInorder(pRoot.getLeftTree(), pOut);
        printInorder(pRoot.getRightTree(), pOut);
    }

    public static BinaryTree<Integer> generateBalancedIntegerTree( int pNodeCount, int pMinValue, int pMaxValue ) {
        return generateIntegerTree(pNodeCount, pMinValue, pMaxValue, 0.5, 0.0);
    }

    public static BinaryTree<Integer> generateCompleteIntegerTree( int pDepth, int pMinValue, int pMaxValue ) {
        return generateBalancedIntegerTree((int)Math.pow(2,pDepth)-1, pMinValue, pMaxValue);
    }

    public static BinaryTree<Integer> generateIntegerTree( int pNodeCount, double pWeight, double pUncertainty ) {
        return generateIntegerTree(pNodeCount, 0, 100, pWeight, pUncertainty);
    }

    public static BinaryTree<Integer> generateIntegerTree( int pNodeCount, int pMinValue, int pMaxValue, double pWeight, double pUncertainty ) {
        final Random rand = new Random();
        return generateTree(pNodeCount, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Integer(rand.nextInt(pMaxValue-pMinValue)+pMinValue);
            }
        }, pWeight, pUncertainty);
    }

    public static BinaryTree<String> generateStringTree( int pNodeCount, double pWeight, double pUncertainty ) {
        return generateStringTree(pNodeCount, new String[]{"Jonas", "Martin", "Harald", "Sabine", "Feline", "Kathrin"}, pWeight, pUncertainty);
    }

    public static BinaryTree<String> generateStringTree( int pNodeCount, final String[] pWords, double pWeight, double pUncertainty ) {
        final Random rand = new Random();
        return generateTree(pNodeCount, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return pWords[rand.nextInt(pWords.length)];
            }
        }, pWeight, pUncertainty);
    }

    public static <T> BinaryTree<T> generateTree( int pNodeCount, Callable<T> pGenerator, double pWeight, double pUncertainty ) {
        return generateTree(new BinaryTree<T>(), pNodeCount, pGenerator, pWeight, pUncertainty);
    }

    public static <T> BinaryTree<T> generateTree( BinaryTree<T> pRoot, int pNodeCount, Callable<T> pGenerator, double pWeight, double pUncertainty ) {
        if( pNodeCount > 0 ) {
            try {
                pRoot.setContent(pGenerator.call());
            } catch( Exception ex ) {
                return pRoot;
            }


            double weight = pWeight + (pUncertainty - (Math.random() * pUncertainty * 2));
            weight = Math.min(1.0, Math.max(0.0, weight));
            int leftCount = (int) (pNodeCount * weight);
            int rightCount = pNodeCount - leftCount - 1;

            generateTree(pRoot.getLeftTree(), leftCount, pGenerator, pWeight, pUncertainty);
            generateTree(pRoot.getRightTree(), rightCount, pGenerator, pWeight, pUncertainty);
        }
        return pRoot;
    }

}
