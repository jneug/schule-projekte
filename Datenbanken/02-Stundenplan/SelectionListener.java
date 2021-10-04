/**
 * Listener-INterface, um auf Änderungen der Auswahlboxen im GUI zu reagieren.
 */
public interface SelectionListener {

    /**
     * Wird aufgerufen, wenn sich die Auswahl ändert.
     * @param pList Name der Auswahlbox
     * @param pNewValue Neuer Wert
     */
    public void selectionChanged(String pList, String pNewValue );

}
