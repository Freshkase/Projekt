package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import datenbank.DatenabrufStudent;
import objekte.Student;

import javax.swing.JTable;
import javax.swing.UIManager;

public class ProfessorenGUI extends JPanel{
	private boolean DEBUG = false;
	private static String anmeldename;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	  
    public ProfessorenGUI(String anmeldename) {
		
    	this.anmeldename = anmeldename;
		
		 String[] columnNames = {"Student",
	        					"E-Mail",
	        					"Unternehmen", 
	        					"Zeitraum",
	        					"Betreuer",
		 						};
		 DatenabrufStudent db = new DatenabrufStudent();
	      ArrayList<Student> ausgabe = db.ausgeben();
		
		 Object [][] data = new Object [ausgabe.size()][5];
		 for (int i=0;i< ausgabe.size();i++)
		 {
			 data[i][0] =  ausgabe.get(i).getNachname() + ", " + ausgabe.get(i).getVorname();
			 data[i][1] =  ausgabe.get(i).getEmail();
			 data[i][2] =  ausgabe.get(i).getUnternehmen();
			 data[i][3] = ausgabe.get(i).getBeginn() + " - " + ausgabe.get(i).getEnde();
			 if(ausgabe.get(i).getProf().getNachname()==null)
			 {
				 data[i][4] = "auswählen";
			 }
			 else {
			 data[i][4] =  ausgabe.get(i).getProf().getNachname() + ", " + ausgabe.get(i).getProf().getVorname();
			 }
		 }
		
     	 final JTable table = new JTable(data, columnNames);
         table.setPreferredScrollableViewportSize(new Dimension(500, 70));
         table.setFillsViewportHeight(true);
         table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
         table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
         
         if (DEBUG) {
             table.addMouseListener(new MouseAdapter() {
                 public void mouseClicked(MouseEvent e) {
                     printDebugData(table);
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
             });
         }
         JScrollPane scrollbar = new JScrollPane(table);
         
         
         
         GroupLayout groupLayout = new GroupLayout(this);
         groupLayout.setHorizontalGroup(
         	groupLayout.createParallelGroup(Alignment.LEADING)
         		.addGroup(groupLayout.createSequentialGroup()
         			.addContainerGap()
         			.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 930, GroupLayout.PREFERRED_SIZE)
         			.addContainerGap(56, Short.MAX_VALUE))
         );
         groupLayout.setVerticalGroup(
         	groupLayout.createParallelGroup(Alignment.TRAILING)
         		.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
         			.addGap(110)
         			.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
         			.addContainerGap(447, Short.MAX_VALUE))
         );
         setLayout(groupLayout);
		
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
                         setForeground(table.getSelectionForeground());
                         setBackground(table.getSelectionBackground());
        				 } else {
                         setForeground(table.getForeground());
                         setBackground(UIManager.getColor("Button.background"));
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
        	 DatenabrufStudent db = new DatenabrufStudent();
   	         ArrayList<Student> ausgabe = db.ausgeben();
        
        	 for (int i=0;i< ausgabe.size();i++) {
        		 if(ausgabe.get(i).getProf().getNachname()==null) {
        			 if(row == i) {
        				 if (isSelected) {
        					 button.setForeground(table.getSelectionForeground());
        					 button.setBackground(table.getSelectionBackground());
        				 } else {
        					 button.setForeground(table.getForeground());
        					 button.setBackground(table.getBackground());
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
         
                JOptionPane.showMessageDialog(null, "Button wurde geklickt!" + button.getLocation());
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
	        JFrame frame = new JFrame("FELD-Professoren");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Create and set up the content pane.
	        ProfessorenGUI newContentPane = new ProfessorenGUI(anmeldename);
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
