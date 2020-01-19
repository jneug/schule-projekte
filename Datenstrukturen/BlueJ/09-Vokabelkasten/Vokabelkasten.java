
import java.util.Scanner;

public class Vokabelkasten {
    
    /**
     * Legt einen neuen Vokabelkasten an.
     */
    public Vokabelkasten() {
        
    }
    
    /**
     * Erstellt eine neue VOkabel im Vokabelkasten.
     */
    public void neueVokabel( String pEnglisch, String pDeutsch ) {
        System.out.println(pEnglisch);
    }
    
    /**
     * Startet eine Abfrage der Vokabeln.
     */
    public void starteAbfrage() {
        Scanner konsole = new Scanner(System.in);
    }
    
    
    
    
    /**
     * Lädt Vokabeln aus der Datei "vokabeln.txt" im Projektordner.
     * Die Vokabeln müssen das Format "en=de" haben.
     * Pro Zeile steht eine Vokabel.
     * z.B.:
     * tree=Baum
     * street=Straße
     */
    public void vokabeldateiLaden() {
        List<String> lines = FileSystem.getFileContents("vokabeln.txt");
        
        lines.toFirst();
        while( lines.hasAccess() ) {
            String[] parts = lines.getContent().split("=", 2);
            
            if( parts.length == 2 ) {
                neueVokabel(parts[0], parts[1]);
            }
            
            lines.next();
        }
    }
    
}
