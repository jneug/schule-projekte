
public class Queue<ContentType> {

    private QueueNode<ContentType> head = null;

    private QueueNode<ContentType> tail = null;

    public Queue() {
    }

    public void enqueue( ContentType pContentObject ) {
        // TODO: Implementiere enqueue
    }

    public void dequeue() {
        // TODO: Implementiere dequeue
    }

    /**
     * Liefert das Inhaltsobjekt vom ersten Knotens der Schlange
     * vom Typ ContentType, falls die Schlange nicht leer ist,
     * ansonsten null.
     *
     * @return Das Inhaltsobjekt oder null.
     */
    public ContentType front() {
        if( !isEmpty() ) {
            return head.getContent();
        } else {
            return null;
        }
    }

    /**
     * Prüft, ob die Schlange leer ist.
     *
     * @return true, wenn die Schlange keine Knoten enthaelt
     */
    public boolean isEmpty() {
        return (head == null);
    }

}
