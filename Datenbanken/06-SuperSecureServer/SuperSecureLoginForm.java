import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class SuperSecureLoginForm extends JFrame {
    // Anfang Attribute
    private JLabel jLabel1 = new JLabel();
    private ImageIcon jLabel1Icon = new ImageIcon(getClass().getResource("images/S3-Logo.png"));
    private JTextField jtfUsername = new JTextField();
    private JTextField jtfPassword = new JTextField();
    private JLabel lBenutzername = new JLabel();
    private JLabel lPasswort = new JLabel();
    private JButton jbLogin = new JButton();
    private JLabel jlStatus = new  JLabel();
    private JCheckBox cbShowQueries = new JCheckBox();
    // Ende Attribute

    private SuperSecureServer sss;

    private ImageIcon accept = new ImageIcon(getClass().getResource("images/accept.png"));
    private ImageIcon cross = new ImageIcon(getClass().getResource("images/cross.png"));

    public SuperSecureLoginForm(SuperSecureServer sss) {
        // Dialog-Initialisierung
        super("SuperSecureServer Login");
        this.sss = sss;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int frameWidth = 399;
        int frameHeight = 398;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        jLabel1.setBounds(0, 0, 382, 188);
        jLabel1.setText("");
        jLabel1.setIcon(jLabel1Icon);
        cp.add(jLabel1);
        jtfUsername.setBounds(168, 200, 182, 20);
        cp.add(jtfUsername);
        jtfPassword.setBounds(168, 232, 182, 20);
        cp.add(jtfPassword);
        lBenutzername.setBounds(40, 200, 110, 20);
        lBenutzername.setText("Benutzername");
        lBenutzername.setLabelFor(jtfUsername);
        cp.add(lBenutzername);
        lPasswort.setBounds(40, 232, 110, 20);
        lPasswort.setText("Passwort");
        lPasswort.setLabelFor(jtfPassword);
        cp.add(lPasswort);
        jbLogin.setBounds(168, 264, 75, 25);
        jbLogin.setText("Login");
        jbLogin.setMargin(new Insets(2, 2, 2, 2));
        jbLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbLogin_ActionPerformed(evt);
            }
        });
        cp.add(jbLogin);
        jlStatus.setBounds(40, 328, 313, 17);
        jlStatus.setText("");
        cp.add(jlStatus);
        cbShowQueries.setBounds(168, 296, 188, 20);
        cbShowQueries.setOpaque(false);
        cbShowQueries.setText("SQL anzeigen");
        cbShowQueries.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                cbShowQueries_StateChanged(evt);
            }
        });
        cp.add(cbShowQueries);
        // Ende Komponenten

        setResizable(false);
        setVisible(true);
    } // end of public SuperSecureLoginForm

    // Anfang Methoden
    public void jbLogin_ActionPerformed(ActionEvent evt) {
        String username = jtfUsername.getText();
        String password = jtfPassword.getText();
        boolean success = sss.login(username, password);
        if (success) {
            jlStatus.setForeground(new Color(116, 200, 104));
            jlStatus.setIcon(accept);
            jlStatus.setText("Anmeldung erfolgreich. Herzlich willkommen!");
        } else {
            jlStatus.setForeground(new Color(255, 84, 87));
            jlStatus.setIcon(cross);
            jlStatus.setText("Anmeldung fehlgeschalgen!");
        } // end of if-else
    } // end of jbLogin_ActionPerformed

    public void cbShowQueries_StateChanged(ChangeEvent evt) {
        boolean checked = cbShowQueries.isSelected();
        sss.setShowQueries(checked);
    } // end of cbShowQueries_StateChanged

    // Ende Methoden

} // end of class SuperSecureLoginForm

