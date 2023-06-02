package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.Collections;

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
import objekte.Professor;
import objekte.Student;
import objekte.Unternehmen;
import sortierung.MyComparator2;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * Professoren-Maske während der Zuteilung
 */
public class ProfessorenWaehrendGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;

	public ProfessorenWaehrendGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		/*
		 * die in der Datenbank (Tabelle Studenten) befindlichen Daten werden ausgelesen
		 * und in Form einer Tabelle eingelesen
		 */
		String[] columnNames = { "Student", "E-Mail", "Unternehmen", "Zeitraum", "Betreuer", };
		DatenabrufStudent db = new DatenabrufStudent();
		ArrayList<Student> ausgabe = db.ausgeben();

		Object[][] data = new Object[ausgabe.size()][5];
		for (int i = 0; i < ausgabe.size(); i++) {
			data[i][0] = ausgabe.get(i).getNachname() + ", " + ausgabe.get(i).getVorname();
			data[i][1] = ausgabe.get(i).getEmail();
			data[i][2] = ausgabe.get(i).getUnternehmen().getName();
			data[i][3] = ausgabe.get(i).getBeginn() + " - " + ausgabe.get(i).getEnde();
			if (ausgabe.get(i).getProf().getNachname() == null) {
				data[i][4] = "auswählen";
			} else {
				data[i][4] = ausgabe.get(i).getProf().getNachname() + ", " + ausgabe.get(i).getProf().getVorname();
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
					for (int i = 0; i < numRows; i++) {
						System.out.print("    row " + i + ":");
						for (int j = 0; j < numCols; j++) {
							System.out.print("  " + model.getValueAt(i, j));
						}
						System.out.println();
					}
					System.out.println("--------------------------");
				}
			});
		}
		JScrollPane scrollbar = new JScrollPane(table);

		JButton AbmeldeButton = new JButton("Abmelden");
		AbmeldeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AnmeldungGUI neu = new AnmeldungGUI();
				neu.main(null);
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(26, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(AbmeldeButton)
								.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 930, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(26, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(AbmeldeButton).addGap(10)
						.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	/**
	 * TableCellRenderer für das JButton-Objekt (innerhalb der Tabelle)
	 */
	static class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			DatenabrufStudent db = new DatenabrufStudent();
			ArrayList<Student> ausgabe = db.ausgeben();

			for (int i = 0; i < ausgabe.size(); i++) {
				/*
				 * Renderer bei Professor = null und der gesamten Spalte 2
				 */
				if (ausgabe.get(i).getProf().getNachname() == null || column == 2) {
					if (row == i || column == 2) {
						setText((value == null) ? "" : value.toString());
						return this;
					}
				} else { // alle anderen Zellen kein Renderer
					return new JLabel((value == null) ? "" : value.toString());
				}
			}
			return new JLabel((value == null) ? "" : value.toString());
		}
	}

	/**
	 * TableCellEditor für das JButton-Objekt (innerhalb der Tabelle)
	 */
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

		
				/*
				 * Button bei Professor = null und der gesamten Spalte 2
				 */
				if (ausgabe.get(row).getProf().getNachname() == null || column == 2) {
					buttonRow = row;
					buttonColumn = column;
				
						label = (value == null) ? "" : value.toString();
						button.setText(label);
						isPushed = true;
						return button;
					
				}else {
					label = (value == null) ? "" : value.toString();
					return new JLabel(label);
					
				}
		}

		public Object getCellEditorValue() {
			if (isPushed) {
				/*
				 * Öffne ein neues Fenster, wenn der Button geklickt wird wenn der Button in
				 * Spalte 2 ist werden alle Unternehmensdaten von dem jeweiligen Studenten
				 * angezeigt, auf das geklickt wurde
				 */
				if (buttonColumn == 2) {
					DatenabrufStudent db = new DatenabrufStudent();
					ArrayList<Unternehmen> unternehmenls = db.ausUnternehmen();
					DatenabrufStudent dk = new DatenabrufStudent();
					ArrayList<Student> ausgabestudent = dk.ausgeben();
					Collections.sort(ausgabestudent, new MyComparator2());

					for (int i = 0; i <= unternehmenls.size() - 1; i++) {
						if (ausgabestudent.get(buttonRow).getAnmeldename()
								.equals(unternehmenls.get(i).getanmeldesnamedesstudenten())) {

							String message = "<html>"
								    + "<h2>Informationen zum Unternehmen</h2>"
								    + "<table>"
								    + "<tr><td><b>Name:</b></td><td>" + unternehmenls.get(i).getName() + "</td></tr>"
								    + "<tr><td><b>Anschrift:</b></td><td>" + unternehmenls.get(i).getAnschrift() + "</td></tr>"
								    + "<tr><td><b>URL:</b></td><td>" + unternehmenls.get(i).getUrl() + "</td></tr>"
								    + "<tr><td><b>E-Mail:</b></td><td>" + unternehmenls.get(i).getEmail() + "</td></tr>"
								    + "<tr><td><b>Firmen-Betreuer:</b></td><td>" + unternehmenls.get(i).getBetreuer() + "</td></tr>"
								    + "<tr><td><b>Telefonnummer:</b></td><td>" + unternehmenls.get(i).getTelefon() + "</td></tr>"
								    + "<tr><td><b>Abteilung:</b></td><td>" + unternehmenls.get(i).getBereich() + "</td></tr>"
								    + "<tr><td><b>Zeitraum:</b></td><td>" + ausgabestudent.get(buttonRow).getBeginn() + " - " + ausgabestudent.get(buttonRow).getEnde() + "</td></tr>"
								    + "<tr><td><b>Themenbereich:</b></td><td>" + unternehmenls.get(i).getThema() + "</td></tr>"
								    + "<tr><td><b>Beschreibung:</b></td><td><html><body><div style='width: 200px; word-wrap: break-word; vertical-align: top;'>" + unternehmenls.get(i).getBeschreibung() + "</div></body></html></td></tr>"
								    + "</table>"
								    + "</html>";

								JOptionPane.showMessageDialog(null, message, "Informationen zum Unternehmen", JOptionPane.PLAIN_MESSAGE);

						}
					}
					/*
					 * andernfalls: der Professor = null
					 */
				} else {
					int option = JOptionPane.showOptionDialog(null, "Sind Sie sicher? ", "Bestätigung",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new String[] { "Ja", "Zurück" }, "Zurück");

					/*
					 * wenn auf "Ja" geklickt wird wird bei jeweiligem Studenten derjenige Professor
					 * in der Datenbank eingetragen, der gerade angemeldet ist
					 */
					if (option == JOptionPane.YES_OPTION) {
						DatenabrufProfessor db2 = new DatenabrufProfessor();
						ArrayList<Professor> ausgabeprof = db2.ausgeben();
						int nummer = ausgabe.get(buttonRow).getMatrikelnr();

						for (int i = 1; i < ausgabeprof.size(); i++) {
							if (ausgabeprof.get(i).getAnmeldename().equals(anmeldename)) {
								db.aendern(ausgabeprof.get(i).getId(), nummer);

							}
						}
						frame.dispose();
						ProfessorenWaehrendGUI neu = new ProfessorenWaehrendGUI(anmeldename);
						neu.main(null);

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
		// Create and set up the window.
		ProfessorenWaehrendGUI.frame = new JFrame("FELD-Professoren");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(ProfessorenWaehrendGUI.class.getResource("/gui/Logo.png")));

		// Create and set up the content pane.
		ProfessorenWaehrendGUI newContentPane = new ProfessorenWaehrendGUI(anmeldename);
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
