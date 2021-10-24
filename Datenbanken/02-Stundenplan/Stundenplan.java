import javax.swing.*;
import java.awt.*;

public class Stundenplan implements SelectionListener {

    /**
     * Main-Methode um das Programm (außerhalb von BlueJ) zu starten.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Setzen des "Look & Feel" des Programms
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        // Programm starten
        new Stundenplan();
    }

    // Instanz des GUI des Programms
    private StundenplanGUI gui;

    // Instanz der Datenbankverbindung
    private DatabaseConnector dbc;

    /**
     * Konstruktor des Hauptprogramms
     */
    public Stundenplan() {
        // Erstellen des GUIs
        gui = new StundenplanGUI();

        // Aufbau der Datenbankverbindung
        dbc = new DatabaseConnector("", 0, "stundenplan.db", "", "");
        QueryResult r;

        // Lehrer abfragen und ein Auswahlfeld erstellen, dass in der
        // GUI angezeigt wird, um den angezeigten Stundenplan anzupassen
        dbc.executeStatement("SELECT kuerzel FROM lehrer");
        r = dbc.getCurrentQueryResult();
        String[] teachers = new String[r.getRowCount()];
        for (int i = 0; i < r.getRowCount(); i++) {
            teachers[i] = r.getData()[i][0];
        }
        gui.addFilter("Lehrer", teachers);

        // GUI anzeigen und Verhalten bei Interaktion festlegen
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.addSelectionListener(this); // GUI ruft Methode selectionChanged auf, wenn sich eine Auswahlbox ändert.
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }

    /**
     * Methode  um auf Änderungen in der GUi zu reagieren. Sobald der nutzer die
     * Auswahl ändert, wird diese Methode aufgerufen. Der erste Parameter ist der
     * Name der Auswahlliste, die sich geändert hat (erster Parameter von
     * {@link StundenplanGUI#addFilter(String, String[])} oben) und der zweite ist
     * der neue Wert der Auswahl.
     *
     * @param pList     Name der Auswahlliste
     * @param pNewValue Neuer Wert der Auswahl
     */
    @Override
    public void selectionChanged(String pList, String pNewValue) {
        if (pList == "Lehrer") {
            // Zuerst alle bisherigen Stunden aus der GUI entfernen
            gui.removeAllLessons();

            // Neue Daten aus der Datenbank abfragen.
            // Hier gefakte Daten, die Struktur der Datenbank ist nicht vorgegeben.
            dbc.executeStatement(
                "SELECT 0,'Q2','405',kuerzel,ABS(RANDOM()) % 5,ABS(RANDOM()) % 10,'Informatik','#00ff3f' " +
                    "FROM lehrer WHERE kuerzel = '" + pNewValue + "'"+
                    "UNION "+
                    "SELECT 1,'EFa','501',kuerzel,ABS(RANDOM()) % 5,ABS(RANDOM()) % 10,'Mathematik','#35b5ff' " +
                    "FROM lehrer WHERE kuerzel = '" + pNewValue + "'"
            );
            QueryResult r = dbc.getCurrentQueryResult();

            // Falls es ein Ergebnis gibt ...
            if (r != null) {
                String[][] data = r.getData();

                // Neue Stunden in die GUI schreiben
                for (int i = 0; i < data.length; i++) {
                    gui.addLesson(
                        Integer.parseInt(data[i][4]), // Spalte im Plan (0=Montag, ... ,4=Freitag)
                        Integer.parseInt(data[i][5]), // Zeile im Plan (0=1. Stunde, ... ,9=10. Stunde)
                        data[i][6], // Titel
                        "Raum " + data[i][2], // Untertitel
                        data[i][1], // Beschreibungstext
                        data[i][3], // Fußzeile
                        Color.decode(data[i][7]) // Farbe (Objekt der Klasse Color)
                    );
                }

                // GUI-Fenster neu zeichnen, um neue Elemente anzuzeigen.
                gui.revalidate();
                gui.repaint();
            }
        }
    }
}
