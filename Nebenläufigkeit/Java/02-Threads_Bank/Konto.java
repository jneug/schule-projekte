public class Konto {

    int geld;

    public Konto() {
        geld = 10000;
    }

    public boolean abheben( int pBetrag, Kunde pKunde ) {
        if( geld-pBetrag >= 0 ) {
            // Genug Geld vorhanden, Vorgang bearbeiten
            // Zufallszeit simuliert Arbeitszeit
            waitRandom();

            geld -= pBetrag;
            System.out.printf("KONTO: %s hebt 1000 ab. Neuer Kontostand: % 5d\n", pKunde.getName(), geld);
            return true;
        } else {
            // nicht genug Geld vorhanden
            return false;
        }
    }

    private void waitRandom() {
        try {
            int millis = (int) (Math.random() * (100 - 10 + 1) + 10);
            Thread.sleep(millis);
        } catch( InterruptedException ex ) {
            // ignore
        }
    }

}
