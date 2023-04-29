package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;


import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
 
public class PPAGUI extends JPanel {
    private boolean DEBUG = false;
  

 
    public PPAGUI() {
 
    	
        
        String[] columnNames = {"Name",
				        		"E-Mail",
				        		"Unternehmen",
				        		"Betreuer",
        						"Besuchsbericht",
                                "Tätigkeitsnachweis",
                                "BPS-Vortrag",
                                "BPS-Bericht",
                                };
        
        String ausgabeBesuchsbericht = "Nein";
        String ausgabeBericht = "Nein";
        String ausgabeNachweis = "Nein";
        String ausgabeVortrag = "Nein";
        
       
        Object [][] data = new Object [1][8];
        	data[0][4] = ausgabeBesuchsbericht;
        	data[0][5] = ausgabeNachweis;
        	data[0][6] = ausgabeVortrag;
        	data[0][7] = ausgabeBericht;
 
 
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
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollbar = new JScrollPane(table);
        
        JButton btnNewButton = new JButton("Zuteilung beenden");
        btnNewButton.setBackground(new Color(255, 0, 0));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
        			.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
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
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FELD-PPA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        PPAGUI newContentPane = new PPAGUI();
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
	
