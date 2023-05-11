package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

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
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableCellRenderer;
import datenbank.DatenabrufStudent;
import gui.ProfessorenWaehrendGUI.ButtonEditor;
import gui.ProfessorenWaehrendGUI.ButtonRenderer;
import objekte.Professor;
import objekte.Student;
import sortierung.MyComparator3;
import datenbank.DatenabrufProfessor;
import datenbank.DatenabrufStatus;

public class PPANachGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;

	public PPANachGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		String[] columnNames = { "Name", "E-Mail", "Unternehmen", "Betreuer", "Tätigkeitsnachweis", "BPS-Bericht",
				"Besuchsbericht", "BPS-Vortrag", };

		DatenabrufStudent db = new DatenabrufStudent();
		ArrayList<Student> ausgabe = db.ausgeben();
		Collections.sort(ausgabe, new MyComparator3());

		Object[][] data = new Object[ausgabe.size()][8];
		for (int i = 0; i < ausgabe.size(); i++) {
			data[i][0] = ausgabe.get(i).getNachname() + ", " + ausgabe.get(i).getVorname();
			data[i][1] = ausgabe.get(i).getEmail();
			data[i][2] = ausgabe.get(i).getUnternehmen().getName();
			data[i][3] = ausgabe.get(i).getProf().getNachname() + ", " + ausgabe.get(i).getProf().getVorname();
			data[i][4] = ausgabe.get(i).getTätigkeitsnachweis();
			data[i][5] = ausgabe.get(i).getBericht();
			if (ausgabe.get(i).getBesuchsbericht().equals(" ")) {
				data[i][6] = "noch nicht erstellt";
			} else {
				data[i][6] = "lesen";
			}
			data[i][7] = ausgabe.get(i).getVortrag();

		}

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		if (DEBUG) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					printDebugData(table);
				}
			});
		}

		// Create the scroll pane and add the table to it.
		JScrollPane scrollbar = new JScrollPane(table);

		JButton btnNewButton = new JButton("Senden");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (PrintWriter writer = new PrintWriter(new File("Mails.csv"))) {
					DatenabrufStudent db = new DatenabrufStudent();
					ArrayList<Student> ausgabe = db.ausgeben();
					
			        StringBuilder sb = new StringBuilder();
			    	sb.append("Nachname");
		        	sb.append(',');
		        	sb.append("Vorname(");
		        	sb.append(',');
		        	sb.append("Anmeldename");
		        	sb.append(',');
		        	sb.append("Matrikelnummer");
		        	sb.append(',');
		        	sb.append("Email");
		        	sb.append(',');
		        	sb.append("Unternehmen");
		        	sb.append(',');
		        	sb.append("Beginn");
		        	sb.append(',');
		        	sb.append("Ende");
		        	sb.append(',');
		        	sb.append("Professor");
		        	sb.append(',');
		        	sb.append("Besuchsbericht");
		        	sb.append(',');
		        	sb.append("Tätigkeitsnachweis");
		        	sb.append(',');
		        	sb.append("Bericht");
		        	sb.append(',');
		        	sb.append("Vortrag");
		        	sb.append('\n');
		        	for(Student i: ausgabe) {
			        	sb.append(i.getNachname());
			        	sb.append(',');
			        	sb.append(i.getVorname());
			        	sb.append(',');
			        	sb.append(i.getAnmeldename());
			        	sb.append(',');
			        	sb.append(i.getMatrikelnr());
			        	sb.append(',');
			        	sb.append(i.getEmail());
			        	sb.append(',');
			        	sb.append(i.getUnternehmen());
			        	sb.append(',');
			        	sb.append(i.getBeginn());
			        	sb.append(',');
			        	sb.append(i.getEnde());
			        	sb.append(',');
			        	sb.append(i.getProf());
			        	sb.append(',');
			        	sb.append(i.getBesuchsbericht());
			        	sb.append(',');
			        	sb.append(i.getTätigkeitsnachweis());
			        	sb.append(',');
			        	sb.append(i.getBericht());
			        	sb.append(',');
			        	sb.append(i.getVortrag());
			        	sb.append('\n');
			        }
			        writer.write(sb.toString());
			        writer.close();
			        System.out.println("done!");

//        		Mail mail = new Mail();
//        		mail.send();
//        		
//        		DatenabrufStatus db = new DatenabrufStatus();
//        		db.aendern(1);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING).addComponent(scrollbar, Alignment.TRAILING,
								GroupLayout.PREFERRED_SIZE, 930, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(90)
						.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE).addComponent(btnNewButton)
						.addGap(25)));
		setLayout(groupLayout);
	}

	private void printDebugData(JTable table) {
		int numRows = table.getRowCount();
		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();

		System.out.println("Value of data: ");
		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
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

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			DatenabrufStudent db = new DatenabrufStudent();
			ArrayList<Student> ausgabe = db.ausgeben();
			Collections.sort(ausgabe, new MyComparator3());

			for (int i = 0; i < ausgabe.size(); i++) {
				if ((!ausgabe.get(i).getBesuchsbericht().equals(" ")) || column == 7) {
					if (row == i || column == 7) {
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

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {

			Collections.sort(ausgabe, new MyComparator3());

			for (int i = 0; i < ausgabe.size(); i++) {
				if ((!ausgabe.get(i).getBesuchsbericht().equals(" ")) || column == 7) {
					if (row == i || column == 7) {
						buttonRow = row;
						buttonColumn = column;
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

				DatenabrufStudent db = new DatenabrufStudent();
				ArrayList<Student> ausgabe = db.ausgeben();
				Collections.sort(ausgabe, new MyComparator3());

				if (buttonColumn == 7) {
					if (ausgabe.get(buttonRow).getVortrag().equals("nein")) {
						int option = JOptionPane.showOptionDialog(null, "Wollen sie den Status ändern? ", "Bestätigung",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								new String[] { "Ja", "Zurück" }, "Zurück");

						if (option == JOptionPane.YES_OPTION) {

							db.aendernVortrag(ausgabe.get(buttonRow).getMatrikelnr());

							ausgabe.clear();
							frame.dispose();
							PPANachGUI neu = new PPANachGUI(anmeldename);
							neu.main(null);

						}
					} else {

						JOptionPane.showMessageDialog(null, "BPS-Bericht wurde schon auf ja gesetzt",
								"Informationen zum Unternehmen", JOptionPane.INFORMATION_MESSAGE);

					}

				} else {
					String header = "Besuchsbericht";
					String message = "Geschrieben von Professor " + ausgabe.get(buttonRow).getProf().getNachname()
							+ " über " + ausgabe.get(buttonRow).getVorname() + " "
							+ ausgabe.get(buttonRow).getNachname() + ":\n" + ausgabe.get(buttonRow).getBesuchsbericht();
					JOptionPane.showMessageDialog(null, message, header, JOptionPane.INFORMATION_MESSAGE);
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
		// Create and set up the window.
		PPANachGUI.frame = new JFrame("FELD-PPA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		PPANachGUI newContentPane = new PPANachGUI(anmeldename);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
