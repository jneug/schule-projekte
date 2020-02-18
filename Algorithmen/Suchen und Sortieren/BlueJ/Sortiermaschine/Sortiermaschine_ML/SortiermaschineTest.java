    
    
    import static org.junit.Assert.*;
    import org.junit.After;
    import org.junit.Before;
    import org.junit.Test;
    
    public class SortiermaschineTest {
    
    int[] zufallsArray;
    List<Integer> zufallsListe;
    
    @Before
    public void setUp() {
        zufallsArray = new int[]{5, 3, 17, 23, 59, 83, 64, 19, 99, 11, 2, 32, 33, 34, 7, 66, 51};
        
        zufallsListe = new List<Integer>();
        for( int i = 0; i < zufallsArray.length; i++ ) {
            zufallsListe.append(zufallsArray[i]);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testBubblesortArray() {
        Sortiermaschine s = new Sortiermaschine();
        testArray(s.bubblesortArray(zufallsArray));
    }
    
    @Test
    public void testSelectionsortArray() {
        Sortiermaschine s = new Sortiermaschine();
        testArray(s.selectionsortArray(zufallsArray));
    }
    
    @Test
    public void testInsertionsortArray() {
        Sortiermaschine s = new Sortiermaschine();
        testArray(s.insertionsortArray(zufallsArray));
    }
    
    private void testArray( int[] sortiert ) {
        for( int i = 1; i < sortiert.length; i++ ) {
            assertFalse(sortiert[i-1]+" (Index "+(i-1)+
                    ") ist größer als "+sortiert[i]+" (Index "+i+")",
                sortiert[i-1]>sortiert[i]);
        }
    }
    
    
    
    @Test
    public void testBubblesortList() {
        Sortiermaschine s = new Sortiermaschine();
        testList(s.bubblesortList(zufallsListe));
    }
    
    @Test
    public void testSelectionsortList() {
        Sortiermaschine s = new Sortiermaschine();
        testList(s.selectionsortList(zufallsListe));
    }
    
    @Test
    public void testInsertionsortList() {
        Sortiermaschine s = new Sortiermaschine();
        testList(s.insertionsortList(zufallsListe));
    }
    
    private void testList( List<Integer> sortiert ) {
        sortiert.toFirst();
        int letzteZahl = sortiert.getContent().intValue();
        sortiert.next();
        while( sortiert.hasAccess() ) {
            int dieseZahl = sortiert.getContent().intValue();
            assertFalse(letzteZahl+" ist größer als "+dieseZahl,
                letzteZahl>dieseZahl);
            sortiert.next();
        }
    }
    
}
