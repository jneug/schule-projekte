
public class Queue<ContentType> {

    private QueueNode<ContentType> head = null;

    private QueueNode<ContentType> tail = null;

    public Queue() {
    }

    public void enqueue( ContentType pContentObject ) {
        /*aufg*
        // TODO: Implementiere enqueue
        *aufg*/
        //ml*
        QueueNode<ContentType> n = new QueueNode<ContentType>(pContentObject);
        if( isEmpty() ) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
        //*ml
    }

    public void dequeue() {
        /*aufg*
        // TODO: Implementiere dequeue
        *aufg*/
        //ml*
        if( head != tail ) {
            head = head.getNext();
        } else if( !isEmpty() ) {
            head = null;
            tail = null;
        }
        //*ml
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
