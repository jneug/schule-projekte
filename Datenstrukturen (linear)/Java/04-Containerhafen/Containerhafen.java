public class Containerhafen {

    private Containerlager lager;
    private Kran kran;

    public Containerhafen( Containerlager pLager, Kran pKran ) {
        lager = pLager;
        kran = pKran;
    }

    public Containerlager getLager() {
        return lager;
    }

    public void setLager(Containerlager pLager) {
        lager = pLager;
    }

    public Kran getKran() {
        return kran;
    }

    public void setKran(Kran pKran) {
        kran = pKran;
    }

    /**
     * Lädt die Container vom Schiff auf die Stapel.
     * @param pSchiff
     */
    public void schiffAbladen(Containerschiff pSchiff) {
        /*aufg* 1
        // TODO: implementieren
        *aufg*/
        /*aufg* 2
        // Lade Container ab, bis beide Stapel leer sind.
        while( !pSchiff.getStapel1().isEmpty() && !pSchiff.getStapel2().isEmpty() ) {
            // TODO: implementieren
        }
        *aufg*/
        /*aufg* 3
        // Lade Container ab, bis beide Stapel leer sind.
        while( !pSchiff.getStapel1().isEmpty() && !pSchiff.getStapel2().isEmpty() ) {
            // TODO: implementieren
        }
        *aufg*/
        //ml*
        // Lade Container ab, bis beide Stapel leer sind.
        while( !pSchiff.getStapel1().isEmpty() &&  !pSchiff.getStapel2().isEmpty() ) {
            kran.abladenVon(pSchiff, lager);
        }
        //*ml
    }

    /**
     * Belädt das Schiff mit <var>pMax</var> Containern von den Stapeln.
     * @param pSchiff
     */
    public void schiffBeladen(Containerschiff pSchiff, int pMax ) {
        /*aufg* 1
        // TODO: implementieren
        *aufg*/
        /*aufg* 2
        // Lade Container ab, bis entweder alle Stapel im Lager leer sind,
        // oder pMax Container verladen wurden.
        while( // TODO: Ist Lager leer?
                pMax > 0 ) {

            pMax -= 1;
        }
        *aufg*/
        /*aufg* 3
        // Lade Container ab, bis entweder alle Stapel im Lager leer sind,
        // oder pMax Container verladen wurden.
        while(
            !lager.istLeer() &&
                pMax > 0 ) {
            // TODO: den Kran anweisen, einen Container aufzuladen

            pMax -= 1;
        }
        *aufg*/
        //ml*
        // Lade Container ab, bis entweder alle Stapel im Lager leer sind,
        // oder pMax Container verladen wurden.
        while( !lager.istLeer() &&
                pMax > 0 ) {
            kran.aufladenAuf(pSchiff, lager);

            pMax -= 1;
        }
        //*ml
    }

    /**
     * Lädt <var>pMax</var> Container aus der Abfertigung auf den Zug.
     * @param pZug
     */
    public void zugBeladen( Zug pZug, int pMax ) {
        /*aufg* 1
        // TODO: implementieren
        *aufg*/
        /*aufg* 2
        while( !lager.getAbfertigung().isEmpty() && pMax > 0 ) {
            // TODO: Container aus Abfertigung auf Zug laden.
        }
        *aufg*/
        /*aufg* 3
        while( !lager.getAbfertigung().isEmpty() && pMax > 0 ) {
            // Ersten Container der Abfertigung aufladen
            // TODO: Zug.aufladen und front() benutzen
            // dann den Container aus der Abfertigung entfernen
            // TODO: dequeue benutzen
        }
        *aufg*/
        //ml*
        while( !lager.getAbfertigung().isEmpty() && pMax > 0 ) {
            // Ersten Container der Abfertigung aufladen
            pZug.aufladen(lager.getAbfertigung().front());
            // dann den Container aus der Abfertigung entfernen
            lager.getAbfertigung().dequeue();
        }
        //*ml
    }

}
