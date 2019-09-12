import java.io.*;

/**
 * Hilfsklasse um auf einfache Weise mit dem Dateisystem zu interagieren.
 * Die Klasse versteckt die Komplexität hinter einfachen Methoden und
 * ist nutzt so weit es geht die Klassen der Abiturvorgaben NRW.
 */
public class FileSystem {

    /**
     * Klassenmethode um eine Textdatei im gleichen Verzeichnis
     * wie die FileSystem-Klasse zeilenweise in eine Liste zu lesen.
     * Im Fehlerfall oder wenn die Datei nicht existiert ist das Ergebnis
     * eine leere Liste.
     * @param pFilename
     * @return
     */
    public static List<String> getFileContents( String pFilename ) {
        try {
            File f = new File(FileSystem.class.getResource(pFilename).toURI());
            
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
    private File root;

    // Aktuelles Verzeichnis, nachdem das Verzeichnis gewechselt wurde.
    private File currentDir;

    /**
     * Erstellt ein Dateisystem relativ zum angegebenen Wurzelordner.
     * @param pRoot
     */
    public FileSystem( String pRoot ) {
        root = new File(pRoot);
        root = root.getAbsoluteFile();
        currentDir = root;
    }

    /**
     * Erstellt ein Dateisystem relativ zum angegebenen Wurzelordner.
     * @param pRoot
     */
    public FileSystem( File pRoot ) {
        root = pRoot.getAbsoluteFile();
        currentDir = root;
    }

    /**
     * Erstellt ein Dateisystem relativ zum aktuellen Arbeitsverzeichnis.
     */
    public FileSystem() {
        File workingDir = new File(FileSystem.class.getResource(".").toString());
        root = workingDir.getAbsoluteFile();
        currentDir = root;
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

    public List<String> getFileList() {
        List<String> files = new List<String>();

        currentDir.listFiles(new FileFilter() {
            @Override
            public boolean accept( File f ) {
                if( f.isFile() && !f.isHidden() ) {
                    files.append(f.getName());
                }
                return false;
            }
        });

        return files;
    }

    public List<String> getDirectoryList() {
        List<String> directories = new List<String>();

        currentDir.listFiles(new FileFilter() {
            @Override
            public boolean accept( File f ) {
                if( f.isDirectory() && !f.isHidden() ) {
                    directories.append(f.getName());
                }
                return false;
            }
        });

        return directories;
    }

    public String getPath() {
        return currentDir.getAbsolutePath();
    }

    public boolean cdUp() {
        if( currentDir.equals(root) ) {
            return false;
        } else {
            currentDir = currentDir.getParentFile().getAbsoluteFile();
            return true;
        }
    }

    public boolean cd( String pPath ) {
        File newDir = new File(currentDir, pPath);
        if( newDir.exists() && newDir.isDirectory()
                && newDir.getAbsolutePath().startsWith(root.getAbsolutePath()) ) {
            currentDir = newDir;
            return true;
        } else {
            return false;
        }
    }

}
