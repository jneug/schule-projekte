import java.util.Random;


public class NumberlockRoom extends Room {

    private Item[] keys;

    public NumberlockRoom() {
        super(1);
        keys = new Item[4];
        int n = EscapeRoom.randInt(12, 40);
        locks[0] = new NumberLock(n);
        keys[0] = new FixedNumberKey(n);                            // Immer korrekt
        keys[1] = new FixedNumberKey(EscapeRoom.randInt(n-5, n+5)); // Manchmal korrekt
        keys[2] = new RandomNumberKey(12,40);            // Selten korrekt
        keys[3] = new RandomNumberKey(12, 40);           // Selten korrekt
    }

    public void play(Player pPlayer) {
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

            if( EscapeRoom.containsAny(s, new String[]{"schau", "such", "seh", "forsch"}) ) {
                EscapeRoom.println("Du siehst ein Zahlenschloss mit einem Rad.");
                EscapeRoom.wait(500);
                EscapeRoom.println("Du siehst einige Gegenstände herumliegen:");
                EscapeRoom.println("  Einen roten, einen blauen, einen gelben und einen weißen.");
            } else if( s.indexOf("weiß") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst den weißen Gegenstand.");
                pPlayer.addItem(keys[0]);
                keys[0].printHint();
            } else if( s.indexOf("blau") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst den blaue Gegenstand.");
                pPlayer.addItem(keys[1]);
                keys[1].printHint();
            } else if( s.indexOf("rot") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst den rot Gegenstand.");
                pPlayer.addItem(keys[2]);
                keys[2].printHint();
            } else if( s.indexOf("gelb") > -1 && s.indexOf("nimm") > -1 ) {
                EscapeRoom.println("Du nimmst den gelber Gegenstand.");
                pPlayer.addItem(keys[3]);
                keys[3].printHint();
            } else if( s.indexOf("weiß") > -1 && s.indexOf("benutz") > -1 ) {
                if( pPlayer.hasItem(keys[0]) ) {
                    EscapeRoom.println("Du gibst die Zahl des weißen Gegenstands im Schloss ein.");
                    locks[0].unlock(keys[0]);
                    EscapeRoom.wait(800);
                    locks[0].printHint();
                } else {
                    EscapeRoom.println("Du hast so einen Gegenstand nicht.");
                }
            } else if( s.indexOf("blau") > -1 && s.indexOf("benutz") > -1 ) {
                if( pPlayer.hasItem(keys[1]) ) {
                    EscapeRoom.println("Du gibst die Zahl des blauen Gegenstands im Schloss ein.");
                    locks[0].unlock(keys[1]);
                    EscapeRoom.wait(800);
                    locks[0].printHint();
                } else {
                    EscapeRoom.println("Du hast so einen Gegenstand nicht.");
                }
            } else if( s.indexOf("rot") > -1 && s.indexOf("benutz") > -1 ) {
                if( pPlayer.hasItem(keys[2]) ) {
                    EscapeRoom.println("Du gibst die Zahl des roten Gegenstands im Schloss ein.");
                    locks[0].unlock(keys[2]);
                    EscapeRoom.wait(800);
                    locks[0].printHint();
                } else {
                    EscapeRoom.println("Du hast so einen Gegenstand nicht.");
                }
            } else if( s.indexOf("gelb") > -1 && s.indexOf("benutz") > -1 ) {
                if( pPlayer.hasItem(keys[3]) ) {
                    EscapeRoom.println("Du gibst die Zahl des gelben Gegenstands im Schloss ein.");
                    locks[0].unlock(keys[3]);
                    EscapeRoom.wait(800);
                    locks[0].printHint();
                } else {
                    EscapeRoom.println("Du hast so einen Gegenstand nicht.");
                }
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
