import java.io.PrintStream;

/**
 * Eine Sammlung von Klassenmethoden, um wiederkehrende
 * Aufgaben mit Listen zu vereinfachen.
 * @version 0.1 (2019-09-13)
 * @author J. Neugebauer <schule@neugebauer.cc>
 */
public class Lists {

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
        pList.toFirst();
        while( pList.hasAccess() ) {
            pOut.println(pList.getContent().toString());
            pList.next();
        }
    }

}
