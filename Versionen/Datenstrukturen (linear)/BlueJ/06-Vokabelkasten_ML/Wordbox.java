
import java.util.Scanner;

public class Wordbox {

    private List<Word> words;

    /**
     * Legt einen neuen Vokabelkasten an.
     */
    public Wordbox() {
        // TODO: Vokabelkasten initialisieren
        words = new List<Word>();
    }

    /**
     * Fügt dem Vokabelkasten eine neue Vokabel hinzu.
     * @param pEnglish Das englische Wort
     * @param pGerman Die deutsche Übersetzung
     */
    public void addWord( String pEnglish, String pGerman ) {
        words.append(new Word(pEnglish, pGerman));
    }

    /**
     * Startet eine Abfrage der Vokabeln.
     */
    public void starteAbfrage() {
        Scanner konsole = new Scanner(System.in);
        if( !words.isEmpty() ) {
            words.toFirst();
            while( words.hasAccess() ) {
                Word vok = words.getContent();
                System.out.print(vok.getEnglish() + " = ");
                String eingabe = konsole.nextLine();
                if( eingabe.equals(vok.getGerman()) ) {
                    System.out.println("Richtig!");
                } else {
                    System.out.println("Falsch!");
                }
                words.next();
            }
        }
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
