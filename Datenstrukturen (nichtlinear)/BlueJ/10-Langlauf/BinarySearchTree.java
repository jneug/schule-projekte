/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * </p>
 * <p>
 * Generische Klasse BinarySearchTree<ContentType>
 * </p>
 * <p>
 * Mithilfe der generischen Klasse BinarySearchTree koennen beliebig viele
 * Objekte in einem Binaerbaum (binaerer Suchbaum) entsprechend einer
 * Ordnungsrelation verwaltet werden. <br />
 * Ein Objekt der Klasse stellt entweder einen leeren binaeren Suchbaum dar oder
 * verwaltet ein Inhaltsobjekt sowie einen linken und einen rechten Teilbaum,
 * die ebenfalls Objekte der Klasse BinarySearchTree sind.<br />
 * Die Klasse der Objekte, die in dem Suchbaum verwaltet werden sollen, muss
 * das generische Interface ComparableContent implementieren. Dabei muss durch
 * Ueberschreiben der drei Vergleichsmethoden isLess, isEqual, isGreater (s.
 * Dokumentation des Interfaces) eine eindeutige Ordnungsrelation festgelegt
 * sein. <br />
 * Alle Objekte im linken Teilbaum sind kleiner als das Inhaltsobjekt des
 * binaeren Suchbaums. Alle Objekte im rechten Teilbaum sind groesser als das
 * Inhaltsobjekt des binaeren Suchbaums. Diese Bedingung gilt (rekursiv) auch in
 * beiden Teilbaeumen. <br />
 * Hinweis: In dieser Version wird die Klasse BinaryTree nicht benutzt.
 * </p>
 * 
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Generisch_03 2017-11-28
 */
public class BinarySearchTree<ContentType extends ComparableContent<ContentType>> {

	/* --------- Anfang der privaten inneren Klasse -------------- */

	/**
	 * Durch diese innere Klasse kann man dafuer sorgen, dass ein leerer Baum
	 * null ist, ein nicht-leerer Baum jedoch immer eine nicht-null-Wurzel sowie
	 * nicht-null-Teilbaeume hat.
	 */
	private class BSTNode<CT extends ComparableContent<CT>> {
	  
		private CT content;
		private BinarySearchTree<CT> left, right;

		public BSTNode(CT pContent) {
			// Der Knoten hat einen linken und rechten Teilbaum, die 
			// beide von null verschieden sind. Also hat ein Blatt immer zwei 
			// leere Teilbaeume unter sich.
			this.content = pContent;
			left = new BinarySearchTree<CT>();
			right = new BinarySearchTree<CT>();
		}
		
	}

	/* ----------- Ende der privaten inneren Klasse -------------- */

	private BSTNode<ContentType> node;

	/**
	 * Der Konstruktor erzeugt einen leeren Suchbaum.
	 */
	public BinarySearchTree() {
		this.node = null;
	}

	/**
	 * Diese Anfrage liefert den Wahrheitswert true, wenn der Suchbaum leer ist,
	 * sonst liefert sie den Wert false.
	 * 
	 * @return true, wenn der binaere Suchbaum leer ist, sonst false
	 * 
	 */
	public boolean isEmpty() {
		return this.node == null;
	}

	/**
	 * Falls der Parameter null ist, geschieht nichts.<br />
	 * Falls ein bezueglich der verwendeten Vergleichsmethode isEqual mit
	 * pContent uebereinstimmendes Objekt im geordneten binaeren Suchbau
	 * enthalten ist, passiert nichts. <br />
	 * Achtung: hier wird davon ausgegangen, dass isEqual genau dann true
	 * liefert, wenn isLess und isGreater false liefern. <br />
	 * Andernfalls (isLess oder isGreater) wird das Objekt pContent entsprechend
	 * der vorgegebenen Ordnungsrelation in den BinarySearchTree eingeordnet.
	 * 
	 * @param pContent
	 *            einzufuegendes Objekt vom Typ ContentType
	 *            
	 */
	public void insert(ContentType pContent) {
		if (pContent != null) {
			if (isEmpty()) {
				this.node = new BSTNode<ContentType>(pContent);
			} else if (pContent.isLess(this.node.content)) {
				this.node.left.insert(pContent);
			} else if(pContent.isGreater(this.node.content)) {
				this.node.right.insert(pContent);
			}
		}
	}

