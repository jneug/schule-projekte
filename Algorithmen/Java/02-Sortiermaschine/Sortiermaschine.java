
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
     * Sortiert ein Array mit dem Insertionsort Algorithmus.
     * @param zahlen
     * @return
     */
    public int[] insertionsortArray( int[] zahlen ) {
        //ml*
        int sorted = 1;
        while( sorted < zahlen.length ) {
            for( int i = sorted; i > 0; i -= 1 ) {
                if( zahlen[i] < zahlen[i-1] ) {
                    int tmp = zahlen[i-1];
                    zahlen[i-1] = zahlen[i];
                    zahlen[i] = tmp;
                }
            }
            sorted += 1;
        }
        //*ml
        return zahlen;
    }

    /**
     * Sortiert ein Array mit dem Selectionsort Algorithmus
     * @param zahlen
     * @return
     */
    public int[] selectionsortArray( int[] zahlen ) {
        //ml*
        int sorted = 0;
        while( sorted < zahlen.length-1 ) {
            int min = sorted;
            for( int i = sorted+1; i < zahlen.length; i += 1 ) {
                if( zahlen[i] < zahlen[min] ) {
                    min = i;
                }
            }

            if( min != sorted ) {
                int tmp = zahlen[min];
                zahlen[min] = zahlen[sorted];
                zahlen[sorted] = tmp;
            }

            sorted += 1;
        }
        //*ml
        return zahlen;
    }

    /**
     * Sortiert ein Array mit dem Bubblesort Algorithmus.
     * @param zahlen
     * @return
     */
    public int[] bubblesortArray( int[] zahlen ) {
        //ml*
        int sorted = zahlen.length-1;
        while( sorted > 0 ) {
            for( int i = 1; i <= sorted; i += 1 ) {
                if( zahlen[i-1] > zahlen[i] ) {
                    int tmp = zahlen[i-1];
                    zahlen[i-1] = zahlen[i];
                    zahlen[i] = tmp;
                }
            }
            sorted -= 1;
        }
        //*ml
        return zahlen;
    }

    /**
     * Sortiert eine Liste mit dem Insertionsort Algorithmus.
     * Tipp: Nutze eine weitere Liste zur Hilfe. Entferne das erste Element
     * aus der unsortierten Liste und sortiere es passend in der neuen Liste ein.
     */
    public List<Integer> insertionsortList( List<Integer> zahlen ) {
        List<Integer> sortiert = new List<Integer>();

        //ml*
        while( !zahlen.isEmpty() ) {
            zahlen.toFirst();
            Integer i = zahlen.getContent().intValue();
            zahlen.remove();

            sortiert.toFirst();
            boolean inserted = false;
            while( sortiert.hasAccess() ) {
                if( sortiert.getContent().intValue() > i ) {
                    sortiert.insert(i);
                    inserted = true;
                    break;
                }
                sortiert.next();
            }
            if( !inserted ) {
                sortiert.append(i);
            }
        }
        //*ml

        return sortiert;
    }

    /**
     * Sortiert eine Liste mit dem Selectionsort Algorithmus.
     * Tipp: Nutze eine weitere Liste zur Hilfe, an die du das jeweils
     * kleinste Element der unsortierten Liste anhängst. Entferne das
     * kleinste Element aus der unsortierten Liste, damit der
     * unsortierte Bereich kleiner wird. Gib dann die neue Liste zurück.
     */
    public List<Integer> selectionsortList( List<Integer> zahlen ) {
        List<Integer> sortiert = new List<Integer>();

        //ml*
        while( !zahlen.isEmpty() ) {
            zahlen.toFirst();
            Integer i = zahlen.getContent().intValue();
            zahlen.remove();

            while( zahlen.hasAccess() ) {
                if( zahlen.getContent().intValue() < i ) {
                    zahlen.insert(i);
                    i = zahlen.getContent().intValue();
                    zahlen.remove();
                } else {
                    zahlen.next();
                }
            }

            sortiert.append(i);
        }
        //*ml

        return sortiert;
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
        //ml*
        boolean sortiert = false;
        while( !sortiert ) {
            boolean vertauschung = false;

            zahlen.toFirst();
            Integer i = zahlen.getContent().intValue();
            zahlen.remove();

            while( zahlen.hasAccess() ) {
                if( zahlen.getContent().intValue() > i ) {
                    zahlen.insert(i);
                    i = zahlen.getContent().intValue();
                    zahlen.remove();
                } else {
                    zahlen.next();
                    vertauschung = true;
                }
            }

            zahlen.append(i);
            sortiert = !vertauschung;
        }
        //*ml

        return zahlen;
    }


    /**
     * Hilfsmethode, um ein Array auf der Konsole auszugeben.
     * @param zahlen
     */
    private void printList( int[] zahlen ) {
        for( int i = 0; i < zahlen.length; i++ ) {
            System.out.println(zahlen[i]);
        }
        System.out.println("---");
    }

    /**
     * Hilfsmethode, um eine Liste auf der Konsole auszugeben.
     * @param zahlen
     */
    private void printList( List<Integer> zahlen ) {
        zahlen.toFirst();
        while( zahlen.hasAccess() ) {
            System.out.println(zahlen.getContent());
            zahlen.next();
        }
        System.out.println("---");
    }

}
