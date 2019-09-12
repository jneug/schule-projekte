import java.io.*;

/**
 * Hilfsklasse um auf einfache Weise mit dem Dateisystem zu interagieren.
 * Die Klasse versteckt die Komplexität hinter einfachen Methoden und
 * ist nutzt so weit es geht die Klassen der Abiturvorgaben NRW.
 */
public class FileSystem {

    /**
     * Klassenmethode um eine Textdatei zeilenweise in eine Liste zu lesen.
     * @param pName
     * @return
     */
    public static List<String> readResource( String pName ) {
        try {
            File f = new File(FileSystem.class.getResource(pName).toURI());
            
            if( f.isFile() ) {
                FileSystem fs;
                fs = new FileSystem(f.getParent());
                return fs.readLines(f.getName());
            } else {
                return new List<String>();
            }
        } catch( Exception ex ) {
            return new List<String>();
        }
    }
    
    
    // Wurzelverzeichnis dieses Dateisystems.
    private String root;

    // Aktuelles Verzeichnis, nachdem das Verzeichnis gewechselt wurde.
    private File currentDir;

    /**
     * Erstellt ein Dateisystem relativ zum angegebenen Wurzelordner.
     * @param pRoot
     */
    public FileSystem( String pRoot ) {
        root = pRoot;
        currentDir = new File(pRoot);
    }

    /**
     * Liest eine Datei zeilenweise in eine Liste von Strings.
     * @param pFilename Dateiname relativ zum aktuellen Verzeichnis.
     * @return
     */
    public List<String> readLines( String pFilename ) {
        File f = new File(currentDir, pFilename);
        if( f.isFile() ) {
            List<String> lines = new List<String>();
            
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                try {
                    String line = br.readLine();
                    
                    while (line != null) {
                        lines.append(line);
                        line = br.readLine();
                    }
                } catch( IOException ex ) {
                
                } finally {
                    br.close();
                }
            } catch( IOException ex ) {
                
            }

            return lines;            
        } else {
            return new List<String>();
        }
    }

    /**
     * Liest eine Datei als String ein.
     * @param pFilename Dateiname relativ zum aktuellen Verzeichnis.
     * @return
     */
    public String readFile( String pFilename ) {
        String content = "";
        
        List<String> lines = readLines(pFilename);
        lines.toFirst();
        while(lines.hasAccess()) {
            content += lines.getContent() + "\n";
        }
        
        return content;
    }
    
}
