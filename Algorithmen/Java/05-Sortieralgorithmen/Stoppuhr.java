
/**
 * Eine Stoppuhr, die die vergangene Zeit in Nanosekunden misst.
 *
 * @author J. Neugebauer
 * @version 1.0
 */
public class Stoppuhr {

    private boolean laeuft;

    private long startzeit;

    private long letzte_messung;

    public Stoppuhr() {
        letzte_messung = 0L;
        laeuft = false;
    }

    public void start() {
        startzeit = System.nanoTime();
        laeuft = true;
    }

    public long stop() {
        letzte_messung = System.nanoTime() - startzeit;
        laeuft = false;
        return letzte_messung;
    }

    public boolean laeuft() {
        return laeuft;
    }

    public long getLetzteMessung() {
        return letzte_messung;
    }
}
