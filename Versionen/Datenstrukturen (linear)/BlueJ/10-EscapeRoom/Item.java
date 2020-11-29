/**
 * Abstrakte Basisklasse für ein Item.
 *
 * Ein Item kann in einem Raum vorhanden sein.
 *
 * Items können benutzt werden, um {@link Lock Schlösser} zu entriegeln, oder
 * für andere Dinge eingesetzt werden.
 *
 * Items können vom Spieler aufgenommen werden.
 */
public abstract class Item {

    private String name;

    public Item( String pName ) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    /**
     * Das Item wird benutzt. Als Ergebnis wird immer ein String zurück gegeben.
     * @return
     */
    public abstract String use();

    public abstract void printHint();

}
