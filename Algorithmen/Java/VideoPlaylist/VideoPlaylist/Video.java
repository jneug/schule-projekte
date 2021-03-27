import java.awt.Desktop;
import java.net.URI;

/**
 * KLasse für Video-Objekte, die in der Playliste gespeichert werden.
 */
public class Video
{
    private String name;
    
    private URI url;
    
    private String autor;
    
    private int likes;
    
    private int views;
    
    /**
     * Erstellt ein neues Video-Objekt.
     */
    public Video( String pName, String pUrl, String pAutor, int pLikes, int pViews )
    {
        name = pName;
        autor = pAutor;
        likes = pLikes;
        views = pViews;
        
        try {
            this.url = new URI(pUrl);
        } catch(Exception ex) {
            this.url = null;
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public String getUrl() {
        return url.toString();
    }
    
    public int getLikes() {
        return likes;
    }
    
    public int getViews() {
        return views;
    }
    
    /**
     * Öffnet das Video im Browser zum Abspielen.
     */
    public void play() {
        Desktop desktop = Desktop.getDesktop();
        
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
