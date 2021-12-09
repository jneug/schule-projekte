/**
 * Ein Waggon eines {@link Zug}s mit einem bestimmten Inhalt.
 *
 * !!! Die ist eine Daten-Klasse und muss nicht verändert werden!
 * @param <Content> Der Typ der Inhaltsobjete des Waggons.
 */
public class Waggon<Content> {

    // Die Ladung des Waggons vom Inhaltstyp.
    private Content ladung;

    /**
     * Erzeugt ein leeres Waggon-Objekt.
     */
    public Waggon() {
        ladung = null;
    }

    /**
     * Erzeugt ein Waggon-Objekt mit der angegeben Ladung.
     * @param pLadung
     */
	public Waggon(Content pLadung) {
        ladung = pLadung;
    }

    public Content getLadung() {
        return ladung;
    }

    public void setLadung(Content pLadung) {
        ladung = pLadung;
    }

}
