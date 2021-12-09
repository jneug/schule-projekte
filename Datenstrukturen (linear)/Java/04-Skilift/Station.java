public class Station {

    private String name;

    private Queue<Gondel> ab;

    private Queue<Gondel> an;

    private Stack<Gondel> abstellgleis;

    private Queue<Passagier> warteschlange;

    public int anzahlBergab = 0, anzahlBergauf = 0;

    public Station( String pName, Queue<Gondel> pKabelAb, Queue<Gondel> pKabelAn ) {
        name = pName;

        ab = pKabelAb;
        an = pKabelAn;
        abstellgleis = new Stack<>();
        warteschlange = new Queue<>();
    }

    public String getName() {
        return name;
    }

    public void warten(Passagier pPassagier ) {
        warteschlange.enqueue(pPassagier);
    }

    public void parken( Gondel pGondel ) {
        abstellgleis.push(pGondel);
    }

    public void abfahrt( Gondel pGondel ) {
        ab.enqueue(pGondel);
    }

    public Gondel ankunft() {
        if( !an.isEmpty() ) {
            Gondel g = an.front();
            an.dequeue();

            g.abfahrtVollendet();

            return g;
        } else {
            return null;
        }
    }

    public void fahren() {
        Gondel g = ankunft();
        if( g != null ) {
            System.out.printf("[%s] Gondel %d angekommen (%d Passagiere).\n", name, g.getNummer(), g.getAnzahlPassagiere());
            anzahlBergab += g.getAnzahlPassagiere();
            g.leeren();

            // Muss die Gondel zur Wartung?
            if( g.getAbfahrten() > 100 ) {
                parken(g);
                System.err.printf("[%s] Gondel %d auf Abstellgleis geparkt.\n", name, g.getNummer());
            } else {
                // Einsteigen
                while( !warteschlange.isEmpty() && !g.isVoll() ) {
                    g.einsteigen(warteschlange.front());
                    warteschlange.dequeue();
                }

                abfahrt(g);
                System.out.printf("[%s] Gondel %d bergauf geschickt (%d Passagiere).\n", name, g.getNummer(), g.getAnzahlPassagiere());
            }
        }

        if( !abstellgleis.isEmpty() ) {
            g = abstellgleis.top();
            g.reparieren();

            // Gondel ist wieder im Einsatz. Erste Fahrt ist leer.
            if( g.getAbfahrten() <= 50 ) {
                abstellgleis.pop();
                abfahrt(g);
                System.out.printf("[%s] Gondel %d vom Abstellgleis bergauf geschickt.\n", name, g.getNummer());
            }
        }
    }

}
