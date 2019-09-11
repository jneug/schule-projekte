import java.io.*;

public class FileSystem {
    
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
    
    
    
    private String root;
    
    private File currentDir;
    
    public FileSystem( String pRoot ) {
        root = pRoot;
        currentDir = new File(pRoot);
    }
    
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
