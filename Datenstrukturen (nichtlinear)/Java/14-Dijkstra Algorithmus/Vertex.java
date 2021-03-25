/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * </p>
 * <p>
 * Klasse Vertex
 * </p>
 * <p>
 * Die Klasse Vertex stellt einen einzelnen Knoten eines Graphen dar. Jedes Objekt
 * dieser Klasse verfuegt ueber eine im Graphen eindeutige ID als String und kann diese
 * ID zurueckliefern. Darueber hinaus kann eine Markierung gesetzt und abgefragt werden.
 * </p>
 *
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Oktober 2015
 */
public class Vertex{
  //Einmalige ID des Knotens und Markierung
  private String id;
  private boolean mark;

  // Ergänzugnen für Dijkstra
  // Speicher für den kürzesten Weg
  private double value;
  // Vorgängenknoten im kürzesten Weg
  private Vertex predecessor;

    /**
  * Ein neues Objekt vom Typ Vertex wird erstellt. Seine Markierung hat den Wert false.
  */
  public Vertex(String pID){
    id = pID;
    mark = false;
    value = Integer.MAX_VALUE;
    predecessor = null;
  }

  /**
  * Die Anfrage liefert die ID des Knotens als String.
  */
  public String getID(){
    return new String(id);
  }

  /**
  * Der Auftrag setzt die Markierung des Knotens auf den Wert pMark.
  */
  public void setMark(boolean pMark){
    mark = pMark;
  }

  /**
  * Die Anfrage liefert true, wenn die Markierung des Knotens den Wert true hat, ansonsten false.
  */
  public boolean isMarked(){
    return mark;
  }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }


}
