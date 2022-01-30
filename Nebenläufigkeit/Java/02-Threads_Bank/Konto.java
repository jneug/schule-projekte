/**
 * Ein Konto mit einem Guthaben und der Möglichkeit, Geld vom Konto
 * abzuheben.
 */
public class Konto {

    /**
     * Das noch vorhandene Gurhabne auf diesem Konto.
     */
    private int guthaben;

    /**
     * Erstellt das Konto mit dem angegebenen Guthaben.
     *
     * @param pGuthaben
     */
    public Konto( int pGuthaben ) {
        guthaben = pGuthaben;
    }

    /**
     * Hebt den angebenen Betrag für den Kunden vom Konto ab.
     * <p>
     * Die Verarbeitung dauert etwas Zeit und wird durch eine zufällige
     * Wartezeit simuliert.
     * <p>
     * Konnte das Geld abgehoben werden, wird <code>true</code> zurückgegeben.
     * Ist nicht genug Geld auf dem Konto, dann ist das Ergebnis <code>false</code>.
     *
     * @param pBetrag
     * @param pKunde
     * @return
     */
    public boolean abheben( int pBetrag, Kunde pKunde ) {
        if( guthaben - pBetrag >= 0 ) {
            // Genug Geld vorhanden, Vorgang bearbeiten
            // Zufallszeit simuliert Arbeitszeit
            waitRandom();

            guthaben -= pBetrag;
            System.out.printf("KONTO: %s hebt 1000 ab. Neuer Kontostand: % 5d\n", pKunde.getName(), guthaben);
            return true;
        } else {
            // Nicht genug Geld vorhanden
            return false;
        }
    }

    /**
     * Wartet eine zufällige Zeit zwischen 10 und 100 Millisekunden.
     */
    private void waitRandom() {
        try {
            int millis = (int) (Math.random() * (100 - 10 + 1) + 10);
            Thread.sleep(millis);
        } catch( InterruptedException ex ) {
            // mit Absicht leer
        }
    }

}
