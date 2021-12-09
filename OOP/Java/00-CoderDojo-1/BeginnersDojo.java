public class BeginnersDojo {

    /**
     * Ermittelt das Maximum der Zahlen <var>a</var> und
     * <var>b</var>.
     * @param a
     * @param b
     * @return
     */
    public int max( int a, int b ) {
        /*aufg*
        return 0;
        *aufg*/
        //ml*
        if( a > b ) {
            return a;
        } else {
            return b;
        }
        //*ml
    }

    /**
     * Ermittelt das Maximum der drei Zahlen <var>a</var>, <var>b</var> und
     * <var>c</var>.
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int max( int a, int b, int c ) {
        /*aufg*
        return 0;
        *aufg*/
        //ml*
        return max(a, max(b, c));
        //*ml
    }

    /**
     * Prüft, ob <var>a</var> größer ist als <var>b</var>. Ist dies der Fall,
     * wird <var>c</var> zurückgegeben, sonst <var>d</var>.
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    public double ifGreater( double a, double b, double c, double d) {
        /*aufg*
        return 0.0;
        *aufg*/
        //ml*
        if( a > b ) {
            return c;
        } else {
            return d;
        }
        //*ml
    }

    /**
     * Prüft, ob wir heute ausschlafen können. Dies ist abhängig davon,
     * ob es kein Wochentag ist (also Wochenende) oder wir im Urlaub sind.
     * @param isWeekday
     * @param isVacation
     * @return
     */
    public boolean sleepIn( boolean isWeekday, boolean isVacation ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        return !isWeekday | isVacation;
        //*ml
    }

    /**
     * Prüft, ob eine der Zahlen gleich zehn ist, oder ob die Summe der Zahlen
     * gleich 10 ist.
     * @param a
     * @param b
     * @return
     */
    public boolean makes10( int a, int b ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        return (a+b == 10) | (a==10) | b==10;
        //*ml
    }

    /**
     * Prüft, ob die Zahl gerade ist, oder nicht.
     * @param pNumber
     * @return
     */
    public boolean isEven( int pNumber ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        return (pNumber%2 == 0);
        //*ml
    }

    /**
     * Prüft, ob die Zahl durch drei oder durch fünf teilbar ist.
     * @param pNumber
     * @return
     */
    public boolean threeOrfive( int pNumber ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        return (pNumber%3 == 0) || (pNumber%5 == 0);
        //*ml
    }

    /**
     * Prüft, ob die Zahl durch drei und durch fünf teilbar ist.
     * @param pNumber
     * @return
     */
    public boolean threeAndfive( int pNumber ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        return (pNumber%15 == 0);
        //*ml
    }

    /**
     * Prüft, ob die Zahl durch drei und / oder durch fünf teilbar ist.
     * Ist die Zahl durch drei teilbar, wird "piff" zurück gegeben, ist sie
     * durch fünf teilbar, dann "paff". Ist die Zahl durhc drei <b>und</b>
     * fünf teilbar, wird "piffpaff" zurückgegeben. Trifft nichts davon zu,
     * wird ein leeres String zurückgegeben.
     * @param pNumber
     * @return
     */
    public String piffpaff( int pNumber ) {
        /*aufg*
        return "";
        *aufg*/
        //ml*
        if( pNumber%3 == 0 ) {
            return "piff";
        } else if( pNumber%5 == 0 ) {
            return "paff";
        } else if( pNumber%15 == 0 ) {
            return "piffpaff";
        } else {
            return "";
        }
        //*ml
    }

    /**
     * Verdoppelt den String <var>pText</var>. Aus "Hallo" wird
     * also "HalloHallo".
     * @param pText
     * @return
     */
    public String duplicate( String pText ) {
        /*aufg*
        return "";
        *aufg*/
        //ml*
        return pText+pText;
        //*ml
    }

    /**
     * Erstellt einen HTML-Tag mit dem Namen <var>pTag</var> und dem Inhalt
     * <var>pText</var>. Ein HTMl-Tag hat das Format
     *
     * <pTag>pText</pTag>
     *
     * @param pText
     * @param pTag
     * @return
     */
    public String makeTag( String pText, String pTag ) {
        /*aufg*
        return "";
        *aufg*/
        //ml*
        return "<"+pTag+">"+pText+"</"+pTag+">";
        //*ml
    }



}
