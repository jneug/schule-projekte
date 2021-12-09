import java.util.Random;

/**
 * Das CoderDojo Projekt ist eine Sammlung unzusammenhängender Übungen zur
 * Objektorientierten Programmierung.
 *
 * Dieser Dojo befasst sich mit den grundlegenden Konzepten der imperativen
 * Programmierung mit Java: Variablen, Kontrollstrukturen, usw.
 *
 *
 * Im Kommentar jeder Methode steht eine Beschreibung des Problems, dass in der
 * Methode gelöst werden soll. Die Klasse {@link CoderDojoTest} enthält
 * Testmethoden, die prüfen, ob die Methode korrekt implementiert wurde. Die
 * Tests können in BlueJ mit einem Rechtsklick auf die Testklasse gestartet
 * werden.
 *
 * Du kannst die Methoden in beliebiger Reihenfolge implementieren. Such
 * dir also die für dich interessantesten heraus.
 *
 * TODO:
 * Implementiere die Methoden nach der Beschreibung im jeweiligen
 * Kommentar. Teste deine Umsetzungen regelmäßig mit den Test-Methoden
 * in der Klasse {@link CoderDojoTest}.
 */
public class CoderDojo {

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
     * Implementiere das Spiel "PiffPaff". Bei PiffPaff werden alle Zahlen von 1
     * (hier bi zu einem Maximum) der Reihe nach aufgezählt. Ist die Zahl durch
     * 3 teilbar, wird statt der Zahl "piff" ausgegeben. Ist die Zahl durch 5
     * teilbar, dann word "paff" ausgegeben. ist die Zahl durch 3 und 5 teilbar,
     * dann wird "piffpaff" ausgegeben.
     *
     * Am Ende soll die Methode die Anzahl der "piffpaff" zurückgeben.
     * @param pMax
     * @return
     */
    public int piffPaff( int pMax ) {
        /*aufg*
        return 0;
        *aufg*/
        //ml*
        int j = 0;
        for( int i = 1; i <= pMax; i++ ) {
            if( i%15 == 0 ) {
                j++;
                System.out.println("piffpaff");
            } else if( i%3 == 0 ) {
                System.out.println("piff");
            } else if( i%5 == 0 ) {
                System.out.println("paff");
            } else {
                System.out.println(i);
            }
        }
        return j;
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

    /**
     * Zählt, wie oft das Zeichen <var>pChar</var> im String <var>pText</var>
     * vorkommt und gibt die Anzahl zurück.
     * @param pText
     * @param pChar
     * @return
     */
    public int countChars( String pText, char pChar ) {
        /*aufg*
        return 0;
        *aufg*/
        //ml*
        int j = 0;
        for( int i = 0; i < pText.length(); i++ ) {
            if( pText.charAt(i) == pChar ) {
                j++;
            }
        }
        return j;
        //*ml
    }

    /**
     * Gibt einen neuen String zurück, der aus <var>pTimes</var> Wiederholungen
     * des Strings <var>pText</var> besteht. Zum Beispiel
     * <pre>
     * repeatString("foo", 4) // foofoofoofoo
     * </pre>
     * @param pText
     * @return
     */
    public String repeatString( String pText, int pTimes ) {
        /*aufg*
        return "";
        *aufg*/
        //ml*
        String out = "";
        for(; pTimes > 0; pTimes--) {
            out += pText;
        }
        return out;
        //*ml
    }


    /**
     * Ermittelt den Namen des Pokemons.
     * @param pPokemon
     * @return
     */
    public String getPokemonName( Pokemon pPokemon ) {
        /*aufg*
        return "";
        *aufg*/
        //ml*
        return pPokemon.getName();
        //*ml
    }

    /**
     * Läasst zwei {@link Pokemon} gegeneinander kämpfen und gibt den Sieger
     * zurück.
     *
     * Dabei wird der {@link Pokemon#getAttack() Angriffswert} des <var>pAttacker</var>
     * mit dem {@link Pokemon#getDefense() Verteidigungswert} des
     * <var>pDefender</var> verglichen. Ist der Wert größer, dann gewinnt der
     * Angreifer, sonst der Verteidiger.
     * @param pAttacker
     * @param pDefender
     * @return
     */
    public Pokemon pokemonFight( Pokemon pAttacker, Pokemon pDefender ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        if( pAttacker.getAttack() > pDefender.getDefense() ) {
            return pAttacker;
        } else {
            return pDefender;
        }
        //*ml
    }

    /**
     * Erhöht den Attack-Wert des Pokemons um den angegebenen Wert. Implementiere
     * dazu zunächst die Methode {@link Pokemon#addAttack(int)} in der Klasse
     * {@link Pokemon}.
     *
     * @see Pokemon#addAttack(int)
     * @param pPokemon
     * @param pValue
     */
    public void addAttack( Pokemon pPokemon, int pValue ) {
        //ml*
        pPokemon.addAttack(pValue);
        //*ml
    }

}
