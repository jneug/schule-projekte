import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * GUI für das Stundenplanprogramm.
 */
public class StundenplanGUI extends JFrame implements ActionListener {

    // Initiale Größe des Fensters
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    // Schriftarten
    public static Font fHeader = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    public static Font fTitle = new Font(Font.SANS_SERIF, Font.BOLD, 11);
    public static Font fSubtitle = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
    public static Font fText = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
    public static Font fFooter = new Font(Font.SANS_SERIF, Font.ITALIC, 8);


    // Der Listener, der bei Änderungen informiert wird.
    private SelectionListener selectionListener;

    // Caches für Stunden und Filter
    private Hashtable<String, JPanel> filters;
    private Hashtable<Point, JPanel> lessons;

    // Panels für die Bereiche des GUIs
    private JPanel jpPlan, jpSidebar;

    /**
     * Erstellt ein neues GUI, zeigt es aber noch nicht an. Das Hauptprogramm
     * muss dies explizit mit {@link #setVisible(boolean)} tun.
     */
    public StundenplanGUI() {
        filters = new Hashtable<>();
        lessons = new Hashtable<>();

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.addComponents();
    }

    /**
     * Fügt der Seitenleister eine neue Auswahlbox hinzu. Der Name wird bei
     * Änderungen an den {@link SelectionListener} übergeben, um verschiedene
     * Boxen unterscheiden zu können.
     * @param pName Name des Filters
     * @param pOptions Mögliche Optionen
     */
    public void addFilter(String pName, String[] pOptions) {
        JPanel jpFilter = new JPanel();
        jpFilter.setLayout(new BorderLayout());
        jpFilter.setBackground(jpSidebar.getBackground());
        jpFilter.setPreferredSize(new Dimension(jpSidebar.getPreferredSize().width, 50));
        jpFilter.add(makeLabel(pName, fHeader), BorderLayout.NORTH);

        JComboBox<String> jcbFilter = new JComboBox<String>(pOptions);
        jcbFilter.setName(pName);
        jcbFilter.addActionListener(this);
        jpFilter.add(jcbFilter, BorderLayout.CENTER);

        filters.put(pName, jpFilter);
        jpSidebar.add(jpFilter);
    }

    /**
     * Entfernt die Auswahlbox mit dem angegebnen Namen aus der Seitenleiste.
     * @param pName
     */
    public void removeFilter(String pName) {
        if (filters.containsKey(pName)) {
            JPanel jpFilter = filters.get(pName);
            jpSidebar.remove(jpFilter);
            filters.remove(pName);
        }
    }

    /**
     * Entfernt alle Auswahlboxen aus der Seitenleiste.
     */
    public void removeAllFilters() {
        jpSidebar.removeAll();
    }

    /**
     * Fügt eine neue Stunde in den Plan ein. Exisitiert in der angegebenen Zelle
     * schon eine Stunde, wird diese entfernt und durch die neue ersetzt.
     *
     * Eine Stunde in der GUI hat verschiedene Bereiche, in denen Informationen
     * (Text) untergebracht werden können, sowie eine Farbe.
     *
     * Nachdem eine oder mehrere Stunden hinzugefügt wurden, sollte das Fenster mit
     * {@link JFrame#revalidate()} und {@link JFrame#repaint()} neu gezeichnet werden.
     *
     * @param pCol Spaltenindex (0=Montag, ..., 4=Freitag)
     * @param pRow Zeilenindex (0=1. Stunde, ..., 9=10. Stunde)
     * @param pTitle Überschrift
     * @param pSubtitle Untertitel
     * @param pText Beschreibung
     * @param pFooter Fußzeile
     * @param pColor Farbe der Stunde im Plan
     */
    public void addLesson(int pCol, int pRow, String pTitle, String pSubtitle, String pText, String pFooter, Color pColor) {
        removeLesson(pCol, pRow);

        Color bg = new Color(pColor.getRed(), pColor.getGreen(), pColor.getBlue(), 128);

        JPanel jpLesson = new JPanel();
        jpLesson.setBackground(bg);
        jpLesson.setForeground(pColor);
        jpLesson.setBorder(new LineBorder(pColor, 2, true));
        jpLesson.setLayout(new BoxLayout(jpLesson, BoxLayout.Y_AXIS));

        JLabel jlTitle = new JLabel(pTitle);
        jlTitle.setFont(fTitle);
        jpLesson.add(jlTitle);

        JLabel jlSubtitle = new JLabel(pSubtitle);
        jlSubtitle.setFont(fSubtitle);
        jpLesson.add(jlSubtitle);

        JLabel jlText = new JLabel(pText);
        jlText.setFont(fText);
        jpLesson.add(jlText);

        JLabel jlFooter = new JLabel(pFooter);
        jlFooter.setFont(fFooter);
        jpLesson.add(jlFooter);


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = pCol + 1;
        c.gridy = pRow + 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(2, 4, 2, 4);
        c.fill = GridBagConstraints.BOTH;

        lessons.put(new Point(pCol, pRow), jpLesson);
        jpPlan.add(jpLesson, c);
    }

