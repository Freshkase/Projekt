package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
import objekte.Unternehmen;
import sortierung.MyComparator1;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 * Professoren-Maske nach der Zuteilung
 */
public class ProfessorenNachGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;
	private static ArrayList<Student> verkuerzt2 = new ArrayList<>();
	private static ArrayList<Student> verkuerzt = new ArrayList<>();
	private static HashSet<Student> unique = new HashSet<>();

	public ProfessorenNachGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		/**
		 * die in der Datenbank (Tabelle Studenten) befindlichen Daten werden ausgelesen
		 * und in Form einer Tabelle eingelesen es werden nur die Studenten angezeigt,
		 * die dem Professor zugeteilt sind, der gerade angemeldet ist!
		 */
		String[] columnNames = { "Student", "E-Mail", "Unternehmen", "Zeitraum", "Besuchsbericht", "BPS-Bericht", };
		DatenabrufStudent db = new DatenabrufStudent();
		ArrayList<Student> ausgabe = db.ausgeben();
		Collections.sort(ausgabe, new MyComparator1());

		for (int j = 0; j < ausgabe.size(); j++) {
			if (ausgabe.get(j).getProf().getAnmeldename() != null) {
				if (ausgabe.get(j).getProf().getAnmeldename().equals(anmeldename)) {
					verkuerzt2.add(ausgabe.get(j));
				}
			}
		}

		for (int i = 0; i < verkuerzt2.size(); i++) {
			unique.add(verkuerzt2.get(i));
		}

		if (unique.size() == verkuerzt2.size()) {
			for (Student i : unique) {
				verkuerzt.add(i);
			}
		}

		Collections.sort(verkuerzt, new MyComparator1());

		Object[][] data = new Object[verkuerzt.size()][6];
		for (int i = 0; i < verkuerzt.size(); i++) {

			data[i][0] = verkuerzt.get(i).getNachname() + ", " + verkuerzt.get(i).getVorname();
			data[i][1] = verkuerzt.get(i).getEmail();
			data[i][2] = verkuerzt.get(i).getUnternehmen().getName();
			data[i][3] = verkuerzt.get(i).getBeginn() + " - " + verkuerzt.get(i).getEnde();
			if (verkuerzt.get(i).getBesuchsbericht().equals(" ")) {
				data[i][4] = "Erstellen";
			} else {
				data[i][4] = "Besuchsbericht erstellt";
			}
			data[i][5] = verkuerzt.get(i).getBericht();
		}

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
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
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(AbmeldeButton).addGap(10)
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
			for (int i = 0; i < verkuerzt.size(); i++) {
				/**
				 * Renderer bei noch nicht geschriebenem Besuchsbericht und der gesamten Spalte
				 * 2 und 5
				 */
				if (verkuerzt.get(i).getBesuchsbericht().equals(" ") || column == 5 || column == 2) {
					if (row == i || column == 5) {
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
			for (int i = 0; i < verkuerzt.size(); i++) {
				/**
				 * Button bei noch nicht geschriebenem Besuchsbericht und der gesamten Spalte 2
				 * und 5
				 */
				if (verkuerzt.get(i).getBesuchsbericht().equals(" ") || column == 5 || column == 2) {
					buttonRow = row;
					buttonColumn = column;
					if (row == i || column == 5) {
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
				/**
				 * Öffne ein neues Fenster, wenn der Button geklickt wird Button ist in Spalte
				 * 5: Wenn der Bericht in der Datenbank auf "nein" gesetzt ist, gibt es die
				 * Möglichkeit ihn auf "ja" zu setzen ist der Bericht bereits auf "ja" gesetzt
				 * wird dies in einer Meldung mitgeteilt
				 */
				if (buttonColumn == 5) {
					DatenabrufStudent db = new DatenabrufStudent();
					ArrayList<Student> ausgabe = db.ausgeben();
					Collections.sort(ausgabe, new MyComparator1());
					if (verkuerzt.get(buttonRow).getBericht().equals("nein")) {
						int option = JOptionPane.showOptionDialog(null, "Wollen sie den Status ändern? ", "Bestätigung",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								new String[] { "Ja", "Zurück" }, "Zurück");

						/**
						 * Bericht wird beim jeweiligen Studenten in der Datenbank auf "ja" gesetzt
						 */
						if (option == JOptionPane.YES_OPTION) {
							db.einlesenbericht(verkuerzt.get(buttonRow).getMatrikelnr());
							verkuerzt.clear();
							verkuerzt2.clear();
							unique.clear();
							frame.dispose();
							ProfessorenNachGUI neu = new ProfessorenNachGUI(anmeldename);
							neu.main(null);

						}
					} else {
						JOptionPane.showMessageDialog(null, "BPS-Bericht wurde schon auf ja gesetzt",
								"Informationen zum Unternehmen", JOptionPane.INFORMATION_MESSAGE);
					}

				}
				/**
				 * Button ist in Spalte 4: wenn der Besuchsbericht noch nicht erstellt wurde,
				 * gibt es die Möglichkeit einen Besuchsbericht zu verfassen
				 */
				if (buttonColumn == 4) {
					JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
					JTextArea textArea = new JTextArea(45, 100);
					if (verkuerzt.get(buttonRow).getBesuchsbericht().equals(" ")) {
						textArea.setText("Bitte hier Besuchbericht reinschreiben");
					}
					panel.add(new JScrollPane(textArea));

					int result = JOptionPane.showConfirmDialog(null, panel,
							"Besuchsbericht " + verkuerzt.get(buttonRow).getVorname() + " "
									+ verkuerzt.get(buttonRow).getNachname(),
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					/**
					 * mit Klick auf OK wird der Besuchsbericht in der Tabelle Studenten in der
					 * Datenbank gespeichert
					 */
					if (result == JOptionPane.OK_OPTION) {
						String input = textArea.getText();
						DatenabrufProfessor db = new DatenabrufProfessor();
						db.BesuchsberichtErstellen(input, verkuerzt.get(buttonRow).getMatrikelnr());

						verkuerzt.clear();
						verkuerzt2.clear();
						unique.clear();
						frame.dispose();

						ProfessorenNachGUI neue = new ProfessorenNachGUI(anmeldename);
						neue.main(null);

					}

				}
				/**
				 * wenn der Button in Spalte 2 ist werden alle Unternehmensdaten von dem
				 * jeweiligen Studenten angezeigt, auf das geklickt wurde
				 */
				if (buttonColumn == 2) {
					DatenabrufStudent db = new DatenabrufStudent();
					ArrayList<Unternehmen> unternehmenls = db.ausUnternehmen();

					for (int i = 0; i <= unternehmenls.size() - 1; i++) {
						if (verkuerzt.get(buttonRow).getAnmeldename()
								.equals(unternehmenls.get(i).getanmeldesnamedesstudenten())) {
							String message = "Name: " + unternehmenls.get(i).getName() + "\n" + "Anschrift: "
									+ unternehmenls.get(i).getAnschrift() + "\n" + "URL: "
									+ unternehmenls.get(i).getUrl() + "\n" + "E-Mail: "
									+ unternehmenls.get(i).getEmail() + "\n" + "Firmen-Betreuer: "
									+ unternehmenls.get(i).getBetreuer() + "\n" + "Telefonnummer: "
									+ unternehmenls.get(i).getTelefon() + "\n" + "Abteilung: "
									+ unternehmenls.get(i).getBereich() + "\n" + "Zeitraum: "
									+ verkuerzt.get(buttonRow).getBeginn() + " - " + verkuerzt.get(buttonRow).getEnde()
									+ "\n" + "Themenbereich: " + unternehmenls.get(i).getThema() + "\n"
									+ "Beschreibung: " + unternehmenls.get(i).getBeschreibung() + "\n";
							JOptionPane.showMessageDialog(null, message, "Informationen zum Unternehmen",
									JOptionPane.INFORMATION_MESSAGE);

						}
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
		ProfessorenNachGUI.frame = new JFrame("FELD-Professoren");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ProfessorenNachGUI.class.getResource("/gui/Logo.png")));

		// Create and set up the content pane.
		ProfessorenNachGUI newContentPane = new ProfessorenNachGUI(anmeldename);
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
