
public class StackNode<ContentType> {

    private ContentType contentObject = null;

    private StackNode<ContentType> nextNode = null;

    /**
     * Ein neues Objekt vom Typ Node<ContentType> wird erschaffen.
     * Der Inhalt wird per Parameter gesetzt. Der Verweis ist leer.
     *
     * @param pContentObject das Inhaltselement des Knotens vom Typ ContentType
     */
    public StackNode( ContentType pContentObject) {
        contentObject = pContentObject;
        nextNode = null;
    }

    /**
     * Der Verweis wird auf das Objekt, das als Parameter uebergeben wird,
     * gesetzt.
     *
     * @param pNext der Nachfolger des Knotens
     */
    public void setNext( StackNode<ContentType> pNext) {
        nextNode = pNext;
    }

    /**
     * Liefert das naechste Element des aktuellen Knotens.
     *
     * @return das Objekt vom Typ QueueNode, auf das der aktuelle Verweis zeigt
     */
    public StackNode<ContentType> getNext() {
        return nextNode;
    }

    /**
     * Liefert das Inhaltsobjekt des Knotens vom Typ ContentType.
     *
     * @return das Inhaltsobjekt des Knotens
     */
    public ContentType getContent() {
        return contentObject;
    }

}
