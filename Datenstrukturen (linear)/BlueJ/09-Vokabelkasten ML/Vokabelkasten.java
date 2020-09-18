
import java.util.Scanner;

public class Vokabelkasten {
    
    private List<Vokabel> vokabeln;

    public Vokabelkasten() {
        vokabeln = new List<Vokabel>();
    }

    public void neueVokabel( String pEn, String pDe ) {
        vokabeln.append(new Vokabel(pEn, pDe));
    }
    
    public void starteAbfrage() {
        Scanner konsole = new Scanner(System.in);
        if( !vokabeln.isEmpty() ) {
            vokabeln.toFirst();
            while( vokabeln.hasAccess() ) {
                Vokabel vok = vokabeln.getContent();
                System.out.print(vok.getEn() + " = ");
                String eingabe = konsole.nextLine();
                if( eingabe.equals(vok.getDe()) ) {
                    System.out.println("Richtig!");
                } else {
                    System.out.println("Falsch!");
                }
                vokabeln.next();
            }
        }
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
        List<String> lines = FileSystem.readResource("vokabeln.txt");
        
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
