import java.util.Date;

public class Qweet {
    
    private static int QWEET_ID = 0;
    
    
    private int id;
    
    private Date posted;
    
    private String text;
    
    private QwitterUser user;
    
    public Qweet(String pText, QwitterUser pUser) {
        this(pText, pUser, new Date());
    }
    
    public Qweet(String pText, QwitterUser pUser, Date pPosted) {
        this.text = pText;
        this.user = pUser;
        this.posted = pPosted;
        
        this.id = ++QWEET_ID;
    }
    
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    
    public QwitterUser getUser() {
        return user;
    }
    
    public Date getPosted() {
        return posted;
    }
    
}
