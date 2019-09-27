import java.util.Random;

/**
 * Implementierungen der drei Traversierungsarten auf
 * Binärbäumen mit Zahl-Inhalten.
 */
public class Traversierungen {

    private BinaryTree<Integer> root;

    public Traversierungen() {
        // Hier den Binärbaum erstellen
        root = new BinaryTree<Integer>(5);

        root.getLeftTree().setContent(4);
        root.getLeftTree().getLeftTree().setContent(8);
        root.getLeftTree().getRightTree().setContent(7);

        root.getRightTree().setContent(6);
        root.getRightTree().getRightTree().setContent(3);
    }

    /**
     * Aufruf für die Preorder-Traversierung.
     *
     * @see #preorder(BinaryTree)
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Rekursive Methode für die Preorder-Traversierung.
     * @param pRoot Der Wurzelknoten des aktuellen Teilbaumes.
     */
    public void preorder( BinaryTree<Integer> pRoot ) {

    }

    /**
     * Aufruf für die Postorder-Traversierung.
     *
     * @see #postorder(BinaryTree)
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Rekursive Methode für die Postorder-Traversierung.
     * @param pRoot Der Wurzelknoten des aktuellen Teilbaumes.
     */
    public void postorder( BinaryTree<Integer> pRoot ) {

    }

    /**
     * Aufruf für die Inorder-Traversierung.
     *
     * @see #inorder(BinaryTree)
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Rekursive Methode für die Inorder-Traversierung.
     * @param pRoot Der Wurzelknoten des aktuellen Teilbaumes.
     */
    public void inorder( BinaryTree<Integer> pRoot ) {

    }
}
