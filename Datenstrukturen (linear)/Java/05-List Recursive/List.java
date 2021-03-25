import javax.swing.text.AbstractDocument;

public class List<ContentType> {

    ContentType content;

    // näcshtes Element der Liste
    List<ContentType> next;

    /**
     * Eine Liste wird erzeugt.
     */
    public List() {
        content = null;
        next = null;
    }

    /**
     * Eine Liste wird erzeugt.
     */
    public List( ContentType pContent ) {
        content = pContent;
        next = null;
    }

    public List( ContentType pContent, List<ContentType> pTail ) {
        content = pContent;
        next = pTail;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn die Liste keine Objekte enthaelt,
     * sonst liefert sie den Wert false.
     *
     * @return true, wenn die Liste leer ist, sonst false
     */
    public boolean isEmpty() {
        if( next == null ) {
            return content == null;
        } else {
            return content == null && next.isEmpty();
        }
    }

    /**
     * Falls die Liste nicht leer ist, es ein aktuelles Objekt gibt und dieses
     * nicht das letzte Objekt der Liste ist, wird das dem aktuellen Objekt in
     * der Liste folgende Objekt zum aktuellen Objekt, andernfalls gibt es nach
     * Ausfuehrung des Auftrags kein aktuelles Objekt, d.h. hasAccess() liefert
     * den Wert false.
     */
    public List<ContentType> next() {
        return next;
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird das
     * aktuelle Objekt zurueckgegeben, andernfalls (hasAccess() == false) gibt
     * die Anfrage den Wert null zurueck.
     *
     * @return das aktuelle Objekt (vom Typ ContentType) oder null, wenn es
     * kein aktuelles Objekt gibt
     */
    public ContentType getContent() {
        return content;
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true) und pContent
     * ungleich null ist, wird das aktuelle Objekt durch pContent ersetzt. Sonst
     * geschieht nichts.
     *
     * @param pContent das zu schreibende Objekt vom Typ ContentType
     */
    public void setContent( ContentType pContent ) {
        // Nichts tun, wenn es keinen Inhalt oder kein aktuelles Element gibt.
        if( pContent != null ) {
            content = pContent;
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird ein neues
     * Objekt vor dem aktuellen Objekt in die Liste eingefuegt. Das aktuelle
     * Objekt bleibt unveraendert. <br />
     * Wenn die Liste leer ist, wird pContent in die Liste eingefuegt und es
     * gibt weiterhin kein aktuelles Objekt (hasAccess() == false). <br />
     * Falls es kein aktuelles Objekt gibt (hasAccess() == false) und die Liste
     * nicht leer ist oder pContent gleich null ist, geschieht nichts.
     *
     * @param pContent das einzufuegende Objekt vom Typ ContentType
     */
    public void insert( ContentType pContent ) {

    }

    /**
     * Falls pContent gleich null ist, geschieht nichts.<br />
     * Ansonsten wird ein neues Objekt pContent am Ende der Liste eingefuegt.
     * Das aktuelle Objekt bleibt unveraendert. <br />
     * Wenn die Liste leer ist, wird das Objekt pContent in die Liste eingefuegt
     * und es gibt weiterhin kein aktuelles Objekt (hasAccess() == false).
     *
     * @param pContent das anzuhaengende Objekt vom Typ ContentType
     */
    public void append( ContentType pContent ) {
        if( next == null ) {
            next = new List<>(pContent);
        } else {
            next.append(pContent);
        }
    }

    /**
     * Falls es sich bei der Liste und pList um dasselbe Objekt handelt,
     * pList null oder eine leere Liste ist, geschieht nichts.<br />
     * Ansonsten wird die Liste pList an die aktuelle Liste angehaengt.
     * Anschliessend wird pList eine leere Liste. Das aktuelle Objekt bleibt
     * unveraendert. Insbesondere bleibt hasAccess identisch.
     *
     * @param pList die am Ende anzuhaengende Liste vom Typ List<ContentType>
     */
    public void concat( List<ContentType> pList ) {
        if( pList != null ) {
            if( next == null ) {
                next = pList;
            } else {
                next.concat(pList);
            }
        }
    }

    /**
     * Wenn die Liste leer ist oder es kein aktuelles Objekt gibt (hasAccess()
     * == false), geschieht nichts.<br />
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird das
     * aktuelle Objekt geloescht und das Objekt hinter dem geloeschten Objekt
     * wird zum aktuellen Objekt. <br />
     * Wird das Objekt, das am Ende der Liste steht, geloescht, gibt es kein
     * aktuelles Objekt mehr.
     */
    public void remove() {

    }

}
