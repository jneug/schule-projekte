import java.util.Random;

/**
 * Ein sechseitiger Würfel.
 */
public class Wuerfel {

    // Referenz auf ein Random-Objekt, um
    // Zufallszahlen generieren zu können.
    private Random zufall;

    // Nummer des Würfels im Spiel.
    private int nummer;

    // Die Augenzahl die "oben" liegt.
    private int augenzahl;

    /**
     * Konstruktor für einen Würfel. Erzeugt einen Würfel
     * mit der angegebenen Nummer und "wirft" ihn einmal, 
     * um festzustellen, welche Augenzahl "oben" liegt.
     */
    public Wuerfel(int pNummer) {
        nummer = pNummer;
        // Neues Random-Objekt erzeugen.
        zufall = new Random();
        werfen();
    }

    /**
     * "Wirft" diesen Würfel. Die geworfene Augenzahl wird danach 
     * mit getAugenzahl() abgerufen.
     */
    public void werfen() {
        // Die Methode nextInt() wird auf dem referenzierten 
        // Random-Objekt aufgerufen. Das Ergebnis ist eine
        // Ganzzahl zwischen 0 und 5. Daher wird noch 1 addiert
        // um zwischen 1 und 6 zu liegen.
        augenzahl = zufall.nextInt(6)+1;
    }

    /**
     * Gibt die Augenzahl zurück, die gerade "oben" liegt.
     */
    public int getAugenzahl() {
        return augenzahl;
    }
}
