


public class MailClient {
     
    public static final String POP3_SERVER = "127.0.0.1";

    public static final int POP3_PORT = 110;
    
    public static final String POP3_USER = "user1";
    
    public static final String POP3_PASS = "user1";
    
    /**
     * Holt die nächste E-Mail per POP3-Protokoll vom
     * Server und gibt sie auf der Kommandozeile aus.
     */
    public void printNextEmail() {
        // Verbindung herstellen
        Connection con = new Connection(POP3_SERVER, POP3_PORT);
        String mes; // Speicher für empfangene Nachrichten(zeilen)
        
        mes = con.receive();  // Begrüssungsnachricht
        if( mes == null || mes.startsWith("+ERR") ) {
          System.err.println("Der POP3-Server reagiert nicht!");
          con.close();
          return; // Verbindung schließen und Methode beenden
        }
        
        // Anmeldung am Server
        con.send("USER " + POP3_USER);
        mes = con.receive();  // Auf Antwort warten, "+OK" erwartet
        if( mes == null || mes.startsWith("+ERR") ) {
          System.err.println("Benutzername nicht akzeptiert!");
          con.close();
          return;
        }
        
        // Passwort senden
        // Arbeiten sie hier viel mit Copy&Paste!
        
        
        // Statistik abfragen
        
        
        // Statistik verarbeiten
        int mailCount = getMessageCount(mes);
        if( mailCount == 0 ) {
          System.out.println("Keine E-Mails auf dem Server vorhanden.");
          con.close();
          return;
        }
        
        // Erste Mail abrufen
        
        
        
        // Die Nahricht besteht aus Textzeilen, die mit einer 
        // Zeile abgeschlossen werden, die nur aus einem Punkt
        // besteht.
        // while( !msg.equals(".") ) {
        
        
        // Verbindung beenden
        con.send("QUIT");
        con.close();    
    }
    
    /**
     * Löscht genau eine E-Mail vom Server.
     */
    public void deleteNextEmail() {
        
    }
    
    private int getMessageCount( String pStatMessage ) {
        int firstSpace  = pStatMessage.indexOf(' '); // 1. Leerzeichen finden
        int secondSpace = pStatMessage.indexOf(' ', firstSpace+1); // 2. Leerzeichen fidnen
        int mailCount = Integer.parseInt(pStatMessage.substring(firstSpace+1, secondSpace));
        return mailCount;
    }
}
