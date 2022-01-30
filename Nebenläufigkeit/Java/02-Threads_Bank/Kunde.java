/**
 * Ein Kunde hat Zugriff auf ein {@link Konto} und hebt von diesem Geld ab.
 * <p>
 * Jeder Kunde ist ein eigener {@link Thread}, da die Kunden unabhängig
 * voneinander in unregelmäßigen Abständen versuchen, Geld vom Konto zu
 * bekommen.
 */
public class Kunde extends Thread {

    /**
     * Der Name des Kunden.
     */
    private String name;

    /**
     * Das Konto des Kunden.
     */
    private Konto konto;

    /**
     * Der Betrag, den der Kunde versucht abzuheben.
     */
    private int betrag;

    /**
     * Erstellt den Kunden
     *
     * @param pName   Der Name des Kunden.
     * @param pKonto  Das verknüpfte Konto.
     * @param pBetrag Der Betrag, den der Kunde jeweils abbuchen möchte.
     */
    public Kunde( String pName, Konto pKonto, int pBetrag ) {
        super(pName);
        name = pName;
        konto = pKonto;
        betrag = pBetrag;
    }

    /**
     * Hauptmethode des {@link Thread}. Der Kunde versucht immer wieder, seinen
     * Betrag vom Konto abzuheben, bis kein Geld meht vorhanden ist.
     */
    @Override
    public void run() {
        for( int i = 0; i < 10; i++ ) {
            System.out.println("KUNDE: " + name + " möchte " + betrag);
            if( konto.abheben(betrag, this) ) {
                System.out.println("KUNDE: " + name + " hat " + betrag + " erhalten");
            }
        }
    }

}
