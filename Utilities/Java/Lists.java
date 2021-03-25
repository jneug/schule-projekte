import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Eine Sammlung von Klassenmethoden, um wiederkehrende
 * Aufgaben mit Listen zu vereinfachen.
 * @version 0.1 (2019-09-13)
 * @author J. Neugebauer <schule@neugebauer.cc>
 */
public class Lists {

    public static void main( String[] args ) {
        List<Integer> list = generateIntegerList(12, 0, 100);
        print(list);
    }

    /**
     * Gibt die Liste auf der {@link System#out Kommandozeile} aus.
     * @param pList
     * @param <T>
     */
    public final static <T> void print( List<T> pList ) {
        print(pList, System.out);
    }

    /**
     * Gibt die Liste auf den angegebenen {@link PrintStream} aus.
     * @param pList
     * @param pOut
     * @param <T>
     */
    public final static <T> void print( List<T> pList, PrintStream pOut ) {
        boolean isFirst = true;

        pList.toFirst();
        while( pList.hasAccess() ) {
            if( !isFirst ) {
                pOut.print(", ");
            }
            pOut.print(pList.getContent().toString());
            pList.next();
            isFirst = false;
        }
        pOut.println();
    }

    public final static List<Integer> generateIntegerList( int pNodeCount, int pMinValue, int pMaxValue ) {
        final Random rand = new Random();
        return generateList(pNodeCount, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Integer(rand.nextInt(pMaxValue-pMinValue)+pMinValue);
            }
        });
    }

    public final static <T> List<T> generateList( int pNodeCount, Callable<T> pGenerator ) {
        List<T> list = new List<T>();
        for( int i = 0; i < pNodeCount; i++ ) {
            try {
                list.append(pGenerator.call());
            } catch( Exception ex ) {
                // Ignore
            }
        }
        return list;
    }

}
