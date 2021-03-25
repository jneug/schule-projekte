public class ExampleRoom extends Room {

    private class PasswortLock extends Lock {
        @Override
        public boolean unlock( Item pItem ) {
            if( this.isOpen() ) {
                // Das Schloss ist eh schon offen
                return true;
            } else {
                // Prüfe das "Passwort"
                // Ist true, wenn das Passeort "Mellon" ist
                this.open = pItem.use().equals("Mellon");
                //  Gib den Status des schlosses zurück
                // (bei falschem Passwort ist das Schloss noch zu)
                return this.isOpen();
            }
        }

        @Override
        public void printHint() {
            EscapeRoom.println("Sprich Freund und tritt ein.");
        }
    }

    private class Passwort extends Item {
        public Passwort() {
            super("Ein Passwort");
        }

        @Override
        public String use() {
            // Fixes Passwort
            return "Mellon";
        }

        @Override
        public void printHint() {
            EscapeRoom.println("Was ist das elbische Wort für \"Freund\"?");
        }
    }

    public ExampleRoom() {
        super(1); // Ein "Schloss"
        this.locks[0] = new PasswortLock();
    }

    @Override
    public void play( Player pPlayer ) {
        EscapeRoom.println("Hallo "+pPlayer.getName()+". Du stehst vor den Türen von Durin, des Herrn von Moria.");

        do {
            String eingabe = EscapeRoom.askForString("Was möchtest du tun?").toLowerCase();

            if( eingabe.contains("umseh") ) {
                EscapeRoom.println("Du siehst ein Schloss");
            }

            if( eingabe.contains("untersuch") ) {
                EscapeRoom.print("Du untersuchst das Schloss:");
                locks[0].printHint();
            }

            if( eingabe.contains("mellon") ) {
                locks[0].unlock(new Passwort());
            }
        } while( !this.isUnlocked() );

        EscapeRoom.println("Die Tür beginnt sich zu öffnen. Eine Stimme sagt:");
        EscapeRoom.println("Und nun, Elbenherr, werdet Ihr die berühmte Gastfreundschaft der Zwerge kennenlernen! Prasselnde Kaminfeuer, Malzbier, gut abgehangenes Fleisch!");
    }

}
