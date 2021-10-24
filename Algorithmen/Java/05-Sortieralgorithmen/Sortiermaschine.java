
/**
 * Eine Sortiermaschine für Arrays und Listen.
 *
 * @author J. Neugebauer
 * @version 1.0
 */
public class Sortiermaschine {

    /**
     * Sortiert ein Array mit dem Bubblesort Algorithmus.
     * @param zahlen
     * @return
     */
    public static int[] bubblesort( int[] zahlen ) {
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
        return zahlen;
    }

    /**
     * Sortiert ein Array mit dem optimierten Bubblesort Algorithmus.
     * @param zahlen
     * @return
     */
    public static int[] bubblesortOptimized( int[] zahlen ) {
        int sorted = zahlen.length-1;
        boolean swaped = false;
        while( sorted > 0 ) {
            for( int i = 1; i <= sorted; i += 1 ) {
                if( zahlen[i-1] > zahlen[i] ) {
                    int tmp = zahlen[i-1];
                    zahlen[i-1] = zahlen[i];
                    zahlen[i] = tmp;
                    swaped = true;
                }
            }
            sorted -= 1;

            // Vorzeitiges abbrechen
            if( !swaped ) {
                break;
            }
        }
        return zahlen;
    }

    public int[] shakersort( int[] zahlen ) {
        int l = 0;
        int r = zahlen.length;
        while( l < r ) {
            l += 1;
            for( int i = l; i < r; i += 1 ) {
                if( zahlen[i-1] > zahlen[i] ) {
                    int tmp = zahlen[i-1];
                    zahlen[i-1] = zahlen[i];
                    zahlen[i] = tmp;
                }
            }
            r -= 2;
            for( int j = r-2; j > l; j += 1 ) {
                if( zahlen[j-1] > zahlen[j] ) {
                    int tmp = zahlen[j-1];
                    zahlen[j-1] = zahlen[j];
                    zahlen[j] = tmp;
                }
            }
        }
        return zahlen;
    }

    /**
     * Sortiert ein Array mit dem Insertionsort Algorithmus.
     * @param zahlen
     * @return
     */
    public static int[] insertionsort( int[] zahlen ) {
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
        return zahlen;
    }

    /**
     * Sortiert ein Array mit dem Selectionsort Algorithmus
     * @param zahlen
     * @return
     */
    public static int[] selectionsort( int[] zahlen ) {
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
        return zahlen;
    }

    public static int[] radixsort( int[] zahlen ) {
        return zahlen;
    }

    public static int[] countingsort( int[] zahlen ) {
        // Maximum ermitteln
        int max = zahlen[0];
        for( int z: zahlen ) {
            if( z > max ) {
                max = z;
            }
        }
        // Zählen
        int[] counts = new int[max+1];
        for( int z: zahlen ) {
            counts[z] += 1;
        }
        // Addieren
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i-1];
        }
        // Sortieren
        int[] sorted = new int[zahlen.length];
        //for (int i = 0; i < zahlen.length; i++) {
        for( int z: zahlen ) {
            sorted[counts[z]-1] = z;
            counts[z] -= 1;
        }

        return sorted;
    }

    public static int[] shellsort( int[] zahlen ) {

        return zahlen;
    }

    public static int[] bucketsort( int[] zahlen ) {
        return zahlen;
    }

    /*public static int[] mergesort( int[] zahlen ) {
        return mergesort(zahlen, 0, zahlen.length-1);
    }

    public static int[] mergesort( int[] zahlen, int left, int right ) {
        if( left == right ) {
            return zahlen;
        }
        int mid = ((left+right)/2);
        zahlen = mergesort(zahlen, left, mid);
        zahlen = mergesort(zahlen, mid+1, right);
        for( int i = 0; i < mid+1; i++ ) {
            if( left[] )
        }
    }*/

    public static int[] quicksort( int[] zahlen ) {
        return quicksort(zahlen, 0, zahlen.length-1);
    }

    public static int[] quicksort( int[] zahlen, int links, int rechts ) {
        if( links < rechts ) {
            int teiler = teilen2(zahlen, links, rechts);
            zahlen = quicksort(zahlen, links, teiler-1);
            zahlen = quicksort(zahlen, teiler+1, rechts);
        }
        return zahlen;
    }

    private static int teilen(int[] zahlen, int links, int rechts ) {
        int pivot = getPivotIndex(zahlen, links, rechts);

        while( links < rechts ) {
            if( zahlen[links] < zahlen[pivot] ) {
                links += 1;
            } else if( zahlen[rechts] >= zahlen[pivot] ) {
                rechts -= 1;
            } else {
                int tmp = zahlen[links];
                zahlen[links] = zahlen[rechts];
                zahlen[rechts] = tmp;
            }
        }

        int tmp = zahlen[pivot];
        zahlen[pivot] = zahlen[rechts];
        zahlen[rechts] = tmp;

        return rechts;
    }

    private static int getPivotIndex( int[] zahlen, int links, int rechts ) {
        return rechts;
    }


    private static int teilen2(int[] zahlen, int links, int rechts ) {
        int pivot = rechts;
        int i = links;
        boolean linksNachRechts = true;

        while( i != pivot ) {
            if( linksNachRechts ) {
                if( zahlen[i] < zahlen[pivot] ) {
                    i += 1;
                } else {
                    int tmp = zahlen[i];
                    zahlen[i] = zahlen[pivot];
                    zahlen[pivot] = tmp;

                    tmp = pivot;
                    pivot = links;
                    i = tmp;

                    linksNachRechts = false;
                }
            } else {
                if( zahlen[i] >= zahlen[pivot] ) {
                    i -= 1;
                } else {
                    int tmp = zahlen[i];
                    zahlen[i] = zahlen[pivot];
                    zahlen[pivot] = tmp;

                    tmp = pivot;
                    pivot = i;
                    i = tmp;

                    linksNachRechts = true;
                }
            }
        }

        return pivot;
    }

    /**
     * Hilfsmethode, um ein Array auf der Konsole auszugeben.
     * @param zahlen
     */
    public static void printArray( int[] zahlen ) {
        for( int j : zahlen ) {
            System.out.println(j);
        }
        System.out.println("---");
    }

    /**
     * Hilfsmethode, um eine Liste auf der Konsole auszugeben.
     * @param zahlen
     */
    public static void printList( List<Integer> zahlen ) {
        zahlen.toFirst();
        while( zahlen.hasAccess() ) {
            System.out.println(zahlen.getContent());
            zahlen.next();
        }
        System.out.println("---");
    }

}
