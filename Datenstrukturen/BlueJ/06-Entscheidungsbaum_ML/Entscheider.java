
public class Entscheider {

    // Wurzel des Entscheidungsbaums
	private BinaryTree<Knoten> root;

	public Entscheider() {
		// Hier den Entscheidungsbaum aufbauen.
        // Vorbereiten der Klassifikationen:
        Klassifikation klasseJa = new Klassifikation("ja");
        Klassifikation klasseNein = new Klassifikation("nein");

        // Von der Wurzel zu den Blättern.
        root = new BinaryTree<Knoten>(
            new Entscheidung("vorhersage", "regnerisch")
        );

        root.getLeftTree().setContent(
            new Entscheidung("feuchtigkeit", "hoch")
        );
        root.getLeftTree().getLeftTree().setContent(klasseNein);
        root.getLeftTree().getRightTree().setContent(klasseJa);

        root.getRightTree().setContent(
            new Entscheidung("vorhersage", "sonnig")
        );
        root.getRightTree().getLeftTree().setContent(
            new Entscheidung("wind", "stark")
        );
        root.getRightTree().getRightTree().setContent(klasseJa);

        root.getRightTree().getLeftTree().getLeftTree().setContent(klasseNein);
        root.getRightTree().getLeftTree().getRightTree().setContent(klasseJa);
	}

    /**
     * Schickt die {@link #getTestdaten() Testdaten} durch den Entscheidungsbaum
     * und gibt die Entscheidungen auf der Kommandozeile aus.
     * @see #testeDatensatz(Datensatz)
     */
    public void testeTestdaten() {
        Datensatz[] testdaten = getTestdaten();
        for( int i = 0; i < testdaten.length; i++ ) {
            Datensatz d = testdaten[i];
            String result = testeDatensatz(d);
            System.out.printf("(%s, %s, %s, %s) = %s",
                d.get("vorhersage"), d.get("temperatur"),
                d.get("feuchtigkeit"), d.get("wind"),
                result);
            System.out.println();
        }
    }

    /**
     * Schickt einen Datensatz durch den Entscheidungsbaum und gibt die
     * Klasse als String zurück.
     * @param pDatensatz Der zu testende Datensatz.
     * @return Die Klasse, der der Datensatz zugeordnet wurde.
     */
	public String testeDatensatz(Datensatz pDatensatz) {
		BinaryTree<Knoten> knoten = root;

		String antwort = "";
		while( antwort.equals("") || antwort.equals("links")
				|| antwort.equals("rechts") ) {
			Knoten e = knoten.getContent();
			antwort = e.entscheide(pDatensatz);

			if( antwort.equals("links") ) {
				// Im linken Teilbaum weiter
				knoten = knoten.getLeftTree();
			} else if( antwort.equals("rechts") ) {
				// Im rechten Teilbaum weiter
				knoten = knoten.getRightTree();
			}
		}
		return antwort;
	}

    /**
     * Erstellt ein Array mit Test-Datensätzen für den Entscheidungsbaum.
     * @return
     */
	public Datensatz[] getTestdaten() {
		String[][] testdaten = new String[][]{
			new String[]{"sonnig", "heiß", "hoch", "schwach"},
			new String[]{"sonnig", "heiß", "hoch", "stark"},
			new String[]{"bewölkt", "heiß", "hoch", "schwach"},
			new String[]{"regnerisch", "mild", "hoch", "schwach"},
			new String[]{"regnerisch", "kalt", "normal", "schwach"},
			new String[]{"regnerisch", "kalt", "normal", "stark"},
			new String[]{"bewölkt", "mild", "hoch", "stark"},
			new String[]{"sonnig", "mild", "hoch", "schwach"},
			new String[]{"sonnig", "kalt", "normal", "schwach"},
			new String[]{"regnerisch", "mild", "normal", "schwach"},
			new String[]{"sonnig", "mild", "normal", "stark"},
			new String[]{"bewölkt", "heiß", "normal", "schwach"},
			new String[]{"bewölkt", "kalt", "normal", "stark"},
			new String[]{"regnerisch", "mild", "hoch", "stark"}};

		Datensatz[] daten = new Datensatz[testdaten.length];
		for( int i = 0; i < testdaten.length; i++ ) {
			daten[i] = new Datensatz();
			daten[i].set("vorhersage", testdaten[i][0]);
			daten[i].set("temperatur", testdaten[i][1]);
			daten[i].set("feuchtigkeit", testdaten[i][2]);
			daten[i].set("wind", testdaten[i][3]);
		}

		return daten;
	}
}
