import java.util.Random;


public class GuessTheNumberRoom extends Room {
    
    private Item[] keys;
    
    public GuessTheNumberRoom() {
        super(1);
        keys = new Item[4];
        int n = EscapeRoom.randInt(12, 40);
        locks[0] = new NumberLock(n);
        keys[0] = new FixedNumberKey(n);
        keys[1] = new FixedNumberKey(EscapeRoom.randInt(12, 40));
        keys[2] = new RandomNumberKey(12, 40);
        keys[3] = new RandomNumberKey(12, 40);
    }
    
    public void play() {
        EscapeRoom.println("Du betrittst einen "
            + EscapeRoom.randElement(new String[]{
                "dunklen", 
                "schummrigen", 
                "verwinkelten"
            }) + ", unheimlichen Raum!");
        EscapeRoom.println("Die "
            + EscapeRoom.randElement(new String[]{
                "Holzdielen knirschen", 
                "Fliese scheppern", 
                "Kopfsteine klappern"
            }) + " unter deinen Füßen.");
        EscapeRoom.wait(1000);
        String s;
        
        do {
            s = EscapeRoom.askForString("Was möchtest du machen?").toLowerCase();
            
            if( EscapeRoom.containsAny(s, new String[]{"schau", "such", "forsch"}) ) {
                EscapeRoom.println("Du siehst ein Zahlenschloss mit einem Rad.");
                EscapeRoom.wait(500);
                EscapeRoom.println("Du siehst einige Papierfetzen herumliegen:");
                EscapeRoom.println("  Ein roter, ein blauer, ein gelber und ein weißer.");
            } else if( s.indexOf("weiß") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst das weiße Papier.");
                EscapeRoom.println(keys[0].hint());
            } else if( s.indexOf("blau") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst das blaue Papier.");
                EscapeRoom.println(keys[1].hint());
            } else if( s.indexOf("rot") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst das rot Papier.");
                EscapeRoom.println(keys[2].hint());
            } else if( s.indexOf("gelb") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst das gelber Papier.");
                EscapeRoom.println(keys[3].hint());
            } else if( s.indexOf("weiß") > -1 && s.indexOf("benutz") > -1 ) {
                EscapeRoom.println("Du gibst die Zahl des weißen Papiers im Schloss ein.");
                locks[0].unlock(keys[0]);
                EscapeRoom.wait(800);
                EscapeRoom.println(locks[0].hint());
            } else if( s.indexOf("blau") > -1 && s.indexOf("benutz") > -1 ) {
                EscapeRoom.println("Du gibst die Zahl des blauen Papiers im Schloss ein.");
                locks[0].unlock(keys[1]);
                EscapeRoom.wait(500);
                EscapeRoom.println(locks[0].hint());
            } else if( s.indexOf("rot") > -1 && s.indexOf("benutz") > -1 ) {
                EscapeRoom.println("Du gibst die Zahl des roten Papiers im Schloss ein.");
                locks[0].unlock(keys[2]);
                EscapeRoom.wait(700);
                EscapeRoom.println(locks[0].hint());
            } else if( s.indexOf("gelb") > -1 && s.indexOf("benutz") > -1 ) {
                EscapeRoom.println("Du gibst die Zahl des gelben Papiers im Schloss ein.");
                locks[0].unlock(keys[3]);
                EscapeRoom.wait(600);
                EscapeRoom.println(locks[0].hint());
            } else if( EscapeRoom.containsAny(s, new String[]{"nach Hause", "fliehen", "weglaufen", "verschwinden", "verlassen"}) ) {
                EscapeRoom.println("Du findest keine Möglichkeit, den Raum zu verlassen. Nur die Tür mit dem Schloss.");
            } else {
                EscapeRoom.println("Nichts passiert.");
            }
            EscapeRoom.wait(500);
        } while( !isUnlocked() );
        
        EscapeRoom.println("Du hast es geschafft, die Tür öffnet sich mit einem lauten Schleifen!");
    }
    
}
