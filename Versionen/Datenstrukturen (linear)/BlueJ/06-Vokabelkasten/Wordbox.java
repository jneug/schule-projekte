
import java.util.Scanner;

public class Wordbox {

    private List<Word> words;

    /**
     * Legt einen neuen Vokabelkasten an.
     */
    public Wordbox() {
        // TODO: Vokabelkasten initialisieren
    }

    /**
     * Fügt dem Vokabelkasten eine neue Vokabel hinzu.
     * @param pEnglish Das englische Wort
     * @param pGerman Die deutsche Übersetzung
     */
    public void addWord( String pEnglish, String pGerman ) {
        System.out.printf("Neue Vokabel: %s\n", pEnglish);
    }

    /**
     * Startet eine Abfrage der Vokabeln.
     */
    public void starteAbfrage() {
        Scanner konsole = new Scanner(System.in);
    }

    /**
     * Lädt Vokabeln aus der Datei "words.txt" im Projektordner.
     * Die Vokabeln müssen das Format "en=de" haben.
     * Pro Zeile steht eine Vokabel.
     * z.B.:
     * tree=Baum
     * street=Straße
     */
    public void loadWordsFile() {
        List<String> lines = FileSystem.readResource("words.txt");

        lines.toFirst();
        while( lines.hasAccess() ) {
            String[] parts = lines.getContent().split("=", 2);

            if( parts.length == 2 ) {
                addWord(parts[0], parts[1]);
            }

            lines.next();
        }
    }

}
