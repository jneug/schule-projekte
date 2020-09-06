/**
 * Ein einfacher Rechner.
 */
public class Calculator {

    /**
     * Bildet die Summe aller gerader Zahlen die kleiner oder gleich
     * <var>pUpperBound</var> sind. Die Eingabe  <code>addEvenNumbers(4)</code>
     * ergibt also <code>2 + 4 = 6</code>.
     *
     * TODO: Benutze den Debugger, um die beiden Fehler in der Methode zu finden.
     *
     * @param pUpperBound Obere Grenze der Summe
     * @return
     */
    public int addEvenNumbers( int pUpperBound ) {
        int sum = 0, i = 1;
        while( i <= pUpperBound ) {
            // Nur gerade Zahlen addieren
            if( i%2 == 0 ) {
                sum += i;
            }
            i++;
        }
        return sum;
    }

    /**
     * Wendet den euklidischen Algorithmus zur Suche des größten gemeinsamen
     * Teilers bei zwei positiven ganzen Zahlen an.
     *
     * TODO: Benutze den Debugger, um den Fehler in der Methode zu finden.
     *
     * @link https://de.wikipedia.org/wiki/Euklidischer_Algorithmus
     * @param pNumberA
     * @param pNumberB
     * @return
     */
    public int ggT( int pNumberA, int pNumberB ) {
        if( pNumberA < 0 || pNumberB < 0 ) {
            return 0;
        } else if( pNumberA == 0 ) {
            return pNumberB;
        } else {
            while( pNumberB > 0 ) {
                if( pNumberA > pNumberB ) {
                    pNumberA -= pNumberB;
                } else {
                    pNumberB -= pNumberA;
                }
            }
            return pNumberA;
        }
    }

    /**
     * Berechnet das kleinste gemeinsame Vielfache zweier positiver Ganzzahlen.
     * @link https://de.wikipedia.org/wiki/Kleinstes_gemeinsames_Vielfaches#Berechnung_über_den_größten_gemeinsamen_Teiler_(ggT)
     * @param pNumberA
     * @param pNumberB
     * @return
     */
    public int kgV( int pNumberA, int pNumberB ) {
        if( pNumberA < 0 || pNumberB < 0 ) {
            return 0;
        } else {
            return (int) (pNumberA / this.ggT(pNumberA, pNumberB) * pNumberB);
        }
    }

}
