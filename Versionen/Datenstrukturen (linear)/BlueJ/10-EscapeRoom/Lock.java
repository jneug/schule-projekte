/**
 * Abstrakte Basisklasse für ein Schloss.
 *
 * Ein Schloss ist entweder verschlossen, oder geöffnet. Alle Schlösser eines
 * Raumes müssen geöffnet werden, damit der Raum als gelöst gilt.
 *
 * Schlösser können alles sein, was das Verlassen des Raumes verhindert. Also
 * zum Beispiel auch Rätsel, die gelöst werden müssen.
 *
 * Um ein "Schloss" zu entriegeln, muss ein {@link Item} eingesetzt werden.
 */
public abstract class Lock {

    protected boolean open;

    public Lock() {
        open = false;
    }

    public boolean isOpen() {
        return open;
    }

    public abstract boolean unlock( Item pItem );

    public abstract void printHint();

}
