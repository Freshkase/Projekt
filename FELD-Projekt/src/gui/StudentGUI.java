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
import java.awt.Toolkit;

/**
 * Studenten-Maske
 */
public class StudentGUI extends JPanel {
	private boolean DEBUG = false;
	private static String anmeldename;
	private static JFrame frame;

	public StudentGUI(String anmeldename) {

		this.anmeldename = anmeldename;

		DatenabrufStudent db = new DatenabrufStudent();
		ArrayList<Student> ausgabe = db.ausgeben();

		/*
		 * von dem Studenten die jeweiligen Informationen anzeigen, der sich auch
		 * angemeldet hat (daher Variable tmp) diese Variable wird im Verlauf dann immer
		 * genutzt, um die Informationen des angemeldeten Studenten auszugeben
		 */
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

		JScrollPane scrollbar = new JScrollPane(table);

		JLabel profil = new JLabel("Profil:");

		JLabel name = new JLabel(ausgabe.get(tmp).getNachname() + ", " + ausgabe.get(tmp).getVorname());
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel professorfix = new JLabel("Zugeteilter Professor:");

		JLabel status = new JLabel("Status:");

		JLabel professor;
		if (ausgabe.get(tmp).getProf().getId() == 0) {
			professor = new JLabel("noch nicht vergeben");
		} else {
			professor = new JLabel(
					ausgabe.get(tmp).getProf().getNachname() + ", " + ausgabe.get(tmp).getProf().getVorname());
		}
		professor.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel matrikelnummerfix = new JLabel("Matrikelnummer:");
		matrikelnummerfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel namefix = new JLabel("Name:");
		namefix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel matrikelnummer = new JLabel(Integer.toString(ausgabe.get(tmp).getMatrikelnr()));
		matrikelnummer.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel emailfix = new JLabel("E-Mail:");
		emailfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel email = new JLabel(ausgabe.get(tmp).getEmail());
		email.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

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
										.addContainerGap(0, 900))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(status)
										.addContainerGap(444, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(professorfix).addContainerGap(352, Short.MAX_VALUE))
								.addGroup(groupLayout
										.createSequentialGroup().addContainerGap().addComponent(professor)
										.addContainerGap(389, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(matrikelnummerfix).addComponent(namefix)
												.addComponent(emailfix).addComponent(unternehmenfix))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(unternehmen).addComponent(email).addComponent(name)
												.addComponent(matrikelnummer))
										.addContainerGap(330, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(26, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(AbmeldeButton))
										.addContainerGap(0, 10)));

		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(profil)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(AbmeldeButton).addComponent(namefix).addComponent(name))
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(matrikelnummerfix).addComponent(matrikelnummer))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(emailfix).addComponent(email))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(unternehmenfix).addComponent(unternehmen))
										.addPreferredGap(ComponentPlacement.RELATED).addGap(15)
										.addComponent(professorfix).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(professor).addGap(23).addComponent(status)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollbar,
												GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
										.addGap(20)));
		setLayout(groupLayout);
	}

	public static void createAndShowGUI() {
		// Create and set up the window.
		StudentGUI.frame = new JFrame("FELD-Student");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(StudentGUI.class.getResource("/gui/Logo.png")));

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
