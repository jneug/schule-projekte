
public class Suchmaschine {

    /**
     * Sucht das erste vorkommen von <code>suchzahl</code> in
     * <code>sucharray</code>. Ist die Zahl vorhanden, wird <code>true</code>
     * zurück gegeben, sonst <code>false</code>.
     *
     * Die Methode nutzt zur Suche die lineare Suche.
     */
    public boolean lineareSuche( int suchzahl, int[] sucharray ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        for( int i = 0; i < sucharray.length; i++ ) {
            if (sucharray[i] == suchzahl) {
                return true;
            }
        }
        return false;
        //*ml
    }

    /**
     * Sucht das erste vorkommen von <code>suchzahl</code> in
     * <code>suchliste</code>. Ist die Zahl vorhanden, wird <code>true</code>
     * zurück gegeben, sonst <code>false</code>.
     *
     * Die Methode nutzt zur Suche die lineare Suche.
     */
    public boolean lineareSuche( int suchzahl, List<Integer> suchliste ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        suchliste.toFirst();
        while( suchliste.hasAccess() ) {
            if (suchliste.getContent().intValue() == suchzahl) {
                return true;
            }
            suchliste.next();
        }
        return false;
        //*ml
    }

    /**
     * Sucht das erste vorkommen von <code>suchzahl</code> in
     * <code>sucharray</code>. Ist die Zahl vorhanden, wird <code>true</code>
     * zurück gegeben, sonst <code>false</code>.
     *
     * Die Methode nutzt zur Suche die binäre Suche. Daher muss
     * <code>sucharray</code> aufsteigend vorsortiert sein.
     */
    public boolean binaereSuche( int suchzahl, int[] sucharray ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        int left = 0, right = sucharray.length-1;
        while( left <= right ) {
            int mid = (int) ((left+right)/2);
            if( sucharray[mid] == suchzahl ) {
                return true;
            } else if( sucharray[mid] < suchzahl ) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
        //*ml
    }

}
