
/**
 * Eine Stoppuhr, die die vergangene Zeit in Millisekunden misst.
 * Misst die Zeit bis zu 10 Millisekunden genau.
 * 
 * @author J. Neugebauer
 * @version 1.0
 */
public class Stoppuhr
{
    private boolean laeuft;
    
    private long startzeit;
    
    private long letzte_messung;
    
    /**
     * Konstruktor für Objekte der Klasse Stoppuhr
     */
    public Stoppuhr()
    {
        letzte_messung = 0L;
        laeuft = false;
    }
    
    public void start()
    {
        startzeit = System.currentTimeMillis();
        laeuft = true;
    }
    
    public long stop()
    {
        letzte_messung = System.currentTimeMillis() - startzeit;
        laeuft = false;
        return letzte_messung;
    }

    public long hole_letzte_messung() {
        return letzte_messung;
    }
}
