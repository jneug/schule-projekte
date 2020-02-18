
/**
 * Eine Sortiermaschine für Arrays und Listen.
 *
 * @author J. Neugebauer
 * @version 1.0
 */
public class Sortiermaschine {

    public Sortiermaschine() {
    }

    /**
     * Sortiert ein Array mit dem Insertionsort Algorithmus
     */
    public int[] insertionsortArray( int[] zahlen ) {
        return zahlen;
    }

    /**
     * Sortiert ein Array mit dem Selectionsort Algorithmus
     */
    public int[] selectionsortArray( int[] zahlen ) {
        return zahlen;
    }

    /**
     * Sortiert ein Array mit dem Bubblesort Algorithmus
     */
    public int[] bubblesortArray( int[] zahlen ) {
        return zahlen;
    }


    /**
     * Sortiert eine Liste mit dem Insertionsort Algorithmus.
     * Tipp: Nutze eine weitere Liste zur Hilfe. Entferne das erste Element
     * aus der unsortierten Liste und sortiere es passend in der neuen Liste ein.
     */
    public List<Integer> insertionsortList( List<Integer> zahlen ) {
        return zahlen;
    }

    /**
     * Sortiert eine Liste mit dem Selectionsort Algorithmus.
     * Tipp: Nutze eine weitere Liste zur Hilfe, an die du das jeweils
     * kleinste Element der unsortierten Liste anhängst. Entferne das
     * kleinste Element aus der unsortierten Liste, damit der
     * unsortierte Bereich kleiner wird. Gib dann die neue Liste zurück.
     */
    public List<Integer> selectionsortList( List<Integer> zahlen ) {
        return zahlen;
    }

    /**
     * Sortiert eine Liste mit dem Bubblesort Algorithmus.
     * Tipp: Durchlaufe die Liste so oft von vorne bis hinten,
     * bis keine Vertauschungen mehr stattfinden (Optimierter Bubblesort).
     * Entferne dazu jeweils das erste Elemente, vergleiche es mit dem nächsten
     * und füge es ggf. wieder ein, falls das nächste größer ist.
     * Ist das entfernte Element größer, vergleiche mit dem nächsten Element. In
     * diesem Fall hat eine Vertauschung stattgefunden.
     */
    public List<Integer> bubblesortList( List<Integer> zahlen ) {
        return zahlen;
    }



    // Hilfsmethode, um ein Array auf der Konsole auszugeben
    private void printList( int[] zahlen ) {
        for( int i = 0; i < zahlen.length; i++ ) {
            System.out.println(zahlen[i]);
        }
        System.out.println("---");
    }

    // Hilfsmethode, um eine Liste auf der Konsole auszugeben
    private void printList( List<Integer> zahlen ) {
        zahlen.toFirst();
        while( zahlen.hasAccess() ) {
            System.out.println(zahlen.getContent());
            zahlen.next();
        }
        System.out.println("---");
    }

}
