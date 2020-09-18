import java.util.Random;

public class CheeseChampion {

    private Queue<Maus> eingang;
    private Stack<Maus> seitengang;
    private Queue<Maus> ausgang;

    public CheeseChampion() {
        eingang = new Queue<Maus>();
        seitengang = new Stack<Maus>();
        ausgang = new Queue<Maus>();
    }

    /**
     * Erzeugt <var>anzahl</var> fortlaufend nummerierte Mäuse (von 1 bis
     * <var>anzahl</var>) mit zufälliger Stärke.
     * @param anzahl
     * @see java.util.Random
     */
    public void erzeugeMaeuse( int anzahl ) {
        Random r = new Random();

        System.out.println("Erzeuge Mäuse:");
        for( int i = 0; i < anzahl; i++ ) {
            int nummer = i+1;
            int staerke = r.nextInt(100)+1;

            eingang.enqueue( new Maus(nummer, staerke) );
            System.out.printf("Maus #%d (Stärke %d)\n", nummer, staerke);
        }
    }

    /**
     * Führt den Mäusekampf nach den bekannten Regeln durch.
     *
     * Siehe im Buch auf Seite 88 für eine Beschreibung des Kampfes.
     */
    public void kampf() {
        while( !eingang.isEmpty() ) {
            if( seitengang.isEmpty() ) {
                seitengang.push(eingang.front());
                eingang.dequeue();
            } else if( eingang.front().getStaerke() >= seitengang.top().getStaerke() ) {
                seitengang.push(eingang.front());
                eingang.dequeue();
            } else {
                ausgang.enqueue(seitengang.top());
                seitengang.pop();
            }
        }
        while( !seitengang.isEmpty() ) {
            ausgang.enqueue(seitengang.top());
            seitengang.pop();
        }

        System.out.println("Das Ergebnis des Mäusekampfes ist:");
        while( !ausgang.isEmpty() ) {
            System.out.printf("Maus #%d (Stärke %d)\n",
                ausgang.front().getNummer(), ausgang.front().getStaerke());
            ausgang.dequeue();
        }
    }

    /**
     * Erzeugt eine Startaufstellung von zehn Mäusen mit festen Stärken, mit
     * denen der Kampf geprüft werden kann. Die erwartete Reihenfolge der Mäuse
     * im Ausgang lautet:
     * <pre>
     * #2, #3, #5, #6, #4, #1, #8, #9, #10, #7
     * </pre>
     */
    public void erzeugeTestMaeuse() {
        eingang.enqueue( new Maus(1, 8) );
        eingang.enqueue( new Maus(2, 75) );
        eingang.enqueue( new Maus(3, 33) );
        eingang.enqueue( new Maus(4, 23) );
        eingang.enqueue( new Maus(5, 73) );
        eingang.enqueue( new Maus(6, 71) );
        eingang.enqueue( new Maus(7, 6) );
        eingang.enqueue( new Maus(8, 86) );
        eingang.enqueue( new Maus(9, 63) );
        eingang.enqueue( new Maus(10, 38) );
    }

    public Queue<Maus> erzeugeErgebnis( String zahlen ) {
        Random r = new Random();
        Queue<Maus> mause = new Queue<Maus>();
        String[] z = zahlen.split(",");
        for( String i: z ) {
            mause.enqueue( new Maus(Integer.parseInt(i), r.nextInt()) );
        }
        return mause;
    }

    public void kampfPruefen( Queue<Maus> pErgebnis ) {
        Stack<Maus> ausgangReversed = new Stack<Maus>();
        while( !pErgebnis.isEmpty() ) {
            ausgangReversed.push(pErgebnis.front());
            pErgebnis.dequeue();
        }

        Stack<Maus> eingangReversed = new Stack<Maus>();
        Stack<Maus> seitengangReversed = new Stack<Maus>();

        while( !ausgangReversed.isEmpty() ) {
            if( seitengangReversed.isEmpty() ) {
                seitengangReversed.push(ausgangReversed.top());
                ausgangReversed.pop();
            } else if( ausgangReversed.top().getStaerke() >= seitengangReversed.top().getStaerke() ) {
                seitengangReversed.push(ausgangReversed.top());
                ausgangReversed.pop();
            } else {
                eingangReversed.push(seitengangReversed.top());
                seitengangReversed.pop();
            }
        }

        while( !seitengang.isEmpty() ) {
            eingangReversed.push(seitengangReversed.top());
            seitengangReversed.pop();
        }

        while( !eingangReversed.isEmpty() ) {
            pErgebnis.enqueue(eingangReversed.top());
            eingangReversed.pop();
        }

        while( !pErgebnis.isEmpty() ) {
            System.out.println("Maus #" + pErgebnis.front().getNummer());
            pErgebnis.dequeue();
        }
    }

}
