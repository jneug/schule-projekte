import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyMailGUI extends JFrame implements ActionListener, ListSelectionListener {

    private MyMail app;

    private java.util.List<Mail> mails = new ArrayList<>(20);

    private JPanel jpStatus, jpSidebar, jpMain;

    private JLabel jlStatusText;

    private JButton jbReload, jbDelete, jbNew, jbReply, jbSettings;

    private JTable jtMaillist;

    private JTextArea jtaMailtext;

    public MyMailGUI( MyMail pClientApp ) {
        super("MyMail: POP3 Client");
        app = pClientApp;

        createComponents();

        // Methoden der Klasse JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setStatus( String pStatusText ) {
        jlStatusText.setText(pStatusText);
        jlStatusText.setIcon(loadImageIcon("information.png"));
    }

    public void setError( String pStatusText ) {
        jlStatusText.setText(pStatusText);
        jlStatusText.setIcon(loadImageIcon("error.png"));
    }

    public void clearAllMails() {
        mails.clear();
        updateMailList();
    }

    public void removeMailFromList( Mail pMail ) {
        if( pMail != null ) {
            mails.remove(pMail);
            updateMailList();
        }
    }

    public void addMailToList( Mail pNewMail ) {
        if( pNewMail != null ) {
            mails.add(pNewMail);
            updateMailList();
        }
    }

    public void addMailsToList( Mail[] pNewMails ) {
        for( int i = 0; i < pNewMails.length; i++ ) {
            if( pNewMails[i] != null ) {
                mails.add(pNewMails[i]);
            }
        }
        updateMailList();
    }

    public void addMailsToList( List<Mail> pNewMails ) {
        pNewMails.toFirst();
        while( pNewMails.hasAccess() ) {
            if( pNewMails.getContent() != null ) {
                mails.add(pNewMails.getContent());
            }
            pNewMails.next();
        }
        updateMailList();
    }

    public void updateMailList() {
        ((MailTableModel) jtMaillist.getModel()).fireTableDataChanged();
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getActionCommand().equals("load") ) {
            app.getAllMails();
        } else if( e.getActionCommand().equals("delete") ) {
            //app.deleteMail(jtMaillist.getSelectedRow()+1);
        }
    }

    @Override
    public void valueChanged( ListSelectionEvent e ) {
        int selectedRow = jtMaillist.getSelectedRow();
        if( selectedRow >= 0 && selectedRow < mails.size() ) {
            Mail selectedMail = mails.get(jtMaillist.getSelectedRow());
            jtaMailtext.setText(selectedMail.getText());
            jbDelete.setEnabled(true);
        } else {
            jtaMailtext.setText("Wähle oben ein Mail aus ..");
            jbDelete.setEnabled(false);
        }
    }

    private void createComponents() {
        jpStatus = new JPanel();
        jpStatus.setLayout(new FlowLayout(FlowLayout.LEADING));
        jpStatus.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.add(jpStatus, BorderLayout.SOUTH);

        jlStatusText = new JLabel("Starte App..");
        jpStatus.add(jlStatusText);

        jpSidebar = new JPanel();
        jpSidebar.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpSidebar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jpSidebar.setBackground(Color.DARK_GRAY);
        jpSidebar.setPreferredSize(new Dimension(160, 600));
        this.add(jpSidebar, BorderLayout.WEST);

        jbReload = new JButton("Neu laden");
        jbReload.setActionCommand("load");
        jbReload.addActionListener(this);
        jbReload.setIcon(loadImageIcon("arrow_refresh.png"));
        jpSidebar.add(jbReload);

        jbDelete = new JButton("Löschen");
        jbDelete.setEnabled(false);
        jbDelete.setActionCommand("delete");
        jbDelete.addActionListener(this);
        jbDelete.setIcon(loadImageIcon("email_delete.png"));
        jpSidebar.add(jbDelete);

        jbSettings = new JButton("Einstellungen");
        jbSettings.setActionCommand("settings");
        jbSettings.addActionListener(this);
        jbSettings.setIcon(loadImageIcon("setting.png"));
        //jpSidebar.add(jbSettings);

        jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());
        this.add(jpMain, BorderLayout.CENTER);

        jtMaillist = new JTable(new MailTableModel());
        jtMaillist.setShowGrid(true);
        jtMaillist.setFillsViewportHeight(true);
        jtMaillist.setCellSelectionEnabled(false);
        jtMaillist.setColumnSelectionAllowed(false);
        jtMaillist.setRowSelectionAllowed(true);
        jtMaillist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtMaillist.setShowHorizontalLines(true);
        jtMaillist.getSelectionModel().addListSelectionListener(this);
        jtMaillist.getColumnModel().getColumn(0).setPreferredWidth(200);
        jtMaillist.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtMaillist.getColumnModel().getColumn(2).setPreferredWidth(400);

        JScrollPane spMaillistContainer = new JScrollPane(jtMaillist);
        spMaillistContainer.setPreferredSize(new Dimension(640, 200));
        jpMain.add(spMaillistContainer, BorderLayout.NORTH);

        jtaMailtext = new JTextArea();
        jtaMailtext.setEditable(false);
        jtaMailtext.setLineWrap(true);
        jtaMailtext.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        jtaMailtext.setText("Wähle oben ein Mail aus ..");

        JScrollPane spMailtextContainer = new JScrollPane(jtaMailtext);
        spMailtextContainer.setPreferredSize(new Dimension(640, 400));
        spMailtextContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jpMain.add(spMailtextContainer, BorderLayout.CENTER);

        this.pack();
    }

    private Icon loadImageIcon( String pImageName ) {
        return new ImageIcon(MyMailGUI.class.getResource("images/" + pImageName));
    }

    class MailTableModel extends AbstractTableModel {

        @Override
        public String getColumnName( int columnIndex ) {
            switch( columnIndex ) {
                case 0:
                    return "Datum";
                case 1:
                    return "Absender";
                case 2:
                    return "Betreff";
                default:
                    return "";
            }
        }

/*
        @Override
        public Class<?> getColumnClass( int columnIndex ) {
            switch( columnIndex ) {
                case 0:
                    return Date.class;
                default:
                    return String.class;
            }
        }
*/

        @Override
        public int getRowCount() {
            return mails.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt( int rowIndex, int columnIndex ) {
            if( rowIndex < mails.size() ) {
                switch( columnIndex ) {
                    case 0:
                        return mails.get(rowIndex).getDate();
                    case 1:
                        return mails.get(rowIndex).getSender();
                    case 2:
                        return mails.get(rowIndex).getSubject();
                    default:
                        return "";
                }
            } else {
                return "";
            }
        }

    }

}
