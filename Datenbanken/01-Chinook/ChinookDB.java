
public class ChinookDB {

    public static final String DB_NAME = "chinook.db";

    private DatabaseConnector dbc;

    private List<Playlist> playlists;

    public static void main(String[] args) {
        ChinookDB cdb = new ChinookDB();
        cdb.printStats();
    }

    public ChinookDB() {
        // Verbindung zur SQLite DB aufbauen.
        // SQLite kann Datenbanken aus Dateien lesen, daher werden die
        // anderen Parameter ignoriert (nur bei Verbindung zu einer
        // DB im Internet/Netzwerk nötig).
        dbc = new DatabaseConnector("", 0, DB_NAME, "", "");

        // Fülle die Liste mit Playlist-Objekten, die passende Track-Objekte enthalten.
        // Die Daten bekommst du aus der Datenbank.
        playlists = new List<Playlist>();
    }

    public void printStats() {
        System.out.println(this.countCustomers() + " customers in the database");
        System.out.println(this.countTracks() + " tracks in the database");
    }

    public int countTracks() {
        // Abfrage ausführen
        dbc.executeStatement("SELECT * FROM tracks");
        // Letztes Abfrageergebnis holen
        QueryResult tracks_result = dbc.getCurrentQueryResult();
        // Anzahl an Zeilen im Ergebnis abfragen und zurückgeben.
        return tracks_result.getRowCount();
    }

    public int countCustomers() {
        // Abfrage ausführen
        dbc.executeStatement("SELECT COUNT(*) FROM customers");
        // Letztes Abfrageergebnis holen
        QueryResult customers_result = dbc.getCurrentQueryResult();
        // Daten des Abfrageerbenis holen (2-dim Array: [Zeile][Spalte])
        // In diesem Fall gibt es nur genau eine Zeile mit einer Spalte, also
        // data := String[1][1]
        String[][] data = customers_result.getData();
        // Ergebnisse sind (hier) immer Strings. Da wir eine Zahl wollen,
        // müssen wir den Streing parsen.
        int count = Integer.parseInt(data[0][0]);
        // Rückgabe
        return count;
    }

}
