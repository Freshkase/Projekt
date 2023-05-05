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
import datenbank.DatenabrufStatus;
import objekte.Professor;
import objekte.Student;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AnmeldungGUI {

	
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(176, Short.MAX_VALUE))
		);
		
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
				RegistrierungGUI registrieren = new RegistrierungGUI();
				registrieren.setVisible(true);
				frame.dispose();
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

				if (dbstatus.ausgeben() == 0) {
					if (name.charAt(1) == '.') {
						DatenabrufPPA dbppa = new DatenabrufPPA();
						ArrayList<Professor> ausgabeppa = dbppa.ausgeben();

						for (int i = 0; i < ausgabeppa.size(); i++) {
							if (name.equals(ausgabeppa.get(i).getAnmeldename())) {
								if (ausgabeppa.get(i).getKennwort().equals(passwort)) {
									PPAWaehrendGUI ppa = new PPAWaehrendGUI(name);
									ppa.main(null);
									frame.dispose();
								}
							} else {
								textField.setText(null);
								passwordField.setText(null);
								lblNewLabel.setText("Falsche Eingabe!");
								lblNewLabel.setForeground(Color.RED);
							}

						}

					}

					boolean studentenpruefung = true;
					for (int i = 0; i < name.length(); i++) {
						if (name.charAt(i) == '.') {
							studentenpruefung = false;
							break;
						}
					}

					if (studentenpruefung == true) {
						DatenabrufStudent dbstudent = new DatenabrufStudent();
						ArrayList<Student> ausgabestudent = dbstudent.ausgeben();

						for (int i = 0; i < ausgabestudent.size(); i++) {
							if (name.equals(ausgabestudent.get(i).getAnmeldename())) {
								if (ausgabestudent.get(i).getKennwort().equals(passwort)) {
									StudentGUI student = new StudentGUI(name);
									student.main(null);
									frame.dispose();
								}
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

						for (int i = 0; i < ausgabeprofessor.size(); i++) {
							if (name.equals(ausgabeprofessor.get(i).getAnmeldename())) {
								if (ausgabeprofessor.get(i).getKennwort().equals(passwort)) {
									ProfessorenWaehrendGUI professor = new ProfessorenWaehrendGUI(name);
									professor.main(null);
									frame.dispose();
								}
							} else {
								textField.setText(null);
								passwordField.setText(null);
								lblNewLabel.setText("Falsche Eingabe!");
								lblNewLabel.setForeground(Color.RED);
							}

						}
					}

				} else {
					//ab hier ändern mit neuen GUI's
					
					if (name.charAt(1) == '.') {
						DatenabrufPPA dbppa = new DatenabrufPPA();
						ArrayList<Professor> ausgabeppa = dbppa.ausgeben();

						for (int i = 0; i < ausgabeppa.size(); i++) {
							if (name.equals(ausgabeppa.get(i).getAnmeldename())) {
								if (ausgabeppa.get(i).getKennwort().equals(passwort)) {
									PPANachGUI ppa = new PPANachGUI(name);
									ppa.main(null);
									frame.dispose();
								}
							} else {
								textField.setText(null);
								passwordField.setText(null);
								lblNewLabel.setText("Falsche Eingabe!");
								lblNewLabel.setForeground(Color.RED);
							}

						}

					}

					boolean studentenpruefung = true;
					for (int i = 0; i < name.length(); i++) {
						if (name.charAt(i) == '.') {
							studentenpruefung = false;
							break;
						}
					}

					if (studentenpruefung == true) {
						DatenabrufStudent dbstudent = new DatenabrufStudent();
						ArrayList<Student> ausgabestudent = dbstudent.ausgeben();

						for (int i = 0; i < ausgabestudent.size(); i++) {
							if (name.equals(ausgabestudent.get(i).getAnmeldename())) {
								if (ausgabestudent.get(i).getKennwort().equals(passwort)) {
									StudentGUI student = new StudentGUI(name);
									student.main(null);
									frame.dispose();
								}
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

						for (int i = 0; i < ausgabeprofessor.size(); i++) {
							if (name.equals(ausgabeprofessor.get(i).getAnmeldename())) {
								if (ausgabeprofessor.get(i).getKennwort().equals(passwort)) {
									ProfessorenNachGUI professor = new ProfessorenNachGUI(name);
									professor.main(null);
									frame.dispose();
								}
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

		});
		
		JLabel lblNewLabel_3 = new JLabel("@hft-stuttgart.de");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addContainerGap(336, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordField, Alignment.LEADING)
								.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAnmelden)
								.addComponent(lblNewLabel_3))
							.addGap(24))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnAnmelden, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
