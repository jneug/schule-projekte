
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

    /**
     * Gibt die Anzahl Tracks in der Datenbank zurück. Gab es bei der Verbindung
     * einen Fehler, dann wird {@code 0} zurückgegeben.
     * @return Die Anzahl der Kunden.
     */
    public int countTracks() {
        // Abfrage ausführen
        dbc.executeStatement("SELECT * FROM tracks");
        // Letztes Abfrageergebnis holen
        QueryResult tracks_result = dbc.getCurrentQueryResult();
        // Anzahl an Zeilen im Ergebnis abfragen und zurückgeben, falls es keinen
        // Fehler gab (dann ist das QueryResult == null).
        if( tracks_result != null ) {
            return tracks_result.getRowCount();
        } else {
            return 0;
        }
    }

    /**
     * Gibt die Anzahl Kunden in der Datenbank zurück. Gab es bei der Verbindung
     * einen Fehler, dann wird {@code 0} zurückgegeben.
     * @return Die Anzahl der Kunden.
     */
    public int countCustomers() {
        // Abfrage ausführen
        dbc.executeStatement("SELECT COUNT(*) FROM customers");
        // Letztes Abfrageergebnis holen
        QueryResult customers_result = dbc.getCurrentQueryResult();
        // Daten des Abfrageerbenis holen (2-dim Array: [Zeile][Spalte])
        // In diesem Fall gibt es nur genau eine Zeile mit einer Spalte, also
        // data := String[1][1]
        // Das Ergebnis kann aber auch leer sein, oder null, falls es einen Fehler gab.
        // Daher prüfen wir dies.
        if( customers_result != null && customers_result.getRowCount() > 0 ) {
            String[][] data = customers_result.getData();
            // Ergebnisse sind (hier) immer Strings. Da wir eine Zahl wollen,
            // müssen wir den Streing parsen.
            int count = Integer.parseInt(data[0][0]);
            // Rückgabe
            return count;
        } else {
            return 0;
        }
    }

    /**
     * Lädt den Track mit der angegebenen {@var pTrackID} aus der Datenbank
     * und gibt das {@link Track}-Objekt zurück. Gab es bei der Verbindung einen
     * Fehler oder gibt es keinen Track mit der passenden ID, dann wird
     * {@code null} zurückgegeben.
     * @param pTrackID Die ID des Tracks in der Datenbank.
     * @return Der geladene Track.
     */
    public Track loadTrack( int pTrackID ) {
        /*aufg* <2
        // TODO: Implementieren
        return null
        *aufg*/
        //ml* =2
        // Datenbankabfrage ausführen und Result holen
        dbc.executeStatement("SELECT TrackId,Name,Milliseconds,Bytes,UnitPrice FROM tracks WHERE TrackId = " + pTrackID);
        QueryResult track_result = dbc.getCurrentQueryResult();
        // Auf Fehler prüfen
        if( track_result != null && track_result.getRowCount() > 0 ) {
            String[][] data = track_result.getData();
            // Daten von String in passende Datentypen umwandeln
            int id = Integer.parseInt(data[0][0]);
            String name = data[0][1];
            long milliseconds = Long.parseLong(data[0][2]);
            long bytes = Long.parseLong(data[0][3]);
            double unitPrice = Double.parseDouble(data[0][4]);
            // Track-Objekt erstellen.
            return new Track(id, name, milliseconds, bytes, unitPrice);
        } else {
            // Fehler! null zurückgeben.
            return null;
        }
        //*ml
    }

    /**
     * Lädt die Playlist mit der angegbeen {@var pPlaylistID} inklusive aller
     * zugehörigen {@link Track}s aus der Datenbank und gibt das
     * {@link Playlist}-Objekt zurück. Gab es bei der Verbindung einen
     * Fehler oder gibt es keinen Playlist mit der passenden ID, dann wird
     * {@code null} zurückgegeben.
     * @param pPlaylistID Die ID der zu ladenden Playlist.
     * @return Die geladene Playlist.
     */
    public Playlist loadPlaylist( int pPlaylistID ) {
        /*aufg*
        // TODO: Implementieren
        return null
        *aufg*/
        //ml*
        return null;
        //*ml
    }

    /**
     * Lädt alle vorhandenen Playlists in die Liste {@var playlists}.
     */
    public void loadPlaylists() {
        /*aufg*
        // TODO: Implementieren
        return null
        *aufg*/
        //ml*
        //*ml
    }

}
