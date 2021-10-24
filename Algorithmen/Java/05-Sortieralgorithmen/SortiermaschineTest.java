

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
        testArray(s.bubblesort(zufallsArray));
    }

    @Test
    public void testSelectionsortArray() {
        Sortiermaschine s = new Sortiermaschine();
        testArray(s.selectionsort(zufallsArray));
    }

    @Test
    public void testInsertionsortArray() {
        Sortiermaschine s = new Sortiermaschine();
        testArray(s.insertionsort(zufallsArray));
    }

    @Test
    public void testQuicksortArray() {
        Sortiermaschine s = new Sortiermaschine();
        testArray(s.quicksort(zufallsArray));
    }

    @Test
    public void testCountingsortArray() {
        Sortiermaschine s = new Sortiermaschine();
        testArray(s.countingsort(zufallsArray));
    }

    private void testArray( int[] sortiert ) {
        for( int i = 1; i < sortiert.length; i++ ) {
            assertFalse(sortiert[i-1]+" (Index "+(i-1)+
                    ") ist größer als "+sortiert[i]+" (Index "+i+")",
                sortiert[i-1]>sortiert[i]);
        }
    }

}
