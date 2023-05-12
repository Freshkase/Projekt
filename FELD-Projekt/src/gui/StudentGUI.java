package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import datenbank.DatenabrufStudent;
import objekte.Student;

import java.awt.Font;

public class StudentGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;


	public StudentGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		DatenabrufStudent db = new DatenabrufStudent();
		ArrayList<Student> ausgabe = db.ausgeben();
		int tmp = 0;
		for (int i = 0; i < ausgabe.size(); i++) {
			if (ausgabe.get(i).getAnmeldename().equals(anmeldename)) {
				tmp = i;
			}
		}

		String[] columnNames = { "Bericht", "Tätigkeitsnachweis", "BPS-Vortrag", };

		Object[][] data = new Object[1][3];
		data[0][0] = ausgabe.get(tmp).getBericht();
		data[0][1] = ausgabe.get(tmp).getTätigkeitsnachweis();
		data[0][2] = ausgabe.get(tmp).getVortrag();

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

		// Create the scroll pane and add the table to it.
		JScrollPane scrollbar = new JScrollPane(table);

		JLabel profil = new JLabel("Profil:");

		JLabel name = new JLabel(ausgabe.get(tmp).getNachname() + ", " + ausgabe.get(tmp).getVorname());
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel emailfix = new JLabel("Zugeteilter Professor:");

		JLabel status = new JLabel("Status:");

		JLabel lblNewLabel;
		if (ausgabe.get(tmp).getProf().getId() == 0) {
			lblNewLabel = new JLabel("noch nicht vergeben");
		} else {
			lblNewLabel = new JLabel(
					ausgabe.get(tmp).getProf().getNachname() + ", " + ausgabe.get(tmp).getProf().getVorname());
		}
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel matrikelnummerfix = new JLabel("Matrikelnummer:");
		matrikelnummerfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel namefix = new JLabel("Name:");
		namefix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel matrikelnummer = new JLabel(Integer.toString(ausgabe.get(tmp).getMatrikelnr()));
		matrikelnummer.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel lblNewLabel_1 = new JLabel("E-Mail:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel lblNewLabel_2 = new JLabel(ausgabe.get(tmp).getEmail());
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel unternehmenfix = new JLabel("Unternehmen:");
		unternehmenfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel unternehmen = new JLabel(ausgabe.get(tmp).getUnternehmen().getName());
		unternehmen.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JButton AbmeldeButton = new JButton("Abmelden");
		AbmeldeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AnmeldungGUI neu = new AnmeldungGUI();
				neu.main(null);
			
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(profil)
										.addContainerGap(450, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(17, Short.MAX_VALUE)
										.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 470,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(status)
										.addContainerGap(444, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(emailfix)
										.addContainerGap(352, Short.MAX_VALUE))
								.addGroup(groupLayout
										.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
										.addContainerGap(389, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(matrikelnummerfix).addComponent(namefix)
												.addComponent(lblNewLabel_1).addComponent(unternehmenfix))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(unternehmen).addComponent(lblNewLabel_2)
												.addComponent(name).addComponent(matrikelnummer))
										.addContainerGap(330, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap(26, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(AbmeldeButton)
											.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 930, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(26, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(profil).addGap(7)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(AbmeldeButton)
				.addGap(10)
				.addGap(5) 
				.addComponent(namefix).addComponent(name))
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(matrikelnummerfix)
						.addComponent(matrikelnummer))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(unternehmenfix)
						.addComponent(unternehmen))
				.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE).addComponent(emailfix)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel).addGap(23).addComponent(status)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollbar, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addGap(20)));
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
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	public static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("FELD-Student");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		StudentGUI newContentPane = new StudentGUI(anmeldename);
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
