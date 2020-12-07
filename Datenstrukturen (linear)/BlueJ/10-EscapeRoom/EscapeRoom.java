import java.util.Scanner;
import java.util.Random;

/**
 * Hauptklasse des Spiels.
 *
 * Die Klasse stellt eine Reihe Klassenmethoden zur Verfügung, um verschiedene
 * Aufgaben zu erfüllen. Ausgaben erfolgen über die <code>print*</code>-Methoden dieser
 * Klasse, nicht direkt mit <code>System.out.println()</code>.
 *
 * Für Eingaben können die <code>ask*</code>-Methoden benutzt werden.
 *
 * Mit den <code>rand*</code>-Methoden können Zufallswerte generiert werden.
 */
public final class EscapeRoom {

    /**
     * main-Methode zum Start des Spiels
     *
     * Hier wird das Spiel erstellt und die zu spielenden Räume
     * hinzugefügt.
     *
     * @param args
     */
    public static void main(String[] args) {
        String name = askForString("Hallo Spieler. Wie ist dein Name?");
        Player player = new Player(name);

        EscapeRoom er = new EscapeRoom(player);

        // Erstellt hier die Räume, die gespielt werden sollen
        er.addRoom(new NumberlockRoom());

        er.play();
    }



    /**
     * Fragt den Nutzer nach einer Zahl.
     * @param pText Frage an den Nutzer
     * @return Die eingegebene Zahl
     */
    public static int askForInt( String pText ) {
        System.out.print(pText+" ");
        return input.nextInt();
    }

    /**
     * Fragt den Nutzer nach einem Wahrheitswert.
     * @param pText
     * @return
     */
    public static boolean askForBool( String pText ) {
        print(pText+" ");
        return input.nextBoolean();
    }

    /**
     * Fragt den NUtzer nach einem Text.
     * @param pText
     * @return
     */
    public static String askForString( String pText ) {
        print(pText+" ");
        return input.nextLine();
    }


    /**
     * Ausgabe auf der Konsole (ohne Zeilenumbruch).
     * @param pText
     */
    public static void print( String pText ) {
        System.out.print(pText);
    }

    /**
     * Ausgabe auf der Konsole (mit Zeilenumbruch).
     * @param pText
     */
    public static void println( String pText ) {
        System.out.println(pText);
    }


    /**
     * Erzeugt eine Zufallszahl im Bereich 0 bis <var>max</var>.
     * @param max
     * @return
     */
    public static int randInt( int max ) {
        return rand.nextInt(max);
    }

    /**
     * Erzeugt eine Zufallszahl im Bereich <var>min</var> bis <var>max</var>.
     * @param min
     * @param max
     * @return
     */
    public static int randInt( int min, int max ) {
        return rand.nextInt(max) + min;
    }

    /**
     * Erzeugt einen zufälligen Wahrheitswert, der zu 50% <code>true</code> ist.
     * @return
     */
    public static boolean randBool() {
        return rand.nextBoolean();
    }

    /**
     * erzeugt einen zufälligen Wahrheitswert, der zu <var>pPercentTrue</var>
     * Prozent <code>true</code> ist.
     * @param pPercentTrue
     * @return
     */
    public static boolean randBool( int pPercentTrue ) {
        return rand.nextInt(pPercentTrue) < 100;
    }

    /**
     * Wählt ein zufällige Element aus dem Array aus.
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> T randElement( T[] elements ) {
        return elements[randInt(elements.length)];
    }

    /**
     * Prüft, ob ein Element im Array vorhanden ist.
     * @param pElement
     * @param pElements
     * @param <T>
     * @return
     */
    public static <T> boolean inArray( T pElement, T[] pElements ) {
        for( T e: pElements ) {
            if( e.equals(pElement) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft, ob mindestens einer der Begriffe in <var>pTerms</var> im String
     * <var>pText</var> vorkommt.
     *
     * Kann genutzt werden, um Nutzereingaben auf eine Reihe Schlüsselworte
     * zu prüfen. Zum Beispiel:
     * <pre>
     *     String input = askForString("Was möchtest du tun?");
     *      if( EscapeRoom.containsAny(input, new String[]{
     *          "prüfen", "umsehen", "suchen", "schauen"
     *      }) ) {
     *          // ...
     *      } else {
     *          EscapeRoom.println("Das verstehe ich nicht!")
     *      }
     * </pre>
     *
     * @param pText
     * @param pTerms
     * @return
     */
    public static boolean containsAny( String pText, String[] pTerms ) {
        for( String str: pTerms ) {
            if( pText.contains(str) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft, ob alle der Begriffe in <var>pTerms</var> im String
     * <var>pText</var> vorkommen.
     *
     * Kann genutzt werden, um Nutzereingaben auf eine Reihe Schlüsselworte
     * zu prüfen. Zum Beispiel:
     * <pre>
     *     String input = askForString("Was möchtest du tun?");
     *      if( EscapeRoom.containsAny(input, new String[]{
     *          "blau", "nimm"
     *      }) ) {
     *          EscapeRoom.println("Du nimmst den blauen Schlüssel");
     *      }
     * </pre>
     *
     * @param pText
     * @param pTerms
     * @return
     */
    public static boolean containsAll( String pText, String[] pTerms ) {
        for( String str: pTerms ) {
            if( !pText.contains(str) ) {
                return false;
            }
        }
        return true;
    }
    /**
     * Wartet die angegbene Anzahl Millisekunden, bevor das Spiel weiter geht.
     * @param milli
     */
    public static void wait( int milli ) {
        try {
            Thread.sleep(milli);
        } catch( InterruptedException ex ) {
            // Ignore
        }
    }


    private static Scanner input = new Scanner(System.in);

    private static Random rand = new Random();



    private Queue<Room> rooms;

    private long startTime;

    private Player player;

    /**
     * Konstruktor
     * @return
     */
    public EscapeRoom( Player pPlayer ) {
        player = pPlayer;
        rooms = new Queue<Room>();
    }

    /**
     * Fügt dem Spiel einen Raum hinzu.
     * @param r
     */
    public void addRoom( Room r ) {
        rooms.enqueue(r);
    }

    /**
     * Startet das Spiel.
     */
    public void play() {
        startTime = System.currentTimeMillis();
        System.out.println("========================================");
        System.out.println("       Willkommen im Escape Room!       ");
        System.out.println("Die erste Herausforderung erwartet dich!");
        System.out.println("========================================");

        while( !rooms.isEmpty() ) {
            rooms.front().play(player);
            rooms.dequeue();
            println("");
        }

        long endTime = System.currentTimeMillis();
        int finalTime = (int) ((endTime-startTime) / (1000));

        System.out.println("========================================");
        System.out.println("           Du bist entkommen!           ");
        System.out.printf ("     Du hast %d Sekunden gebraucht!     \n", finalTime);
        System.out.println("========================================");
    }

}
