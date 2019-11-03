package util;

import java.io.*;

/**
 * Hilfsklasse um auf einfache Weise mit dem Dateisystem zu interagieren.
 * Die Klasse versteckt die Komplexität hinter einfachen Methoden und
 * ist nutzt so weit es geht die Klassen der Abiturvorgaben NRW.
 * @version 1.1 (2019-09-13)
 * @author J. Neugebauer <schule@neugebauer.cc>
 */
public class FileSystem {

    /**
     * Klassenmethode um eine Textdatei im gleichen Verzeichnis
     * wie die FileSystem-Klasse zeilenweise in eine Liste zu lesen.
     * Im Fehlerfall oder wenn die Datei nicht existiert ist das Ergebnis
     * eine leere Liste.
     *
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
     *
     * @param pRoot
     */
    public FileSystem( String pRoot ) {
        root = new File(pRoot);
        root = root.getAbsoluteFile();
        currentDir = root;
    }

    /**
     * Erstellt ein Dateisystem relativ zum angegebenen Wurzelordner.
     *
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
        root = new File(".").getAbsoluteFile().getParentFile();
        currentDir = root;
    }

    /**
     * Liest eine Datei zeilenweise in eine Liste von Strings. Existiert die
     * Datei nicht oder kann nicht gelesen werden wird eine leere Liste erzeugt.
     *
     * @param pFilename Dateiname relativ zum aktuellen Verzeichnis.
     * @return
     */
    public List<String> readLines( String pFilename ) {
        File f = new File(currentDir, pFilename);
        if( f.exists() && f.isFile() && f.canRead() ) {
            List<String> lines = new List<String>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                try {
                    String line = br.readLine();

                    while( line != null ) {
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
     * Liest eine Datei als String ein. Existiert die
     * Datei nicht oder kann nicht gelesen werden wird ein
     * leerer String zurück gegeben.
     *
     * @param pFilename Dateiname relativ zum aktuellen Verzeichnis.
     * @return
     */
    public String readFile( String pFilename ) {
        String content = "";

        List<String> lines = readLines(pFilename);
        lines.toFirst();
        while( lines.hasAccess() ) {
            content += lines.getContent() + "\n";
        }

        return content;
    }

    /**
     * Gibt eine Liste von Strings zurück, die die Namen aller Dateien
     * im aktuellen Verzeichnis enthält.
     *
     * @return
     */
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

    /**
     * Gibt eine Liste von Strings zurück, die die Namen aller Ordner
     * im aktuellen Verzeichnis enthält.
     *
     * @return
     */
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

    /**
     * Gibt den absoluten Pfad des aktuellen Verzeichnisses
     * zurück.
     *
     * @return
     */
    public String getPath() {
        return currentDir.getAbsolutePath();
    }

    /**
     * Gibt den absoluten Pfad des Wurzelverzeichnisses zurück.
     *
     * @return
     */
    public String getRootPath() {
        return root.getAbsolutePath();
    }

    /**
     * Navigiert in das Elternverzeichnis des aktuellen Verzeichnisses,
     * wenn möglich. Falls das aktuelle Verzeichnis das Wurzelverzeichnis
     * ist oder es kein Elternverzeichnis gibt, ändert sich das aktuelle
     * Verzeichnis nicht.
     *
     * @return <code>true</code> - Verzeichnis geändert, <code>false</code> - Navigation nicht möglich
     */
    public boolean cdUp() {
        if( currentDir.equals(root) ) {
            return false;
        } else {
            currentDir = currentDir.getParentFile().getAbsoluteFile();
            return true;
        }
    }

    /**
     * Navigiert das aktuelle Verzeichnis in den angegebenen Pfad relativ
     * zum aktuellen Verzeichnis. Ist der neue Pfad ein existierender Pfad
     * innerhalb des Wurzelverzeichnisses, dann wird das aktuelle Verzeichnis
     * gewechselt, sonst nicht.
     *
     * @param pPath
     * @return <code>true</code> - Verzeichnis geändert, <code>false</code> - Navigation nicht möglich
     */
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

    /**
     * Erstellt eine leere Datei am angegebene Ort. Gibt es schon eine Datei mit
     * dem Namen oder existieren keine Schreibrechte passiert nichts.
     *
     * @param pName
     * @return <code>true</code> - Datei erzeugt, <code>false</code> - Aktion nicht möglich
     */
    public boolean createFile( String pName ) {
        return createFile(pName, "");
    }

    public boolean createFile( String pName, String content ) {
        File newFile = new File(currentDir, pName);
        try {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        } catch( Exception e ) {
            return false;
        }

        if( content.length() > 0 ) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                writer.write(content);
                writer.flush();
            } catch( IOException e ) {
                return false;
            }
        }

        return true;
    }

    /**
     * Erstellt ein neues Verzeichnis mit dem angegebenen Namen relativ
     * zum aktuellen Verzeichnis.
     *
     * @param pName
     * @return <code>true</code> - Verzeichnis erzeugt, <code>false</code> - Aktion nicht möglich
     */
    public boolean createDirectory( String pName ) {
        File newFile = new File(currentDir, pName);
        try {
            newFile.mkdirs();
        } catch( Exception e ) {
            return false;
        }

        return true;
    }

}
