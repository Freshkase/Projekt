package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableCellRenderer;
import datenbank.DatenabrufStudent;
import gui.ProfessorenWaehrendGUI.ButtonEditor;
import gui.ProfessorenWaehrendGUI.ButtonRenderer;
import objekte.Student;
import datenbank.DatenabrufStatus;
 
public class PPANachGUI extends JPanel {
    private boolean DEBUG = false;
    private static String anmeldename;

 
    public PPANachGUI(String anmeldename) {
 
    	this.anmeldename = anmeldename;
    	
        String[] columnNames = {"Name",
				        		"E-Mail",
				        		"Unternehmen",
				        		"Betreuer",
        						"Besuchsbericht",
                                "Tätigkeitsnachweis",
                                "BPS-Vortrag",
                                "BPS-Berichteee",
                                };
        
        String ausgabeBesuchsbericht = "Nein";
        String ausgabeBericht = "Nein";
        String ausgabeNachweis = "Nein";
        String ausgabeVortrag = "Nein";
        DatenabrufStudent db = new DatenabrufStudent();
	      ArrayList<Student> ausgabe = db.ausgeben();
       
        Object [][] data = new Object [ausgabe.size()][8];
        for (int i=0;i< ausgabe.size();i++)
		 {
			 data[i][0] =  ausgabe.get(i).getNachname() + ", " + ausgabe.get(i).getVorname();
			 data[i][1] =  ausgabe.get(i).getEmail();
			 data[i][2] =  ausgabe.get(i).getUnternehmen();
			 if(ausgabe.get(i).getProf().getNachname()==null)
			 {
				 data[i][3] = "auswählen";
			 }
			 else {
			 data[i][3] =  ausgabe.get(i).getProf().getNachname() + ", " + ausgabe.get(i).getProf().getVorname();
			 }
        	 data[i][4] = ausgabeBesuchsbericht;
        	 data[i][5] = ausgabeNachweis;
        	 data[i][6] = ausgabeVortrag;
        	 data[i][7] = ausgabeBericht;
		 }
 
 
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));
 
        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollbar = new JScrollPane(table);
        
        JButton btnNewButton = new JButton("Zuteilung beenden");
        btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnNewButton.setBackground(new Color(255, 0, 0));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		Mail mail = new Mail();
//        		mail.send();
//        		
        		DatenabrufStatus db = new DatenabrufStatus();
        		db.aendern(1);
        	}
        });
       
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap(13, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnNewButton, Alignment.TRAILING)
        				.addComponent(scrollbar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 930, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(90)
        			.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
        			.addComponent(btnNewButton)
        			.addGap(25))
        );
        setLayout(groupLayout);
    }
 
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
 
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
    
 // TableCellRenderer für den JButton-Objekt
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        	 DatenabrufStudent db = new DatenabrufStudent();
   	         ArrayList<Student> ausgabe = db.ausgeben();
        
        	 for (int i=0;i< ausgabe.size();i++) {
        		 if(ausgabe.get(i).getProf().getNachname()==null) {
        			 if (row == i) { 
        				 if (isSelected) {
                        
        				 } else {
                         
                     }
                     setText((value == null) ? "" : value.toString());
                     return this;
			 }
            } else { // alle anderen Zellen
                return new JLabel((value == null) ? "" : value.toString());
            }
        }
        	 return new JLabel((value == null) ? "" : value.toString());
       }
    }
    
    // TableCellEditor für den JButton-Objekt
    static class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        
        private String label;
        
        private boolean isPushed;
        private int buttonRow;
        private int buttonColumn;
        private DatenabrufStudent db = new DatenabrufStudent();
	    private ArrayList<Student> ausgabe = db.ausgeben();
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        	 
        
        	 for (int i=0;i< ausgabe.size();i++) {
        		 if(ausgabe.get(i).getProf().getNachname()==null) {
        			 buttonRow = row;
        			 buttonColumn = column;
        			 if(row == i || column == 2) {
        				 if (isSelected) {
        					
        				 } else {
        					
        				 }
        				 label = (value == null) ? "" : value.toString();
        				 button.setText(label);
        				 isPushed = true;
        			 }
        		 }
        	}
        	 return button;
        }
        
        public Object getCellEditorValue() {
            if (isPushed) {
                // Öffne ein neues Fenster, wenn der Button geklickt wird
            	String message = "Name: " + ausgabe.get(buttonRow).getUnternehmen();
            	JOptionPane.showMessageDialog(null, message, "Informationen zum Unternehmen", JOptionPane.INFORMATION_MESSAGE);
            } 
          
            isPushed = false;
            return new String(label);
        }
        
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
        
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
 
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FELD-PPA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        PPANachGUI newContentPane = new PPANachGUI(anmeldename);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
	