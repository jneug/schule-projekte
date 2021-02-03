/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * </p>
 * <p>
 * Klasse Edge
 * </p>
 * <p>
 * Die Klasse Edge stellt eine einzelne, ungerichtete Kante eines Graphen dar. 
 * Beim Erstellen werden die beiden durch sie zu verbindenden Knotenobjekte und eine 
 * Gewichtung als double uebergeben. Beide Knotenobjekte koennen abgefragt werden. 
 * Des Weiteren koennen die Gewichtung und eine Markierung gesetzt und abgefragt werden.
 * </p>
 * 
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Oktober 2015
 */
public class Edge{
  private Vertex[] vertices;
  private double weight;
  private boolean mark;
  
  /**
  * Ein neues Objekt vom Typ Edge wird erstellt. Die von diesem Objekt 
  * repraesentierte Kante verbindet die Knoten pVertex und pAnotherVertex mit der 
  * Gewichtung pWeight. Ihre Markierung hat den Wert false.
  */
  public Edge(Vertex pVertex, Vertex pAnotherVertex, double pWeight){
    vertices = new Vertex[2];
    vertices[0] = pVertex;
    vertices[1] = pAnotherVertex;
    weight = pWeight;
    mark = false;
  }
  
  /**
  * Die Anfrage gibt die beiden Knoten, die durch die Kante verbunden werden, als neues Feld vom Typ Vertex zurueck. Das Feld hat 
  * genau zwei Eintraege mit den Indexwerten 0 und 1.
  */
  public Vertex[] getVertices(){
    Vertex[] result = new Vertex[2];
    result[0] = vertices[0]; 
    result[1] = vertices[1];
    return result;
  }
  
  /**
  * Der Auftrag setzt das Gewicht der Kante auf pWeight.
  */
  public void setWeight(double pWeight){
    weight = pWeight;
  } 
  
  /**
  * Die Anfrage liefert das Gewicht der Kante als double.
  */
  public double getWeight(){
    return weight;
  } 
  
  /**
  * Der Auftrag setzt die Markierung der Kante auf den Wert pMark.
  */
  public void setMark(boolean pMark){
    mark = pMark;
  }
  
  /**
  * Die Anfrage liefert true, wenn die Markierung der Kante den Wert true hat, ansonsten false.
  */
  public boolean isMarked(){
    return mark;
  }
  
}
