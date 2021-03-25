class ListNode<ContentType> {

    private ContentType contentObject;
    private ListNode<ContentType> next;

    /**
     * Ein neues Objekt wird erschaffen. Der Verweis ist leer.
     *
     * @param pContent das Inhaltsobjekt vom Typ ContentType
     */
    public ListNode( ContentType pContent ) {
        contentObject = pContent;
        next = null;
    }

    /**
     * Der Inhalt des Knotens wird zurueckgeliefert.
     *
     * @return das Inhaltsobjekt des Knotens
     */
    public ContentType getContentObject() {
        return contentObject;
    }

    /**
     * Der Inhalt dieses Kontens wird gesetzt.
     *
     * @param pContent das Inhaltsobjekt vom Typ ContentType
     */
    public void setContentObject( ContentType pContent ) {
        contentObject = pContent;
    }

    /**
     * Der Nachfolgeknoten wird zurueckgeliefert.
     *
     * @return das Objekt, auf das der aktuelle Verweis zeigt
     */
    public ListNode<ContentType> getNextNode() {
        return this.next;
    }

    /**
     * Der Verweis wird auf das Objekt, das als Parameter uebergeben
     * wird, gesetzt.
     *
     * @param pNext der Nachfolger des Knotens
     */
    public void setNextNode( ListNode<ContentType> pNext ) {
        this.next = pNext;
    }

}