	/**
	 * Diese Anfrage liefert den linken Teilbaum des binaeren Suchbaumes. <br />
	 * Wenn er leer ist, wird null zurueckgegeben.
	 * 
	 * @return den linken Teilbaum (Objekt vom Typ BinarySearchTree<ContentType>) 
	 *         bzw. null, wenn der Suchbaum leer ist
	 *         
	 */
	public BinarySearchTree<ContentType> getLeftTree() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.node.left;
		}
	}

	/**
	 * Diese Anfrage liefert das Inhaltsobjekt des Suchbaumes. Wenn der Suchbaum
	 * leer ist, wird null zurueckgegeben.
	 * 
	 * @return das Inhaltsobjekt vom Typ ContentType bzw. null, wenn der aktuelle
	 *         Suchbaum leer ist
	 *         
	 */
	public ContentType getContent() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.node.content;
		}
	}

	/**
	 * Diese Anfrage liefert den rechten Teilbaum des binaeren Suchbaumes. <br />
	 * Wenn er leer ist, wird null zurueckgegeben.
	 * 
	 * @return den rechten Teilbaum (Objekt vom Typ BinarySearchTree<ContentType>) 
	 *         bzw. null, wenn der aktuelle Suchbaum leer ist
	 *         
	 */
	public BinarySearchTree<ContentType> getRightTree() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.node.right;
		}
	}

	/**
	 * Falls ein bezueglich der verwendeten Vergleichsmethode mit
	 * pContent uebereinstimmendes Objekt im binaeren Suchbaum enthalten
	 * ist, wird dieses entfernt. Falls der Parameter null ist, aendert sich
	 * nichts.
	 * 
	 * @param pContent
	 *            zu entfernendes Objekt vom Typ ContentType
	 *            
	 */
	public void remove(ContentType pContent) {
		if (isEmpty() || pContent == null ) {
			// Abbrechen, da kein Element zum entfernen vorhanden ist.
		  return;
		}
		
		if (pContent.isLess(node.content)) {
			// Element ist im linken Teilbaum zu loeschen.
			node.left.remove(pContent);
		} else if (pContent.isGreater(node.content)) {
			// Element ist im rechten Teilbaum zu loeschen.
			node.right.remove(pContent);
		} else {
			// Element ist gefunden.
			if (node.left.isEmpty()) {
				if (node.right.isEmpty()) {
					// Es gibt keinen Nachfolger.
					node = null;
				} else {
					// Es gibt nur rechts einen Nachfolger.
					node = getNodeOfRightSuccessor();
				}
			} else if (node.right.isEmpty()) {
				// Es gibt nur links einen Nachfolger.
				node = getNodeOfLeftSuccessor();
			} else {
				// Es gibt links und rechts einen Nachfolger.
				if (getNodeOfRightSuccessor().left.isEmpty()) {
					// Der rechte Nachfolger hat keinen linken Nachfolger.
					node.content = getNodeOfRightSuccessor().content;
					node.right = getNodeOfRightSuccessor().right;
				} else {
					BinarySearchTree<ContentType> previous = node.right
							.ancestorOfSmallRight();
					BinarySearchTree<ContentType> smallest = previous.node.left;
					this.node.content = smallest.node.content;
					previous.remove(smallest.node.content);
				}
			}
		}		
	}

	/**
	 * Falls ein bezueglich der verwendeten Vergleichsmethode isEqual mit
	 * pContent uebereinstimmendes Objekt im binaeren Suchbaum enthalten ist,
	 * liefert die Anfrage dieses, ansonsten wird null zurueckgegeben. <br />
	 * Falls der Parameter null ist, wird null zurueckgegeben.
	 * 
	 * @param pContent
	 *            zu suchendes Objekt vom Typ ContentType
	 * @return das gefundene Objekt vom Typ ContentType, bei erfolgloser Suche null
	 * 
	 */
	public ContentType search(ContentType pContent) {
		if (this.isEmpty() || pContent == null) {
			// Abbrechen, da es kein Element zu suchen gibt.
			return null;
		} else {
			ContentType content = this.getContent();
			if (pContent.isLess(content)) {
				// Element wird im linken Teilbaum gesucht.
				return this.getLeftTree().search(pContent);
			} else if (pContent.isGreater(content)) {
				// Element wird im rechten Teilbaum gesucht.
				return this.getRightTree().search(pContent);
			} else if (pContent.isEqual(content)) {
				// Element wurde gefunden.
			  return content;				
			} else {	
			  // Dieser Fall sollte nicht auftreten.
				return null;
			}
		}
	}

	/* ----------- Weitere private Methoden -------------- */

	/**
	 * Die Methode liefert denjenigen Baum, dessen linker Nachfolger keinen linken
	 * Nachfolger mehr hat. Es ist also spaeter moeglich, in einem Baum im
	 * rechten Nachfolger den Vorgaenger des linkesten Nachfolgers zu finden.
	 * 
	 */
	private BinarySearchTree<ContentType> ancestorOfSmallRight() {		
		if (getNodeOfLeftSuccessor().left.isEmpty()) {
			return this;
		} else {
			return node.left.ancestorOfSmallRight();
		}
	}

	private BSTNode<ContentType> getNodeOfLeftSuccessor() {
		return node.left.node;
	}

	private BSTNode<ContentType> getNodeOfRightSuccessor() {
		return node.right.node;
	}

}
