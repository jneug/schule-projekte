
import java.util.Scanner;

public class QwitterServer extends Server {
    
    // Standardport des Servers
    public static final int PORT = 49999;
    
    public static final int MAX_QWEET_LENGTH = 141;
    
    public static final String USERS_FILE = "users.txt";
    
    public static final String QWEETS_FILE = "qweets.txt";
    
    
    private List<QwitterUser> users;
    
    private BinarySearchTree<QwitterUser> logins;
    
    private List<Qweet> qweets;
    
    private int userCount;
    
    private int qweetCount;
   
    public QwitterServer() {
        this(PORT);
    }
   
    public QwitterServer( int port ) {
        super(port);
        
        userCount = 0;
        qweetCount = 0;
        
        users = new List<QwitterUser>();
        qweets = new List<Qweet>();
        logins = new BinarySearchTree<QwitterUser>();
        
        this.initializeUsers();
        this.initializeQweets();
    }
    
    private void initializeUsers() {
        List<String> lines = FileSystem.readResource(USERS_FILE);
        
        lines.toFirst();
        while( lines.hasAccess() ) {
            String[] parts = lines.getContent().split(" ", 2);
            
            if( parts.length == 2 ) {
                users.append(new QwitterUser(parts[0], parts[1]));
                userCount++;
            }
            
            lines.next();
        }
    }
    
    private void initializeQweets() {
        List<String> lines = FileSystem.readResource(QWEETS_FILE);
        
        lines.toFirst();
        while( lines.hasAccess() ) {
            String[] parts = lines.getContent().split(" ", 2);
            
            if( parts.length == 2 ) {
                QwitterUser u = getUserByName(parts[0]);
                if( u != null ) {
                    qweets.append(new Qweet(parts[1], u));
                    qweetCount++;
                }
            }
            
            lines.next();
        }
    }
    
    private QwitterUser getUserByName( String pName ) {
        users.toFirst();
        while( users.hasAccess() ) {
            QwitterUser u = users.getContent();
            if( u.getName().equals(pName) ) {
                return u;
            }
            users.next();
        }
        return null;
    }
    
    private QwitterUser getUserByAddress( String pClientIP, int pClientPort ) {
        QwitterUser u = new QwitterUser("", "");
        u.setAddress(pClientIP, pClientPort);
        return logins.search(u);
    }
    
    private boolean isLoggedIn( String pClientIP, int pClientPort ) {
        QwitterUser u = new QwitterUser("", "");
        u.setAddress(pClientIP, pClientPort);
        return (logins.search(u) != null);
    }
    
    
    
    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort, "+OK please log in");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        String cmd = pMessage.toUpperCase();
        String args = "";
        
        int space = pMessage.indexOf(" ");
        if( space > -1 ) {
            cmd = pMessage.substring(0, space).toUpperCase();
            args = pMessage.substring(space+1);
        }
        
        if( cmd.equals("LOGIN") ) {
            if( isLoggedIn(pClientIP, pClientPort) ) {
                send(pClientIP, pClientPort, "-ERR you are already logged in");
            } else {
                space = args.indexOf(" ");
                if( space > -1 ) {
                    String username = args.substring(0, space);
                    String password = args.substring(space+1);
                    
                    QwitterUser u = getUserByName(username);
                    if( u != null ) {
                        if( u.hasAddress() ) {
                            send(pClientIP, pClientPort, "-ERR username already in use");
                        } else if( !u.getPassword().equals(password) ) {
                            send(pClientIP, pClientPort, "-ERR wrong credentials");
                        } else {
                            u.setAddress(pClientIP, pClientPort);
                            logins.insert(u);
                            send(pClientIP, pClientPort, "+OK hello, "+u.getName());
                        }
                    } else {
                        send(pClientIP, pClientPort, "-ERR wrong credentials");
                    }
                } else {
                    send(pClientIP, pClientPort, "-ERR wrong credentials");
                }
            }
        } else if( isLoggedIn(pClientIP, pClientPort) ) {
            if( cmd.equals("STAT") ) {
                send(pClientIP, pClientPort, "+OK "+qweetCount+" "+userCount);
            } else if( cmd.equals("LIST") ) {
                int n = qweetCount;
                if( !args.isEmpty() ) {
                    n = Integer.parseInt(args);
                }
                
                sendList(pClientIP, pClientPort, n);
            } else if( cmd.equals("QWEET") ) {
                sendQweet(pClientIP, pClientPort, args);
            } else if( cmd.equals("QUIT") ) {
                closeConnection(pClientIP, pClientPort);
            } else {
                send(pClientIP, pClientPort, "+ERR unknown command");
            }
        } else {
            send(pClientIP, pClientPort, "-ERR you are not logged in");
        }
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
        if( isLoggedIn(pClientIP, pClientPort) ) {
            QwitterUser u = getUserByAddress(pClientIP, pClientPort);
            logins.remove(u);
            u.unsetAddress();
        }
        send(pClientIP, pClientPort, "+OK bye");
    }
    
    
    private void sendList(String pClientIP, int pClientPort, int n ) {
        if( n > qweetCount || n < 0 ) {
            n = qweetCount;
        }
        
        send(pClientIP, pClientPort, "+OK "+n+" qweets following");
        
        qweets.toFirst();
        while( qweets.hasAccess() && n > 0 ) {
            Qweet qweet = qweets.getContent();
            send(pClientIP, pClientPort, qweet.getUser().getName() + " " + qweet.getText());
            qweets.next();
            n--;
        }
        send(pClientIP, pClientPort, ".");
    }
    
    private void sendQweet(String pClientIP, int pClientPort, String text ) {
        if( text.isEmpty() ) {
            send(pClientIP, pClientPort, "-ERR qweet text was empty");
        } else  {
            QwitterUser u = getUserByAddress(pClientIP, pClientPort);
            if( text.length() > MAX_QWEET_LENGTH ) {
                text = text.substring(0, MAX_QWEET_LENGTH);
            }
            Qweet qweet = new Qweet(text, u);
            qweets.append(qweet);
            
            qweetCount++;
            
            send(pClientIP, pClientPort, "+OK new qweet received");
            sendToAll("NEW " + qweet.getUser().getName() + " " + qweet.getText());
        }
    }
    
    public void quit() {
        quit(true);
    }
    
    public void quit( boolean exit ) {
        close();
        
        if( exit) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        QwitterServer qs = new QwitterServer();
        System.out.println("QwitterServer running on 127.0.0.1:"+PORT);
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'quit' to quit");
        String cmd = "";
        while( !cmd.toLowerCase().equals("quit") ) {
            cmd = scanner.next();
        }
        qs.quit();
    }

}
