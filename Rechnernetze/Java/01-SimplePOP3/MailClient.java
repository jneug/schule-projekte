


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
        if( mes == null || mes.startsWith("-ERR") ) {
          System.err.println("Der POP3-Server reagiert nicht!");
          con.close();
          return; // Verbindung schließen und Methode beenden
        }

        // Anmeldung am Server
        con.send("USER " + POP3_USER);
        mes = con.receive();  // Auf Antwort warten, "+OK" erwartet
        if( mes == null || mes.startsWith("-ERR") ) {
          System.err.println("Benutzername nicht akzeptiert!");
          con.close();
          return;
        }

        con.send("PASS " + POP3_PASS);
        mes = con.receive();  // Auf Antwort warten, "+OK" erwartet
        if( mes == null || mes.startsWith("-ERR") ) {
          System.err.println("Falsches Passwort!");
          con.close();
          return;
        }

        // Statistik abfragen
        con.send("STAT");
        mes = con.receive();
        if( mes == null || mes.startsWith("-ERR") ) {
          System.err.println("Fehler bei der STAT Anfrage.");
          con.close();
          return;
        }

        // Statistik verarbeiten
        int mailCount = getMessageCount(mes);
        if( mailCount == 0 ) {
          System.out.println("Keine E-Mails auf dem Server vorhanden.");
          con.close();
          return;
        }

        // Erste Mail abrufen
        con.send("RETR 1");
        mes = con.receive();
        if( mes == null || mes.startsWith("-ERR") ) {
          System.err.println("Fehler beim Empfangen der Mail.");
          con.close();
          return;
        }

        while( !mes.equals(".") ) {
            System.out.println(mes);
            mes = con.receive();
        } // end of while

        con.send("QUIT");
        con.close();
    }

    /**
     * Löscht genau eine E-Mail vom Server.
     */
    public void deleteNextEmail() {
        Connection con = new Connection(POP3_SERVER, POP3_PORT);
        con.send("DELE 1");
        con.receive();
        con.send("QUIT");
        con.close();
    }

    private int getMessageCount( String pStatMessage ) {
        int firstSpace  = pStatMessage.indexOf(' '); // 1. Leerzeichen finden
        int secondSpace = pStatMessage.indexOf(' ', firstSpace+1); // 2. Leerzeichen fidnen
        int mailCount = Integer.parseInt(pStatMessage.substring(firstSpace+1, secondSpace));
        return mailCount;
    }
}
