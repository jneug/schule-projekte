
/**
 * Klasse für einen Besitzer
 *
 * Ein Besitzer hat immer einen Namen und genau einen Motorroller.
 * Er kann Geld bekommen und weiß, ob er einen Führerschein hat.
 *
 * @author J. Neugebauer
 * @version 1.1
 */
public class Besitzer {

	// Objektvariablen deklarieren
	private String name;

	private double geld;

	private boolean fuehrerschein;

	private Motorroller meinRoller;

	/**
	 * Konstruktor für Objekte der Klasse Besitzer
	 */
	public Besitzer(String pName, double pGeld, boolean pFuehrerschein, Motorroller pMeinRoller) {
		// Objektvariablen initialisieren
		name = pName;
		geld = pGeld;
		fuehrerschein = pFuehrerschein;
		meinRoller = pMeinRoller;
	}

	public String getName() {
		return name;
	}

	public double getGeld() {
		return geld;
	}

	public boolean hatFuehrerschein() {
		return fuehrerschein;
	}

	/**
	 * Gibt die Referenz auf das Objekt vom Typ "Motorroller" zurück.
	 */
	public Motorroller getMeinenRoller() {
		return meinRoller;
	}

	/**
	 * Ändert die Motorroller-Referenz auf ein neues Objekt vom Typ
	 * "Motorroller".
	 */
	public void setMeinenRoller( Motorroller pNeuerRoller ) {
		meinRoller = pNeuerRoller;
	}

	public void taschengeldBekommen( double pBetrag ) {
		if( pBetrag > 0 ) {
			geld = geld + pBetrag;
		}
	}

	/**
	 * Tankt den zugeordneten Roller um die angegebene Menge (in Litern) auf.
	 * Dabei wird zunächst geprüft wie viel Platz noch im Tank ist
	 * und nur die Menge getankt, die auch noch in den Tank passt.
	 * Für die getankte Menge müssen pro Liter 1.5 Euro gezahlt werden, die
	 * vom vorhandenen Geld abgezogen werden.
	 */
	public void tankeRoller( double pMenge ) {
		meinRoller.tanke(pMenge);
		geld = geld - 1.5*pMenge;

		// TODO:
		// - Teste die Methode mit verschiedenen Eingaben. Probier
		//      auch Werte aus, die eigentlich nicht logisch sind (z.B.
		//      negative Mengen).
		// - Diese Methode wurde nicht korrekt nach der
		//      Spezifikation (im Kommentar oben) implementiert.
		//      Welche Fehler können auftreten?
		// - Korrigiere so viele Fehler wie möglich.
		// - Bei dieser Methode ist auch die Spezifikation fehlerhaft.
		//      Welche möglichen Objektzustände werden dort nicht
		//      beachtet?
		// - Ergänze den Kommentar zu einer möglichst exakten Beschreibung.
	}

	/**
	 * Fährt den Roller um die angegeben Strecke (in km). Der Besitzer
	 * prüft zunächst, ob genug Benzin im Tank ist für die Strecke, und fährt
	 * nur dann los. Er prüft dies, indem er ungefähr den Verbrauch des Rollers
	 * abschätzt.
	 */
	public void fahreRoller( int pStrecke ) {
		if( meinRoller.getTankinhalt() >= pStrecke ) {
			meinRoller.fahre(pStrecke);
		}




		// TODO:
		// - Der Besitzer schätzt nur grob den Verbrauch seines Rollers ab.
		//      Wie macht er das?
		// - Wie könnten die Klassn sinnvoll ergänzt werden, um eine genauere
		//      Rechnung pro Roller zu ermöglichen?
		// - Implementiere eure Idee.
	}
}
