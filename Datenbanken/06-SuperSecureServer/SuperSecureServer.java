/**
 * Main Server class
 */
public class SuperSecureServer {


    public static void main(String[] args) {
        new SuperSecureServer();
    }


    // Name der Datenbank
    private static final String dbname = "auth.db";

    // Datenbankverbindung
    private DatabaseConnector db;

    // Sollen Anfragen angezeigt werden?
    private boolean showQueries = false;

    public SuperSecureServer() {
        db = new DatabaseConnector(null, 0, dbname, null, null);
        if (db.getErrorMessage() != null) {
            System.out.println(db.getErrorMessage());
            db = null;
        }

        new SuperSecureLoginForm(this);
    }

    public boolean login(String username, String password) {
        if (db != null) {
            String query = "SELECT username FROM users WHERE username = \"" + username + "\" AND password = \"" + password + "\"";
            if (showQueries) {
                System.out.println(query);
            }
            db.executeStatement(query);

            QueryResult result = db.getCurrentQueryResult();

            if (db.getErrorMessage() != null) {
                System.out.println(db.getErrorMessage());
                return false;
            }

            if (result != null) {
                return result.getRowCount() >= 1;
            }
        }
        return false;
    }

    public void setShowQueries(boolean bln) {
        showQueries = bln;
    }

}
