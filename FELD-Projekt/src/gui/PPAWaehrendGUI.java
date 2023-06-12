package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import datenbank.DatenabrufProfessor;
import datenbank.DatenabrufStatus;
import datenbank.DatenabrufStudent;
import mail.Mail;
import objekte.Professor;
import objekte.Student;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * PPA-Maske während der Zuteilung
 */
public class PPAWaehrendGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;
	private JTextField textField;

	public PPAWaehrendGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		/*
		 * die in der Datenbank (Tabelle Studenten) befindlichen Daten werden ausgelesen
		 * und in Form einer Tabelle eingelesen
		 */
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

		JScrollPane scrollbar = new JScrollPane(table);

		JButton beenden = new JButton("Zuteilung beenden");
		beenden.setFont(new Font("Dialog", Font.PLAIN, 13));
		beenden.setBackground(new Color(255, 0, 0));
		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * alle Studenten müssen einem Professor zugeordnet sein erst dann kann die
				 * Zuteilung beendet werden
				 */
				boolean erlaubt = true;
				for (int i = 0; i < ausgabe.size(); i++) {
					if (ausgabe.get(i).getProf().getId() == 0) {
						erlaubt = false;
						break;
					}
				}

				/*
				 * ist jedem Studenten ein Professor zugeteilt wird eine Mail an alle Personen
				 * in der Datenbank versendet der Status der Zuteilung in der Datenbank wird nun
				 * von 0 auf 1 gesetzt (damit werden neue andere Masken freigeschalten
				 */
				if (erlaubt) {
					Mail mail = new Mail();
					mail.send();

					DatenabrufStatus db = new DatenabrufStatus();
					db.aendern(1);

					frame.setVisible(false);
					PPANachGUI ppa = new PPANachGUI(anmeldename);
					ppa.main(null);

					/*
					 * es gibt noch Studenten, die keinem Professor zugeteilt sind (Zuteiungsprozess
					 * kann noch nicht abgeschlossen werden)
					 */
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
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(beenden, Alignment.TRAILING)
						.addComponent(scrollbar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 920, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(nichterlaubt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 745, Short.MAX_VALUE)
							.addComponent(AbmeldeButton)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(AbmeldeButton)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nichterlaubt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(beenden)
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
				 * Renderer, wenn Student noch keinem Professor zugeteilt ist
				 */
				if (ausgabe.get(i).getProf().getNachname() == null) {
					if (row == i) {
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
				 * Button, wenn Student noch keinem Professor zugeteilt ist
				 */
				if (ausgabe.get(row).getProf().getNachname() == null) {
					buttonRow = row;
					buttonColumn = column;
					
						label = (value == null) ? "" : value.toString();
						button.setText(label);
						isPushed = true;
						return button;
				} else {
					label = (value == null) ? "" : value.toString();
					return new JLabel(label);
					
				}
	
		}

		public Object getCellEditorValue() {
			if (isPushed) {
				/*
				 * Öffne ein neues Fenster, wenn der Button geklickt wird
				 */
				DatenabrufProfessor db2 = new DatenabrufProfessor();
				ArrayList<Professor> ausgabeprof = db2.ausgeben();

				/*
				 * alle Professoren, die sich in der Datenbank befinden werden ausgelesen, damit
				 * diese ausgewählt werden können es kann maximal ein Professor ausgewählt
				 * werden (ButtonGroup)
				 */
				Object[] message = new Object[ausgabeprof.size() - 1];
				ButtonGroup x = new ButtonGroup();
				JRadioButton[] tmp = new JRadioButton[ausgabeprof.size() - 1];

				for (int i = 1; i < ausgabeprof.size(); i++) {
					tmp[i - 1] = new JRadioButton(
							ausgabeprof.get(i).getNachname() + ", " + ausgabeprof.get(i).getVorname());
					x.add(tmp[i - 1]);
					message[i - 1] = tmp[i - 1];
				}

				int option = JOptionPane.showOptionDialog(null, message, "Professoren-Zuteilung",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Auswählen", "Zurück" }, "Zurück");

				/*
				 * ausgewählter Professor wird beim jeweiligen Studenten in der Datenbank
				 * (Tabelle Student) eingetragen
				 */
				if (option == JOptionPane.YES_OPTION) {
					int nummer = ausgabe.get(buttonRow).getMatrikelnr();

					for (int i = 0; i < ausgabeprof.size() - 1; i++) {
						if (((JRadioButton) message[i]).isSelected()) {
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PPAWaehrendGUI.class.getResource("/gui/Logo.png")));

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
