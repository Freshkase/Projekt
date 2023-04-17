package projekt;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Anmeldung {

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
					Anmeldung window = new Anmeldung();
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
	public Anmeldung() {
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
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		
		
		textField = new JTextField();
		textField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Anmeldename:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel lblNewLabel_2 = new JLabel("Kennwort:");
		
		JButton btnNewButton = new JButton("Registrieren");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrierung registrieren = new Registrierung();
				registrieren.setVisible(true);;
				frame.dispose();
			}
		});
		
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton.setEnabled(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 128, 255));
		
		JButton btnAnmelden = new JButton("Anmelden");
		
		btnAnmelden.setForeground(UIManager.getColor("Button.background"));
		btnAnmelden.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnAnmelden.setBackground(new Color(0, 128, 255));
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DatenabrufStudent db = new DatenabrufStudent();
		        ArrayList<Student> ausgabe = db.ausgeben();
		        
		        String name = textField.getText();
		        String passwort = passwordField.getText();
		        for (int i = 0; i < ausgabe.size(); i++) {
		        	if(name.equals(ausgabe.get(i).getAnmeldename())) {
		        		if (ausgabe.get(i).getKennwort().equals(passwort)) {
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
				
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addComponent(lblNewLabel_1)))
					.addContainerGap(329, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(passwordField, Alignment.LEADING)
						.addComponent(lblNewLabel_2, Alignment.LEADING)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAnmelden)
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(9)
					.addComponent(lblNewLabel_1)
					.addGap(2)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
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
