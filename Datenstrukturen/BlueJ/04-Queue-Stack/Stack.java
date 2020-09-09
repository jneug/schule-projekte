
public class Stack<ContentType> {

    private StackNode<ContentType> head = null;

    public Stack() {
    }


    public void push( ContentType pContentObject ) {
        /*aufg*
        // TODO: Implementiere push
        *aufg*/
        //ml*
        StackNode<ContentType> n = new StackNode<ContentType>(pContentObject);
        if( head == null ) {
            head = n;
        } else {
            n.setNext(head);
            head = n;
        }
        //*ml
    }

    public void pop() {
        /*aufg*
        // TODO: Implementiere pop
        *aufg*/
        if( head != null ) {
            head = head.getNext();
        }
        //*ml
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
