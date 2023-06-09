package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

import datenbank.DatenabrufPPA;
import datenbank.DatenabrufProfessor;
import datenbank.DatenabrufStudent;
import datenbank.DatenabrufStudierendensekretariat;
import datenbank.DatenabrufStatus;
import objekte.Professor;
import objekte.Sekretaerin;
import objekte.Student;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * Anmeldungsmaske
 */
public class AnmeldungGUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnmeldungGUI window = new AnmeldungGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnmeldungGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("FELD");

		/*
		 * Logo einfügen und Logo als Icon
		 */
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGUI.class.getResource("/gui/Logo.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(176, Short.MAX_VALUE)));

		JPanel panel = new JPanel();
		panel_1.add(panel);

		JLabel lblNewLabel = new JLabel("Anmeldung");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

		textField = new JTextField();
		textField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("E-Mail:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel lblNewLabel_2 = new JLabel("Kennwort:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JButton btnNewButton = new JButton("Registrieren");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatenabrufStatus dbstatus = new DatenabrufStatus();

				/*
				 * wenn der Status "während der Zuteilung" ist, besteht die Möglichkeit der
				 * Registrierung für den Studenten öffnen der Registrierungsmaske + schließen
				 * der Anmeldungsmaske
				 */
				if (dbstatus.ausgeben() == 0) {
					RegistrierungGUI registrieren = new RegistrierungGUI();
					registrieren.setVisible(true);
					frame.dispose();

					/*
					 * ist der Zuteilungsprozess beendet, ist keine nachträgliche Registrierung mehr
					 * möglich
					 */
				} else {
					textField.setText(null);
					passwordField.setText(null);
					lblNewLabel.setText("Der Registrierungsprozess ist leider schon vorbei.");
					lblNewLabel.setForeground(Color.RED);
				}
			}
		});

		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(254, 255, 255));

		JButton btnAnmelden = new JButton("Anmelden");

		btnAnmelden.setForeground(new Color(0, 0, 0));
		btnAnmelden.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnAnmelden.setBackground(new Color(254, 255, 255));
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = textField.getText();
				String passwort = passwordField.getText();

				DatenabrufStatus dbstatus = new DatenabrufStatus();

				/*
				 * wenn der Status == 0 (während Zuteilung) werden andere Fenster geöffnet als
				 * wenn der Status == 1 (nach Zuteilung) ist
				 */
				if (dbstatus.ausgeben() == 0) {
					/*
					 * das PPA hat an zweiter Stelle einen Punkt
					 */
					if (name.charAt(1) == '.') {
						DatenabrufPPA dbppa = new DatenabrufPPA();
						ArrayList<Professor> ausgabeppa = dbppa.ausgeben();

						/*
						 * wenn Passwort und Anmeldename mit den Daten in der Tabelle "PPA"
						 * übereinstimmt, wird die PPA-Maske geöffnet
						 */
						for (int i = 0; i < ausgabeppa.size(); i++) {
							if (name.equals(ausgabeppa.get(i).getAnmeldename())) {
								if (ausgabeppa.get(i).getKennwort().equals(passwort)) {
									PPAWaehrendGUI ppa = new PPAWaehrendGUI(name);
									ppa.main(null);
									frame.dispose();
								}
								/*
								 * anderenfalls: Rückmeldung, dass die Eingabe falsch ist
								 */
							} else {
								textField.setText(null);
								passwordField.setText(null);
								lblNewLabel.setText("Falsche Eingabe!");
								lblNewLabel.setForeground(Color.RED);
							}

						}

					}
					/*
					 * die Studenten-Email (Präfix) an der HFT enthalt keinen Punkt und kein
					 * Bindestrich
					 */
					boolean studentenpruefung = true;
					for (int i = 0; i < name.length(); i++) {
						if (name.charAt(i) == '.' || name.charAt(i) == '-') {
							studentenpruefung = false;
							break;
						}
					}

					if (studentenpruefung == true) {
						DatenabrufStudent dbstudent = new DatenabrufStudent();
						ArrayList<Student> ausgabestudent = dbstudent.ausgeben();

						/*
						 * wenn Passwort und Anmeldename mit den Daten in der Tabelle "Studenten"
						 * übereinstimmt, wird die Studenten-Maske geöffnet
						 */
						for (int i = 0; i < ausgabestudent.size(); i++) {
							if (name.equals(ausgabestudent.get(i).getAnmeldename())) {
								if (ausgabestudent.get(i).getKennwort().equals(passwort)) {
									StudentGUI student = new StudentGUI(name);
									student.main(null);
									frame.dispose();
								}
								/*
								 * anderenfalls: Rückmeldung, dass die Eingabe falsch ist
								 */
							} else {
								textField.setText(null);
								passwordField.setText(null);
								lblNewLabel.setText("Falsche Eingabe!");
								lblNewLabel.setForeground(Color.RED);
							}
						}
						/*
						 * wenn der Anmeldename einen Bindestrich enthält, handelt es sich um das
						 * Studierendensekretariat
						 */
					} else {
						boolean sekretariatpruefung = false;
						for (int i = 0; i < name.length(); i++) {
							if (name.charAt(i) == '-') {
								sekretariatpruefung = true;
								break;
							}
						}
						/*
						 * das Studierendensekretariat hat während der Zuteilung noch keinen Zugriff auf
						 * das FELD-System
						 */
						if (sekretariatpruefung) {
							textField.setText(null);
							passwordField.setText(null);
							lblNewLabel.setText("Zuteilungsprozess noch nicht beendet. Daher noch keinen Zugriff.");
							lblNewLabel.setForeground(Color.RED);
							/*
							 * andernfalls handelt es sich um einen Professor (E-Mail Präfix enthält nach
							 * dem Vornamen einen Punkt)
							 */
						} else {
							DatenabrufProfessor dbprofessor = new DatenabrufProfessor();
							ArrayList<Professor> ausgabeprofessor = dbprofessor.ausgeben();

							/*
							 * wenn Passwort und Anmeldename mit den Daten in der Tabelle "Professoren"
							 * übereinstimmt, wird die Professoren-Maske geöffnet
							 */
							for (int i = 0; i < ausgabeprofessor.size(); i++) {
								if (name.equals(ausgabeprofessor.get(i).getAnmeldename())) {
									if (ausgabeprofessor.get(i).getKennwort().equals(passwort)) {
										ProfessorenWaehrendGUI professor = new ProfessorenWaehrendGUI(name);
										professor.main(null);
										frame.dispose();
									}
									/*
									 * anderenfalls: Rückmeldung, dass die Eingabe falsch ist
									 */
								} else {
									textField.setText(null);
									passwordField.setText(null);
									lblNewLabel.setText("Falsche Eingabe!");
									lblNewLabel.setForeground(Color.RED);
								}

							}
						}
					}

					/*
					 * hier findet man die Fenster für nach der Zuteilung (Status == 1)
					 */
				} else {
					/*
					 * das PPA hat an zweiter Stelle einen Punkt
					 */
					if (name.charAt(1) == '.') {
						DatenabrufPPA dbppa = new DatenabrufPPA();
						ArrayList<Professor> ausgabeppa = dbppa.ausgeben();

						/*
						 * wenn Passwort und Anmeldename mit den Daten in der Tabelle "PPA"
						 * übereinstimmt, wird die PPA-Maske geöffnet
						 */
						for (int i = 0; i < ausgabeppa.size(); i++) {
							if (name.equals(ausgabeppa.get(i).getAnmeldename())) {
								if (ausgabeppa.get(i).getKennwort().equals(passwort)) {
									PPANachGUI ppa = new PPANachGUI(name);
									ppa.main(null);
									frame.dispose();
								}
								/*
								 * anderenfalls: Rückmeldung, dass die Eingabe falsch ist
								 */
							} else {
								textField.setText(null);
								passwordField.setText(null);
								lblNewLabel.setText("Falsche Eingabe!");
								lblNewLabel.setForeground(Color.RED);
							}

						}

					}
					/*
					 * die Studenten-Email (Präfix) an der HFT enthalt keinen Punkt und kein
					 * Bindestrich
					 */
					boolean studentenpruefung = true;
					for (int i = 0; i < name.length(); i++) {
						if (name.charAt(i) == '.' || name.charAt(i) == '-') {
							studentenpruefung = false;
							break;
						}
					}

					if (studentenpruefung == true) {
						DatenabrufStudent dbstudent = new DatenabrufStudent();
						ArrayList<Student> ausgabestudent = dbstudent.ausgeben();

						/*
						 * wenn Passwort und Anmeldename mit den Daten in der Tabelle "Studenten"
						 * übereinstimmt, wird die Studenten-Maske geöffnet
						 */
						for (int i = 0; i < ausgabestudent.size(); i++) {
							if (name.equals(ausgabestudent.get(i).getAnmeldename())) {
								if (ausgabestudent.get(i).getKennwort().equals(passwort)) {
									StudentGUI student = new StudentGUI(name);
									student.main(null);
									frame.dispose();
								}
								/*
								 * anderenfalls: Rückmeldung, dass die Eingabe falsch ist
								 */
							} else {
								textField.setText(null);
								passwordField.setText(null);
								lblNewLabel.setText("Falsche Eingabe!");
								lblNewLabel.setForeground(Color.RED);
							}

						}
						/*
						 * wenn der Anmeldename einen Bindestrich enthält, handelt es sich um das
						 * Studierendensekretariat
						 */
					} else {
						boolean sekretariatpruefung = false;
						for (int i = 0; i < name.length(); i++) {
							if (name.charAt(i) == '-') {
								sekretariatpruefung = true;
								break;
							}
						}

						if (sekretariatpruefung) {
							DatenabrufStudierendensekretariat dbstudierendensekretariat = new DatenabrufStudierendensekretariat();
							ArrayList<Sekretaerin> ausgabesk = dbstudierendensekretariat.ausgeben();
							/*
							 * nun hat das Studierendensekretariat auch Zugriff (Status == 1) wenn Passwort
							 * und Anmeldename mit den Daten in der Tabelle "Studierendensekretariat"
							 * übereinstimmt, wird die Sekretariats-Maske geöffnet
							 */
							for (int i = 0; i < ausgabesk.size(); i++) {
								if (name.equals(ausgabesk.get(i).getAnmeldename())) {
									if (ausgabesk.get(i).getKennwort().equals(passwort)) {
										StudierendensekretariatGUI sk = new StudierendensekretariatGUI(name);
										sk.main(null);
										frame.dispose();
									}
									/*
									 * anderenfalls: Rückmeldung, dass die Eingabe falsch ist
									 */
								} else {
									textField.setText(null);
									passwordField.setText(null);
									lblNewLabel.setText("Falsche Eingabe!");
									lblNewLabel.setForeground(Color.RED);
								}
							}
						} else {
							DatenabrufProfessor dbprofessor = new DatenabrufProfessor();
							ArrayList<Professor> ausgabeprofessor = dbprofessor.ausgeben();

							/*
							 * wenn Passwort und Anmeldename mit den Daten in der Tabelle "Professoren"
							 * übereinstimmt, wird die Professoren-Maske geöffnet
							 */
							for (int i = 0; i < ausgabeprofessor.size(); i++) {
								if (name.equals(ausgabeprofessor.get(i).getAnmeldename())) {
									if (ausgabeprofessor.get(i).getKennwort().equals(passwort)) {
										ProfessorenNachGUI professor = new ProfessorenNachGUI(name);
										professor.main(null);
										frame.dispose();
									}
									/*
									 * anderenfalls: Rückmeldung, dass die Eingabe falsch ist
									 */
								} else {
									textField.setText(null);
									passwordField.setText(null);
									lblNewLabel.setText("Falsche Eingabe!");
									lblNewLabel.setForeground(Color.RED);
								}
							}
						}
					}
				}

			}

		});

		JLabel lblNewLabel_3 = new JLabel("@hft-stuttgart.de");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(19)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_2).addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(passwordField, Alignment.LEADING)
										.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 275,
												Short.MAX_VALUE)
										.addComponent(btnNewButton, Alignment.LEADING))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(btnAnmelden)
										.addComponent(lblNewLabel_3))
								.addGap(24))))
				.addGroup(gl_panel.createSequentialGroup().addGap(20)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_1)
										.addContainerGap(376, Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1).addGap(5)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNewLabel_2)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(btnAnmelden, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(21)));
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
