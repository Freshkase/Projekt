package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableCellRenderer;
import datenbank.DatenabrufStudent;
import mail.Mail2;
import objekte.Student;
import sortierung.MyComparator3;
import sortierung.MyComparator5;

//PPA-Maske nach Zuteilung
public class PPANachGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;

	public PPANachGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		//die in der Datenbank (Tabelle Studenten) befindlichen Daten werden ausgelesen und in Form einer Tabelle eingelesen
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

		JScrollPane scrollbar = new JScrollPane(table);
		
		JButton senden = new JButton("Senden");
		senden.setFont(new Font("Dialog", Font.PLAIN, 13));
		senden.setBackground(new Color(0, 128, 255));
		senden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//zuerst werden die Daten in die .csv Datei aus der Datenbank eingelesen
				//im Anschluss wird eine E-Mail an das Prüfungsamt versendet mit der .csv Datei als Anhang
				try (PrintWriter writer = new PrintWriter(new File("Ergebnisse_BPS.csv"))) {
					DatenabrufStudent db = new DatenabrufStudent();
					ArrayList<Student> ausgabe = db.ausgeben();
					Collections.sort(ausgabe,new MyComparator5());
			        StringBuilder sb = new StringBuilder();
			    	sb.append("Nachname");
		        	sb.append(',');
		        	sb.append("Vorname");
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
			        	sb.append(i.getUnternehmen().getName());
			        	sb.append(',');
			        	sb.append(i.getBeginn());
			        	sb.append(',');
			        	sb.append(i.getEnde());
			        	sb.append(',');
			        	sb.append(i.getProf().getVorname()+ " " + i.getProf().getNachname());
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
			        Mail2 mail = new Mail2();
					mail.send();  
					JOptionPane.showMessageDialog(null, "Die E-Mail wurde erfolgreich an das Prüfungsamt versendet.", "Bestätigung", JOptionPane.INFORMATION_MESSAGE);
       		

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
						.addComponent(senden, Alignment.TRAILING)
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
					.addComponent(senden)
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

	//TableCellRenderer für das JButton-Objekt (innerhalb der Tabelle)
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
				//Renderer, wenn Besuchsbericht verfasst wurde und in Spalte 7
				if ((!ausgabe.get(i).getBesuchsbericht().equals(" ")) || column == 7) {
					if (row == i || column == 7) {
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

	//TableCellEditor für das JButton-Objekt (innerhalb der Tabelle)
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
				//Button, wenn Besuchsbericht verfasst wurde und in Spalte 7
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

				//Button ist in Spalte 7: Wenn der Vortrag in der Datenbank auf "nein" gesetzt ist, gibt es die Möglichkeit ihn auf "ja" zu setzen
				//ist der Bericht bereits auf "ja" gesetzt wird dies in einer Meldung mitgeteilt
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
						JOptionPane.showMessageDialog(null, "BPS-Vortrag wurde schon auf ja gesetzt",
								"Informationen zum Unternehmen", JOptionPane.INFORMATION_MESSAGE);
					}

				//anderenfalls ist der Besuchsbericht schon geschrieben
				//es wird eine Scrollbar mit dem vom Professor geschriebenen Besuchsbericht geöffnet, damit das PPA ihn lesen kann
				} else {
					JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
					JTextArea textArea = new JTextArea(45, 100);
					textArea.setText(ausgabe.get(buttonRow).getBesuchsbericht());
					panel.add(new JScrollPane(textArea));
					String message = "Geschrieben von Professor " + ausgabe.get(buttonRow).getProf().getNachname()
							+ " über " + ausgabe.get(buttonRow).getVorname() + " "
							+ ausgabe.get(buttonRow).getNachname();
					String header = "Besuchsbericht: " + message;
					
					JOptionPane.showMessageDialog(null, panel, header, JOptionPane.INFORMATION_MESSAGE);
					
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PPANachGUI.class.getResource("/gui/Logo.png")));

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
