package gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import datenbank.DatenabrufStudent;
import objekte.Student;

import javax.swing.JTable;

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
				 data[i][4] ="auswählen";
			 }
			 else {
			 data[i][4] =  ausgabe.get(i).getProf().getNachname() + ", " + ausgabe.get(i).getProf().getVorname();
			 }
		 }
		
     	 final JTable table = new JTable(data, columnNames);
         table.setPreferredScrollableViewportSize(new Dimension(500, 70));
         table.setFillsViewportHeight(true);
         
         if (DEBUG) {
             table.addMouseListener(new MouseAdapter() {
                 public void mouseClicked(MouseEvent e) {
                     printDebugData(table);
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
         			.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
         			.addContainerGap(447, Short.MAX_VALUE))
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
