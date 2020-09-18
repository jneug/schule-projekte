
public class Stack<ContentType> {

    private StackNode<ContentType> head = null;

    public Stack() {
    }


    public void push( ContentType pContentObject ) {
        // TODO: Implementiere push
    }

    public void pop() {
        // TODO: Implementiere pop
        if( head != null ) {
            head = head.getNext();
        }
    }

    /**
     * Liefert das Inhaltsobjekt vom obersten Knoten des Stapels
     * vom Typ ContentType, falls die Schlange nicht leer ist,
     * ansonsten null.
     *
     * @return Das Inhaltsobjekt oder null.
     */
    public ContentType top() {
        if( !isEmpty() ) {
            return head.getContent();
        } else {
            return null;
        }
    }

    /**
     * Prüft, ob der Stapel leer ist.
     *
     * @return true, wenn der Stapel keine Knoten enthaelt
     */
    public boolean isEmpty() {
        return (head == null);
    }

}
