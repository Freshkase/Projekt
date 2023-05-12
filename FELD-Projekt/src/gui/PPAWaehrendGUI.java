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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableCellRenderer;
import datenbank.DatenabrufStudent;
import gui.ProfessorenWaehrendGUI.ButtonEditor;
import gui.ProfessorenWaehrendGUI.ButtonRenderer;
import objekte.Professor;
import objekte.Student;
import datenbank.DatenabrufStatus;
import datenbank.DatenabrufProfessor;

public class PPAWaehrendGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;

	public PPAWaehrendGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		String[] columnNames = { "Name", "E-Mail", "Unternehmen", "Betreuer", };

		DatenabrufStudent db = new DatenabrufStudent();
		ArrayList<Student> ausgabe = db.ausgeben();

		Object[][] data = new Object[ausgabe.size()][4];
		for (int i = 0; i < ausgabe.size(); i++) {
			data[i][0] = ausgabe.get(i).getNachname() + ", " + ausgabe.get(i).getVorname();
			data[i][1] = ausgabe.get(i).getEmail();
			data[i][2] = ausgabe.get(i).getUnternehmen().getName();
			if (ausgabe.get(i).getProf().getNachname() == null) {
				data[i][3] = "auswählen";
			} else {
				data[i][3] = ausgabe.get(i).getProf().getNachname() + ", " + ausgabe.get(i).getProf().getVorname();
			}

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

		JLabel nichterlaubt = new JLabel("");
		nichterlaubt.setForeground(new Color(255, 38, 0));

		// Create the scroll pane and add the table to it.
		JScrollPane scrollbar = new JScrollPane(table);

		JButton btnNewButton = new JButton("Zuteilung beenden");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean erlaubt = true;
				for (int i = 0; i < ausgabe.size(); i++) {
					if (ausgabe.get(i).getProf().getId() == 0) {
						erlaubt = false;
						break;
					}
				}
				if (erlaubt) {
					Mail mail = new Mail();
					mail.send();

					DatenabrufStatus db = new DatenabrufStatus();
					db.aendern(1);

					frame.setVisible(false);
					PPANachGUI ppa = new PPANachGUI(anmeldename);
					ppa.main(null);

				} else {
					nichterlaubt.setText(
							"Bitte teilen Sie zunächst jedem Studierenden einen Professor zu, bevor Sie die Zuteilung beenden.");
				}
			}
		});

		JButton AbmeldeButton = new JButton("Abmelden");
		AbmeldeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				AnmeldungGUI neu = new AnmeldungGUI();
				neu.main(null);
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING)
						.addComponent(scrollbar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 920, GroupLayout.PREFERRED_SIZE)
						.addComponent(AbmeldeButton, Alignment.TRAILING))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(AbmeldeButton)
					.addGap(10)
					.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
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

			for (int i = 0; i < ausgabe.size(); i++) {
				if (ausgabe.get(i).getProf().getNachname() == null) {
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

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {

			for (int i = 0; i < ausgabe.size(); i++) {
				if (ausgabe.get(i).getProf().getNachname() == null) {
					buttonRow = row;
					buttonColumn = column;
					if (row == i || column == 2) {
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
				DatenabrufProfessor db2 = new DatenabrufProfessor();
				ArrayList<Professor> ausgabeprof = db2.ausgeben();

				Object[] message = new Object[ausgabeprof.size() - 1];
				for (int i = 1; i < ausgabeprof.size(); i++) {
					message[i - 1] = new JCheckBox(
							ausgabeprof.get(i).getNachname() + ", " + ausgabeprof.get(i).getVorname());
				}

				int option = JOptionPane.showOptionDialog(null, message, "Professoren-Zuteilung",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Auswählen", "Zurück" }, "Zurück");

				if (option == JOptionPane.YES_OPTION) {
					// Hier kann dann in die Datenbank eingelesen werden
					int nummer = ausgabe.get(buttonRow).getMatrikelnr();

					for (int i = 0; i < ausgabeprof.size() - 1; i++) {
						if (((JCheckBox) message[i]).isSelected()) {
							db.aendern(ausgabeprof.get(i + 1).getId(), nummer);
						}

					}

					frame.dispose();
					PPAWaehrendGUI neu = new PPAWaehrendGUI(anmeldename);
					neu.main(null);
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
		PPAWaehrendGUI.frame = new JFrame("FELD-PPA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		PPAWaehrendGUI newContentPane = new PPAWaehrendGUI(anmeldename);
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
