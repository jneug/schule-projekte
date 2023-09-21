
/**
 * Aus dem Informatik-Biber 2010:
 * <p>
 * Im Restaurant der Biberschule gibt es normalerweise zwei Warteschlangen: In
 * der einen holen sich die kleinen Biber ihre hohen grünen Teller, in der
 * anderen holen sich die großen Biber ihre flachen braunen Teller. Wegen
 * Bauarbeiten kann es heute nur eine Warteschlange für alle Biber geben. Die
 * Küchenbiber müssen deshalb einen Tellerstapel vorbereiten, der zur Schlange
 * passt: Sie müssen die grünen und braunen Teller so stapeln, dass jeder Biber
 * in der Schlange den passenden Teller bekommt.
 * <p>
 * In den Kommentaren und Tests werden folgende Abkürzungen verwendet:
 *
 * <ul>
 * <li><b>f</b> Flacher Teller
 * <li><b>h</b> Hoher Teller
 * <li><b>k</b> Kleiner Biber
 * <li><b>g</b> Großer Biber
 * </ul>
 * <p>
 * Eine Reihe wartender Biber kann dann als {@code [kkgk]}  angegeben werden. Ein
 * Stapel mit Tellern als {@code [hhff]}. In diesem Beispiel ist das oberste
 * Element des Stapels ein hoher Teller.
 * <p>
 * TODO: Implementiere eine Lösung für das Tellerstapel-Problem.
 */
public class Tellerstapel {

    /**
     * Führt einen Test des Tellerstapel-Problems durch.
     * <p>
     * <b>Tipp:</b> <a
     * href="https://tipp.ngb.schule/gduc-p3lt-qnf1">https://tipp.ngb.schule/gduc-p3lt-qnf1</a>
     *
     * @param pTeller Der Tellerstapel mit großen und kleinen Tellern.
     * @param pBiber Die Warteschlange mit großen und kleinen Bibern.
     * @return {@code true}, wenn alle ihren Teller bekommen, sonst
     *     {@code false}.
     */
    public boolean testeTellerstapel( Stack<Teller> pTeller, Queue<Biber> pBiber ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        while( !pTeller.isEmpty() && !pBiber.isEmpty() ) {
            if( pTeller.top().istHoch() == pBiber.front().istGross() ) {
                // kleiner Biber mit flachen Teller oder grosser Biber mit hohem Teller
                return false;
            }
            pTeller.pop();
            pBiber.dequeue();
        }
        if( !pTeller.isEmpty() || !pBiber.isEmpty() ) {
            // es sind noch Teller oder Biber da
            return false;
        }
        return true;
        //*ml
    }

    /**
     * Zählt die Anzahl an falschen Zuordnungen (kleiner Biber zu flachem Teller
     * und großer Biber zu hohem Teller) für den gegebenen Tellerstapel und die
     * Schlagen wartender Biber. Sind am Ende der Reihe noch Teller oder Biber
     * übrig, zählen sie alle als Fehler.
     * <p>
     * <b>Tipp:</b> <a
     * href="https://tipp.ngb.schule/889m-2on7-a2h4">https://tipp.ngb.schule/889m-2on7-a2h4</a>
     *
     * @param pTeller Der Tellerstapel mit großen und kleinen Tellern.
     * @param pBiber Die Warteschlange mit großen und kleinen Bibern.
     * @return Die Anzahl an falschen Zuordnungen.
     */
    public int zaehleFalsche( Stack<Teller> pTeller, Queue<Biber> pBiber ) {
        /*aufg*
        return -1;
        *aufg*/
        //ml*
        int i = 0;
        while( !pTeller.isEmpty() || !pBiber.isEmpty() ) {
            if( !pTeller.isEmpty() && !pBiber.isEmpty() ) {
                if( pTeller.top().istHoch() == pBiber.front().istGross() ) {
                    // kleiner Biber mit flachen Teller oder grosser Biber mit hohem Teller
                    i++;
                }
            } else {
                i++;
            }
            // pop / dequeue auf leeren Stapeln / Schlangen macht nichts
            pTeller.pop();
            pBiber.dequeue();
        }
        return i;
        //*ml
    }


