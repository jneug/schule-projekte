import java.util.HashMap;

public class LiteraturServer extends Server {

    private List<String> auth;

    private FileSystem folders;

    private HashMap<String, String> sessions;

    public LiteraturServer( int port ) {
        super(port);
        auth = new List<String>();
        auth.append("user1:pass1");
        auth.append("user2:pass2");
        auth.append("user3:pass3");
        auth.append("user4:pass4");
        auth.append("user5:pass5");
        auth.toFirst();

        sessions = new HashMap<>();

        folders = new FileSystem("./Literatur");
    }

    public void processNewConnection( String pClientIP, int pClientPort ) {
        // Bestätigung bei erfolgreicher Verbindung
        send(pClientIP, pClientPort, "+OK you are connected");
    }

    public void processMessage( String pClientIP, int pClientPort, String pMessage ) {
        /*aufg*
        if( pMessage.startsWith("LOGIN") ) {
            if( sessions.containsKey())
            pMessage = pMessage.substring(6);

            int empty = pMessage.indexOf(" ");
            if( empty > -1 ) {
                String username = pMessage.substring(0, empty);
                String password = pMessage.substring(empty + 1);

                // Anmeldung prüfen
                if( login(username, password) ) {
                    // TODO: Anmeldung des Nutzers merken
                    send(pClientIP, pClientPort, "+OK welcome " + username);
                } else {
                    send(pClientIP, pClientPort, "-ERR wrong credentials");
                }
            } else {
                send(pClientIP, pClientPort, "-ERR wrong credentials");
            }
        } else if( pMessage.startsWith("QUIT") ) {
            send(pClientIP, pClientPort, "+OK goodbye");
            closeConnection(pClientIP, pClientPort);
        } else {
            // Nachrichten werden wieder zurück gesendet (Echo)
            // TODO: Verhalten des Servers implementieren
            //       GGf. macht es Sinn, die einzelnen Befehle in eigene
            //       Methoden auszulagern.
            send(pClientIP, pClientPort, "+OK received " + pMessage);
        }
        *aufg*/
        //ml*
        // Eindeutige ID aus IP+Port
        String clientId = String.format("%s:%d", pClientIP, pClientPort);

        if( pMessage.startsWith("LOGIN") ) {
            // Ist der CLient schon angemeldet?
            if( sessions.containsKey(clientId) ) {
                send(pClientIP, pClientPort, "-ERR already logged in");
            } else {
                pMessage = pMessage.substring(6);

                // Nachricht aufteilen
                int space = pMessage.indexOf(" ");
                if (space > -1) {
                    String username = pMessage.substring(0, space);
                    String password = pMessage.substring(space + 1);

                    // Anmeldung prüfen
                    if (login(username, password)) {
                        sessions.put(clientId, username);
                        send(pClientIP, pClientPort, "+OK welcome " + username);
                    } else {
                        send(pClientIP, pClientPort, "-ERR wrong credentials");
                    }
                } else {
                    send(pClientIP, pClientPort, "-ERR wrong credentials");
                }
            }
        } else if( pMessage.startsWith("QUIT") ) {
            send(pClientIP, pClientPort, "+OK goodbye");
            closeConnection(pClientIP, pClientPort);
        } else {
            if( pMessage.startsWith("CD") ) {
                String dir = pMessage.substring(3);
                if( folders.cd(dir) ) {
                    send(pClientIP, pClientPort, "+OK directory changed");
                } else {
                    send(pClientIP, pClientPort, "-ERR requested directory does not exist");
                }
            } else if( pMessage.startsWith("UP") ) {
                if( folders.cdUp() ) {
                    send(pClientIP, pClientPort, "+OK directory changed");
                } else {
                    send(pClientIP, pClientPort, "-ERR already in the root directory");
                }
            } else if( pMessage.startsWith("LIST") ) {
                String type = pMessage.substring(5);

                if( type.equals("FILE") ) {
                    List<String> files = folders.getFileList();
                    send(pClientIP, pClientPort, "+OK here are the files");
                    files.toFirst();
                    while( files.hasAccess() ) {
                        send(pClientIP, pClientPort, files.getContent());
                        files.next();
                    }
                    send(pClientIP, pClientPort, ".");
                } else {
                    List<String> dirs = folders.getDirectoryList();
                    send(pClientIP, pClientPort, "+OK here are the directories");
                    dirs.toFirst();
                    while( dirs.hasAccess() ) {
                        send(pClientIP, pClientPort, dirs.getContent());
                        dirs.next();
                    }
                    send(pClientIP, pClientPort, ".");
                }
            } else if( pMessage.startsWith("READ") ) {
                String file = pMessage.substring(5);

                List<String> lines = folders.readLines(file);
                if( lines.isEmpty() ) {
                    send(pClientIP, pClientPort, "-ERR file not found");
                } else {
                    send(pClientIP, pClientPort, "+OK here is your file");
                    lines.toFirst();
                    while( lines.hasAccess() ) {
                        send(pClientIP, pClientPort, lines.getContent());
                        lines.next();
                    }
                    send(pClientIP, pClientPort, ".");
                }
            }
        }
        //*ml
    }

    public void processClosingConnection( String pClientIP, int pClientPort ) {
        // Client hat Verbindung geschlossen
    }

    /**
     * Prüft, ob Nutzername / Passwort ein gültiger Login in der Auth-Liste sind.
     */
    public synchronized boolean login( String pUsername, String pPassword ) {
        boolean loginOk = false;
        while( auth.hasAccess() ) {
            if( auth.getContent().equals(pUsername + ":" + pPassword) ) {
                loginOk = true;
                break;
            }
            auth.next();
        }
        auth.toFirst();

        return loginOk;
    }

}
