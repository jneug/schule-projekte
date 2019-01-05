/**
 * Vorlage für den QwitterClient. Als Unterklasse des Clients wird
 * die Verbindung zum Server bei erstellen des Objekts sofort aufgebaut.
 * 
 * Außerdem wird eine GUI erstellt, in der neue Qweets dargestellt werden können.
 * Dazu stehen auf der "gui" folgende Methoden zur Verfügung:
 * 
 * - gui.addQweet(String username, String text)
 *      Fügt der GUI einen neuen Qweet hinzu.
 * - gui.clear()
 *      Entfernt alle Qweets aus der GUI und leert diese.
 *      
 * Wichtig: Der Konstruktor sollte nicht verändert werden, da sonst
 * ggf. die GUI nicht korrekt initialisiert wird.
 */
public class QwitterClient extends Client
{
    // Standardport des Servers
    private static final int PORT = 49999;
    
    private QwitterClientGUI gui;
    
    private int state = 0;
    
    // Konstruktor, der direkt zum lokalen Server verbindet
    public QwitterClient()
    {
        this("127.0.0.1");
    }
    
    // Konstruktor, der zum Server bei einer IP verbindet
    public QwitterClient( String ip )
    {
        super(ip, PORT);
        
        gui = new QwitterClientGUI();
    }
    
    // Login beim Server
    public void login( String pUsername, String pPassword ) {
        send("LOGIN "+pUsername+" "+pPassword);
    }
    
    // Einen Qweet absenden
    public void qweet( String text ) {
        send("QWEET "+text);
    }
    
    // Vom Server abmelden
    public void quit() {
        send("QUIT");
        close();
    }

    // Verarbeitung eingehender Nachrichten
    public void processMessage(String pMessage)
    {
        System.out.println(pMessage);
        if( pMessage.startsWith("NEW") ) {
            String[] parts = pMessage.split(" ", 3);
            gui.addQweet(parts[1], parts[2]);
        } else {
            switch( state ) {
                case 0:
                    if( pMessage.startsWith("+OK") ) {
                        state = 1;
                    }
                    break;
                case 1:
                    if( pMessage.startsWith("+OK") ) {
                        state = 2;
                        send("LIST");
                    }
                    break;
                case 2:
                    if( pMessage.startsWith("+OK") ) {
                        state = 3;
                    }
                    break;
                case 3:
                    if( pMessage.equals(".") ) {
                        state = 4;
                    } else {
                        String[] parts = pMessage.split(" ", 2);
                        gui.addQweet(parts[0], parts[1]);
                    }
                    break;
            }
        }
    }
}