    /**
     * Entfernt die Stunde aus der angebenen Zelle.
     * @param pCol Spaltenindex (0=Montag, ..., 4=Freitag)
     * @param pRow Zeilenindex (0=1. Stunde, ..., 9=10. Stunde)
     */
    public void removeLesson(int pCol, int pRow) {
        Point key = new Point(pCol, pRow);
        JPanel jpLesson = lessons.get(key);
        if (jpLesson != null) {
            jpPlan.remove(jpLesson);
            lessons.remove(key);
        }
    }

    /**
     * Entfernt alle derzeit angezeigten Stunden aus dem GUI.
     */
    public void removeAllLessons() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                removeLesson(i, j);
            }
        }
    }

    /**
     * Setzt den Listener, der über Änderungen der Auswahlboxen informiert wird.
     * @param pSelectionListener
     */
    public void addSelectionListener(SelectionListener pSelectionListener) {
        this.selectionListener = pSelectionListener;
    }

    /**
     * Leitet die Änderungen der Auswahlboxen an den {@link SelectionListener}
     * weiter.
     * @param pActionEvent
     */
    public void actionPerformed(ActionEvent pActionEvent) {
        if (this.selectionListener != null) {
            JComboBox<String> source = ((JComboBox<String>) pActionEvent.getSource());

            String newValue = (String) source.getSelectedItem();
            String sourceName = source.getName();

            this.selectionListener.selectionChanged(sourceName, newValue);
        }
    }


    /**
     * Fügt dem GUI die Standard-Komponenten hinzu.
     */
    private void addComponents() {
        this.setLayout(new BorderLayout());

        // Seitenleiste für Filter (Auswahlboxen)
        jpSidebar = new JPanel();
        jpSidebar.setBackground(Color.LIGHT_GRAY);
        jpSidebar.setPreferredSize(new Dimension(100, FRAME_HEIGHT));
        this.add(jpSidebar, BorderLayout.LINE_START);

        // Hauptbereich für den Stundenplan
        jpPlan = new JPanel();
        this.add(jpPlan, BorderLayout.CENTER);
        jpPlan.setLayout(new GridBagLayout());
        jpPlan.add(new JPanel());

        // Überschriften für die Spalten (Wochentage)
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        c.weighty = 0.2;
        jpPlan.add(makeLabel("Montag", fHeader), c);
        c.gridx++;
        jpPlan.add(makeLabel("Dienstag", fHeader), c);
        c.gridx++;
        jpPlan.add(makeLabel("Mittwoch", fHeader), c);
        c.gridx++;
        jpPlan.add(makeLabel("Donnerstag", fHeader), c);
        c.gridx++;
        jpPlan.add(makeLabel("Freitag", fHeader), c);

        // Überschriften für die Zeilen (Stunden)
        c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.2;
        c.weighty = 0.5;
        for (int i = 0; i < 10; i++) {
            c.gridy = i + 1;
            jpPlan.add(makeLabel((i + 1) + ". Stunde", fHeader), c);
        }
    }

    /**
     * Erzeugt ein Text-Label mit der Standard-Schrift und dem angegebenen Text.
     * @param pLabel
     * @return
     */
    private JLabel makeLabel(String pLabel) {
        return makeLabel(pLabel, fText);
    }

    /**
     * Erzeugt ein Text-Label mit der angegebenen Schrift und dem Text.
     * @param pLabel
     * @param pFont
     * @return
     */
    private JLabel makeLabel(String pLabel, Font pFont) {
        JLabel jlLabel = new JLabel(pLabel);
        jlLabel.setFont(pFont);
        return jlLabel;
    }

}