    /**
     * Erstellt einen Tellerstapel, der genau zur Schlange der wartenden Biber
     * passt. Für jeden kleinen Biber liegt also ein hoher Teller auf dem
     * Stapel, für jeden großen Biber ein Flacher.
     * <p>
     * Für die Eingabe {@code [kkgkkg]} wird also das Ergebnis {@code [hhfhhf]}
     * (als Stapel) produziert.
     * <p>
     * <em>Dies ist eine Sprinteraufgabe!</em>
     * <p>
     * <b>Tipp:</b> <a
     * href="https://tipp.ngb.schule/i1fi-2044-sxju">https://tipp.ngb.schule/i1fi-2044-sxju</a>
     *
     * @param pBiber Eine Warteschlange mit Bibern.
     * @return Ein Tellerstapel, der zur Warteschlange passt.
     */
    public Stack<Teller> erstelleTellerstapel( Queue<Biber> pBiber ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        // Zuerst müssen wir die Biberschlange in eine Tellerschlange umformen,
        // da die Teller im Ergebnisstapel genau anders herum eingefügt werden
        // müssen!
        Queue<Teller> tellerschlange = new Queue<>();
        while( !pBiber.isEmpty() ) {
            Biber front = pBiber.front();
            if( front.istGross() ) {
                tellerschlange.enqueue(new Teller(false));
            } else {
                tellerschlange.enqueue(new Teller(true));
            }
            // Kurszschreibweise:
            // tellerschlange.enqueue( new Teller( !front.istGross() ) );

            pBiber.dequeue();
        }

        // Dann die Tellerschlange nun in Stack umformen
        Stack<Teller> teller = new Stack<>();
        while( !tellerschlange.isEmpty() ) {
            teller.push(tellerschlange.front());
            tellerschlange.dequeue();
        }
        return teller;
        //*ml
    }

    /**
     * Da nur noch eine Essensausgabe geöffnet ist, müssen die schlangen der
     * wartenden kleinen und großen Biber zusammengeführt werden. Die Methode
     * ermittelt für einen vorbereiteten Tellerstapel eine Biberwarteschlange,
     * indem aus den waretenden kleinen und große jeweils der erste passende
     * Biber in die neue Schlange eingefügt wird (Reißverschlusssystem).
     * <p>
     * Sollte der Tellerstapel nicht für alle Biber reichen, dann werden die
     * verbleibenden Biber "nach Hause" geschickt. In der Rückgabe sind also nur
     * maximal so viele Biber, wie Teller da sind.
     * <p>
     * Sollte es nicht genug wartende große oder kleine Biber geben, dann ist im
     * Ergebnis die größte Anzahl Biber (in der passenden Reihenfolge), die
     * einen passenden Teller bekommen kann.
     * <p>
     * Für die Eingabe {@code [hfhf], [kk], [gg]} wird also die Schlange
     * {@code [kgkg]} erzeugt. Für die Eingabe {@code [hfhf], [kkk], [ggg]} auch
     * die Schlange {@code [kgkg]} und für die Eingabe {@code [hfhf], [k], [gg]}
     * die Schlange {@code [kg]}.
     * <p>
     * <em>Dies ist eine Sprinteraufgabe!</em>
     * <p>
     * <b>Tipp:</b> <a
     * href="https://tipp.ngb.schule/6m1j-iz1d-bvju">https://tipp.ngb.schule/6m1j-iz1d-bvju</a>
     *
     * @param pTeller Der Tellerstapel mit großen und kleinen Tellern.
     * @param pKleineBiber Die Warteschlange mit kleinen Bibern.
     * @param pGrosseBiber Die Warteschlange mit großen Bibern.
     * @return
     */
    public Queue<Biber> ordneBiber( Stack<Teller> pTeller, Queue<Biber> pKleineBiber, Queue<Biber> pGrosseBiber ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        Queue<Biber> reihe = new Queue<Biber>();

        while( !pTeller.isEmpty() ) {
            Teller top = pTeller.top();
            pTeller.pop();

            if( top.istHoch() ) {
                if( !pKleineBiber.isEmpty() ) {
                    reihe.enqueue(pKleineBiber.front());
                    pKleineBiber.dequeue();
                } else {
                    return reihe;
                }
            } else {
                if( !pGrosseBiber.isEmpty() ) {
                    reihe.enqueue(pGrosseBiber.front());
                    pGrosseBiber.dequeue();
                } else {
                    return reihe;
                }
            }
        }

        return reihe;
        //*ml
    }

}
