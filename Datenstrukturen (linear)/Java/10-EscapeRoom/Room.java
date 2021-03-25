
/**
 * Abstrakte Basisklasse für einen Raum im EscapeRoom.
 *
 * Die Methode {@link #play(Player)} implementiert die Logik des Raumes und läuft,
 * bis der Raum gelöst wurde. Danach wird der Raum aus dem Spiel entfernt.
 *
 * Ein Raum ist gelöst, wenn alle seine {@link Lock Schlösser} geöffnet wurden.
 */
public abstract class Room {

    protected Lock[] locks;

    /**
     * Konstruktor eines Raumes
     *
     * Legt fest, wie viele Schlösser im Raum vorhanden sind.
     * @param pNumberOfLocks
     */
    public Room( int pNumberOfLocks ) {
        locks = new Lock[pNumberOfLocks];
    }

    /**
     * Prüft alle Schlösser, ob sie geöffnet sind, oder noch mindesens eins
     * verschlossen ist.
     * @return
     */
    public boolean isUnlocked() {
        for( Lock l: locks ) {
            if( !l.isOpen() ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Hauptmethode des Raumes
     * @param pPlayer Der aktuelle Spieler
     */
    public abstract void play(Player pPlayer);

}
