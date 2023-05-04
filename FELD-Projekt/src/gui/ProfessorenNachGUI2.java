package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import datenbank.DatenabrufProfessor;
import datenbank.DatenabrufStudent;
import objekte.Student;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class ProfessorenNachGUI2 extends JPanel{
	private boolean DEBUG = false;
	private static String anmeldename;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	  
    public ProfessorenNachGUI2(String anmeldename) {
		
    	this.anmeldename = anmeldename;
		
		 String[] columnNames = {"Student",
	        					"E-Mail",
	        					"Unternehmen", 
	        					"Zeitraum",
	        					"Besuchsbericht",
	        					"BPS-Bericht",
		 						};
		 DatenabrufStudent db = new DatenabrufStudent();
	     ArrayList<Student> ausgabe = db.ausgeben();
	     ArrayList<Student> verkuerzt = new ArrayList<>();
	     for (int j=0;j< ausgabe.size();j++) {
			if(ausgabe.get(j).getProf().getAnmeldename().equals(anmeldename)){
				verkuerzt.add(ausgabe.get(j));
			}
	      }
	      
		 Object [][] data = new Object [verkuerzt.size()][6];
		 for (int j=0;j< verkuerzt.size();j++) {
			 
			 if(verkuerzt.get(j).getProf().getAnmeldename().equals(anmeldename)){
		
				 data[j][0] =  verkuerzt.get(j).getNachname() + ", " + verkuerzt.get(j).getVorname();
				 data[j][1] =  verkuerzt.get(j).getEmail();
				 data[j][2] =  verkuerzt.get(j).getUnternehmen();
				 data[j][3] = verkuerzt.get(j).getBeginn() + " - " + verkuerzt.get(j).getEnde();
				 if(verkuerzt.get(j).getBesuchsbericht().equals(" ")){
					 data[j][4] = "Erstellen";
				 }
				 else {
					 data[j][4] = "Bericht wurde erstellt";
				 }
				 data[j][5]=verkuerzt.get(j).getBericht();
		 
			 }
		 } 
		
		
     	 final JTable table = new JTable(data, columnNames);
         table.setPreferredScrollableViewportSize(new Dimension(500, 70));
         table.setFillsViewportHeight(true);
        
         table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
         table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
         table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
         table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
         table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			ArrayList<Student> verkuerzt = new ArrayList<>();
			for (int j = 0; j < ausgabe.size(); j++) {
				if (ausgabe.get(j).getProf().getAnmeldename().equals(anmeldename)) {
					verkuerzt.add(ausgabe.get(j));
				}
			}

			for (int i = 0; i < ausgabe.size(); i++) {
				System.out.println(i);
				if (verkuerzt.get(i).getBesuchsbericht().equals("1")) {
				
					if (row == i || column == 2) {

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
        private int buttonRow;
        private int buttonColumn;
      
        
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
    	     ArrayList<Student> verkuerzt = new ArrayList<>();
    	     for (int j=0;j< ausgabe.size();j++) {
    			if(ausgabe.get(j).getProf().getAnmeldename().equals(anmeldename)){
    				verkuerzt.add(ausgabe.get(j));
    			}
    	      }
    	     
        	 for (int i=0;i< verkuerzt.size();i++) {
        		 if(verkuerzt.get(i).getBesuchsbericht().equals(" ") || column == 2) {
        			 buttonRow = row;
        			 buttonColumn = column;
        			 
        			 if(row == i || column == 2) {
        				 
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
        	 DatenabrufStudent db = new DatenabrufStudent();
    	     ArrayList<Student> ausgabe = db.ausgeben();
    	     ArrayList<Student> verkuerzt = new ArrayList<>();
    	     for (int j=0;j< ausgabe.size();j++) {
    			if(ausgabe.get(j).getProf().getAnmeldename().equals(anmeldename)){
    				verkuerzt.add(ausgabe.get(j));
    			}
    	      }
            if (isPushed) {
                // Öffne ein neues Fenster, wenn der Button geklickt wird
            if(buttonColumn == 2) {
            	String message = "Name: " + verkuerzt.get(buttonRow).getUnternehmen();
            	JOptionPane.showMessageDialog(null, message, "Informationen zum Unternehmen", JOptionPane.INFORMATION_MESSAGE);
            } else {
		        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		        JTextArea textArea = new JTextArea(45, 120);
		        if(verkuerzt.get(buttonRow).getBesuchsbericht().equals(" "))
		        {
		        textArea.setText("Bitte hier Besuchbericht reinschreiben");
		        }
		        else
		        {
		        	 textArea.setText(verkuerzt.get(buttonRow).getBesuchsbericht());
		        }
		        	
		        panel.add(new JScrollPane(textArea));

		        int result = JOptionPane.showConfirmDialog(null, panel, "Besuchsbericht "+verkuerzt.get(buttonRow).getVorname()+" "+verkuerzt.get(buttonRow).getNachname(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (result == JOptionPane.OK_OPTION) {
		            String input = textArea.getText();
		            DatenabrufProfessor dp=new DatenabrufProfessor();
		           // dp.Besuchsberichterstellen(input, ausgabe.get(buttonRow).getMatrikelnr());
		            System.out.println("Eingegebener Text: " + input);
		            System.out.println(anmeldename);
		            // Hier wird der eingegebene Text in der Variable "eingabe" gespeichert
		            String eingabe = input;
		        }
            }
            	
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
	        ProfessorenNachGUI2 newContentPane = new ProfessorenNachGUI2(anmeldename);
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