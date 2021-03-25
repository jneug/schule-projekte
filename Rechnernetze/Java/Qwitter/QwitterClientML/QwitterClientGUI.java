import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

public class QwitterClientGUI extends JFrame
{
    
    private JTable table;
   
    public QwitterClientGUI()
    {
       super("Qwitter");
       initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        
        String[] columnNames = {"Username", "Qweet"};
        Object[][] data = new Object[0][0];
                    
        DefaultTableModel model = new DefaultTableModel(columnNames,0) {
            public boolean isCellEditable( int a, int b ) {return false;}
        };
        table = new JTable(model);
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
       
       pack();
       setSize(600, 300);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setVisible(true);
    }
    
    public void addQweet( String username, String text ) {
        EventQueue.invokeLater(new Runnable(){
           public void run() {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.insertRow(0, new Object[]{username, text});
            }
        });
    }
    
    public void clear() {
        EventQueue.invokeLater(new Runnable(){
           public void run() {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                while( model.getRowCount() > 0 ) {
                    model.removeRow(0);
                }
            }
        });
    }

}
