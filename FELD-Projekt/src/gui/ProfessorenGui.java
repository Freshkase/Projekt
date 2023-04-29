package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;

import datenbank.DatenabrufStudent;
import objekte.Student;

import javax.swing.JTable;

public class ProfessorenGui extends JPanel{

	private JFrame frame;
	private boolean DEBUG = false;
	private JTable table;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	  DatenabrufStudent db = new DatenabrufStudent();
      ArrayList<Student> ausgabe = db.ausgeben();
	public ProfessorenGui() {
		
		
		 String[] columnNames = {"Student",
	        					"E-Mail",
	        					"Unternehmen",
	        					"Betreuer",
		 						};
		 
		
		 Object [][] data = new Object [ ausgabe.size()][4];
		 for (int i=0;i< ausgabe.size();i++)
		 {
			 data[i][0] =  ausgabe.get(i).getNachname();
			 data[i][1] =  ausgabe.get(i).getEmail();
			 data[i][2] =  ausgabe.get(i).getUnternehmen();
			 if(ausgabe.get(i).getProf().getNachname()==null)
			 {
				 data[i][3] ="auswählen";
			 }
			 else {
			 data[i][3] =  ausgabe.get(i).getProf().getNachname();
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
         			.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
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
	        ProfessorenGui newContentPane = new ProfessorenGui();
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
