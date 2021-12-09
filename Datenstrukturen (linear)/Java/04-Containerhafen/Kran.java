import java.util.Random;

/**
 * Ein Hafenkran, der Container von einem Schiff in das Lager bzw.
 * zurück laden kann.
 */
public class Kran {

    /**
     * Erzeugt ein neues Kran-Objekt.
     */
    public Kran() {

    }

    /**
     * Lädt <b>genau einen</b> Container vom Schiff in das Lager, falls das Schiff noch
     * nicht leer ist.
     * @param pSchiff
     * @param pLager
     */
    public void abladenVon(Containerschiff pSchiff, Containerlager pLager) {
        /*aufg* 1
        // TODO: implementieren
        *aufg*/
        /*aufg* 2
        // Lade vom ersten Stapel auf Lager 1
        // (Du kannst zB auch mit Random einen zufälligen Platz auswählen.)
        if( !pSchiff.getStapel1().isEmpty() ) {
            // TODO: implementieren
        }
        // sonst lade vom zweiten Stapel auf Lager 2
        else if( !pSchiff.getStapel2().isEmpty() ) {
            // TODO: implementieren
        }
        *aufg*/
        /*aufg* 3
        // Lade vom ersten Stapel auf Lager 1
        // (Du kannst zB auch mit Random einen zufälligen Platz auswählen.)
        if( !pSchiff.getStapel1().isEmpty() ) {
            // Hole den obersten Container vom Stapel
            Container c = pSchiff.getStapel1().top();
            // lege auf den ersten Lagerplatz
            pLager.getLager1().push(c);
            // Nimm den obersten Container vom Stapel
            pSchiff.getStapel1().pop();
        }
        // sonst lade vom zweiten Stapel auf einen zufälligen Lagerplatz
        else if( !pSchiff.getStapel2().isEmpty() ) {
            // TODO: implementieren
        }
        *aufg*/
        //ml*
        // Lade vom ersten Stapel auf Lager 1
        if( !pSchiff.getStapel1().isEmpty() ) {
            // Hole den obersten Container vom Stapel
            Container c = pSchiff.getStapel1().top();
            // lege auf den ersten Lagerplatz
            pLager.getLager1().push(c);
            // Nimm den obersten Container vom Stapel
            pSchiff.getStapel1().pop();
        }
        // sonst lade vom zweiten Stapel auf einen zufälligen Lagerplatz
        else if( !pSchiff.getStapel2().isEmpty() ) {
            // Bestimme zufälligen Lagerplatz
            Random zufall = new Random();
            int lager = zufall.nextInt(4)+1;

            // Hole den obersten Container vom Stapel
            Container c = pSchiff.getStapel1().top();
            // lege auf zufälligen Lagerplatz
            if( lager == 1 ) {
                pLager.getLager1().push(c);
            } else if( lager == 2 ) {
                pLager.getLager2().push(c);
            } else if( lager == 3 ) {
                pLager.getLager3().push(c);
            } else {
                pLager.getLager4().push(c);
            }

            // Nimm den obersten Container vom Stapel
            pSchiff.getStapel1().pop();
        }
        //*ml
    }

    /**
     * Lädt einen Container aus dem Lager in das Schiff, falls im Lager noch
     * Container vorhanden sind.
     * @param pSchiff
     * @param pLager
     */
    public void aufladenAuf(Containerschiff pSchiff, Containerlager pLager) {
        /*aufg* 1
        // TODO: implementieren
        *aufg*/
        /*aufg* 2
        // Suche einen Container von einem nicht leeren Lagerplatz
        Container c = null; // Noch kein Container gefunden
        if( !pLager.getLager1().isEmpty() ) {
            // TODO: obersten Container in c speichern und aus lager1 entfernen
        } else if( !pLager.getLager2().isEmpty() ) {
            // TODO: implementieren
        }
        // TODO: Lagerplatz 3 & 4

        // Container gefunden?
        if( c != null ) {
            // TODO: c auf einen Stapel des Schiffs legen
        }
        *aufg*/
        /*aufg* 3
        // Suche einen Container von einem nicht leeren Lagerplatz
        Container c = null; // Noch kein Container gefunden
        if( !pLager.getLager1().isEmpty() ) {
            c = pLager.getLager1().top();
            pLager.getLager1().pop();
        } else if( !pLager.getLager2().isEmpty() ) {
            // TODO: implementieren
        }
        // TODO: Lagerplatz 3 & 4

        // Container gefunden?
        if( c != null ) {
            // TODO: c auf einen Stapel des Schiffs legen
            // pSchiff.getStapel1().push( ... );
        }
        *aufg*/
        //ml*
        // Suche einen Container von einem nicht leeren Lagerplatz
        Container c = null; // Noch kein Container gefunden
        if( !pLager.getLager1().isEmpty() ) {
            c = pLager.getLager1().top();
            pLager.getLager1().pop();
        } else if( !pLager.getLager2().isEmpty() ) {
            c = pLager.getLager2().top();
            pLager.getLager2().pop();
        } else if( !pLager.getLager3().isEmpty() ) {
            c = pLager.getLager3().top();
            pLager.getLager3().pop();
        } else if( !pLager.getLager4().isEmpty() ) {
            c = pLager.getLager4().top();
            pLager.getLager4().pop();
        }

        // Container gefunden?
        if( c != null ) {
            // Zufällig auf einen der Schiffsstapel laden
            Random zufall = new Random();
            if( zufall.nextBoolean() ) {
                pSchiff.getStapel1().push(c);
            } else {
                pSchiff.getStapel2().push(c);
            }
        }
        //*ml
    }

}
