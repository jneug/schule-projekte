public class MuppetShow {

    public BinarySearchTree<Muppet> theMuppets;

    public MuppetShow() {
        theMuppets = new BinarySearchTree<Muppet>();

        theMuppets.insert(new Muppet("Kermit", "green", 0.8));
        theMuppets.insert(new Muppet("Miss Piggy", "pink", 1.2));
        theMuppets.insert(new Muppet("Gonzo", "blue", 0.75));
        theMuppets.insert(new Muppet("Rowlf", "brown", 1.0));
        theMuppets.insert(new Muppet("Beaker", "pink", 0.9));
        theMuppets.insert(new Muppet("Statler", "normal", 1.1));
        theMuppets.insert(new Muppet("Waldorf", "normal", 1.11));
    }

    public void insertMuppet( Muppet pMuppet ) {
        theMuppets.insert(pMuppet);
    }

    public void removeMuppet( Muppet pMuppet ) {
        theMuppets.remove(pMuppet);
    }

    public Muppet searchMuppet( String pName ) {
        // Erstellen eines Dummy-Muppets, der zum Suchen genutzt werden kann.
        Muppet dummyMuppet = new Muppet(pName, "", 0.0);

        BinarySearchTree<Muppet> node = theMuppets;
        while( !node.isEmpty() ) {
            /*aufg*
            // Nutzen sie die Methoden isEqual, isLess und isGreater
            // der Muppets, um den passenden Eintrag im Baum zu suchen.
            //
            // Nutzen sie diese Programmzeilen:

            // node = node.getLeftTree();
            // if( node.getContent().isEqual(dummyMuppet) ) {
            // node = node.getRightTree();
            // } else if( node.getContent().isLess(dummyMuppet) ) {
            // } else if( node.getContent().isGreater(dummyMuppet) ) {
            // }
            // return node.getContent();
            *aufg*/
            //ml*
            if( node.getContent().isEqual(dummyMuppet) ) {
                return node.getContent();
            } else if( node.getContent().isLess(dummyMuppet) ) {
                node = node.getRightTree();
            } else if( node.getContent().isGreater(dummyMuppet) ) {
                node = node.getLeftTree();
            }
            //*ml
        }
        // Muppet mit Namen pName im Baum nicht gefunden!
        return null;
    }

    /**
     * Sucht im Baum nach einem Muppet und gibt seine Farbe zurück.
     *
     * @param pName
     * @return
     */
    public String getMuppetColor( String pName ) {
        /*aufg*
        return "";
         *aufg*/
        //ml*
        Muppet m = searchMuppet(pName);
        if( m != null ) {
            return m.getColor();
        } else {
            return null;
        }
        //*ml
    }

    /**
     * Ändern sie die Methoden isEqual, isLess und isGreater in der
     * Klasse Muppet so um, dass die Muppets nun im Baum nach Größe
     * sortiert werden.
     * <p>
     * Implementieren sie dann diese Methode wie folgt:
     * - Die Methode sucht nach dem Muppet mit der Größe pOldHeight und ändert
     * seine Größe auf pNewHeight.
     * - Durch dei Änderung könnte es sein, dass der Muppet nicht mehr richtig
     * im Baum einsortiert ist. Löschen sie ihn und fügen sie ihn erneut ein.
     *
     * @param pOldHeight
     * @param pNewHeight
     */
    public void changeHeight( double pOldHeight, double pNewHeight ) {
        //ml*
        Muppet dummyMuppet = new Muppet("", "", pOldHeight);
        Muppet m = searchRecursive(theMuppets, dummyMuppet);
        if( m != null ) {
            removeMuppet(m);
            m.setHeight(pNewHeight);
            insertMuppet(m);
        }
        //*ml
    }

    //ml*
    public Muppet searchRecursive( BinarySearchTree<Muppet> pRoot, Muppet pMuppet ) {
        if( pRoot.isEmpty() ) {
            return null;
        }

        if( pRoot.getContent().isEqual(pMuppet) ) {
            return pRoot.getContent();
        } else if( pRoot.getContent().isLess(pMuppet) ) {
            return searchRecursive(pRoot.getRightTree(), pMuppet);
        } else {
            return searchRecursive(pRoot.getLeftTree(), pMuppet);
        }
    }
    //*ml

}
