import java.util.Scanner;
import java.util.Random;


public final class EscapeRoom {
    
    public static void main(String[] args) {
        EscapeRoom er = getGame();
        er.addRoom(new GuessTheNumberRoom());
        er.play();
    }
    
    private static EscapeRoom game;
    
    public static EscapeRoom getGame() {
        if( game == null ) {
            game = new EscapeRoom();
        }
        return game;
    }
    
    private Queue<Room> rooms;
    
    private long startTime;
    
    private static Scanner input = new Scanner(System.in);
    
    private static Random rand = new Random();
    
    public EscapeRoom() {
        rooms = new Queue<Room>();
    }
    
    public void addRoom( Room r ) {
        rooms.enqueue(r);
    }
    
    public void play() {
        startTime = System.currentTimeMillis();
        System.out.println("Willkommen im Escape Room!");
        System.out.println("Die erste Herausforderung erwartet dich!");
        
        while( !rooms.isEmpty() ) {
            rooms.front().play();
            rooms.dequeue();
        }
        
        long endTime = System.currentTimeMillis();
        int finalTime = (int) ((endTime-startTime) / (1000));
        System.out.println("Du bist entkommen!");
        System.out.printf("Du hast %d Sekunden gebraucht!\n", finalTime);
    }
    
    public static int askForInt( String pText ) {
        System.out.print(pText+" ");
        return input.nextInt();
    }
    
    public static boolean askForBool( String pText ) {
        System.out.print(pText+" ");
        return input.nextBoolean();
    }
    
    public static String askForString( String pText ) {
        System.out.print(pText+" ");
        return input.nextLine();
    }
    
    public static void print( String pText ) {
        System.out.print(pText);
    }
    
    public static void println( String pText ) {
        System.out.println(pText);
    }
    
    public static int randInt( int max ) {
        return rand.nextInt(max);
    }
    
    public static int randInt( int min, int max ) {
        return rand.nextInt(max) + min;
    }
    
    public static boolean randBool() {
        return rand.nextBoolean();
    }
    
    public static boolean randBool( int pPercentTrue ) {
        return rand.nextInt(pPercentTrue) < 100;
    }
    
    public static <T> T randElement( T[] elements ) {
        return elements[randInt(elements.length)];
    }
    
    public static <T> boolean inArray( T pElement, T[] pElements ) {
        for( T e: pElements ) {
            if( e.equals(pElement) ) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsAny( String pText, String[] pTerms ) {
        for( String str: pTerms ) {
            if( pText.contains(str) ) {
                return true;
            }
        }
        return false;
    }
    
    public static void wait( int milli ) {
        try {
            Thread.sleep(milli);
        } catch( InterruptedException ex ) {
            // Ignore
        }
    }
    
}
