package projekt;
import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrierung extends JFrame {
	private JPasswordField passwort;

    public Registrierung() {
       
        setTitle("Registrierungsformular");
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportBorder(null);
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_1.setBorder(null);

      
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
        );
        
        JPanel panel = new JPanel();
        scrollPane_1.setViewportView(panel);
        JTextField anmeldename = new JTextField(20);
        
               
                JLabel nameLabel = new JLabel("Anmeldename:");
        
                JLabel matrikelnummerLabel = new JLabel("Kennwort:");
        JTextField email = new JTextField(10);
        JTextField name = new JTextField(20);
        
                JLabel emailLabel = new JLabel("Name, Vorname:");
        JTextField matrikelnummer = new JTextField(20);
        
                JLabel benutzernameLabel = new JLabel("Matrikelnummer");
        
                JLabel passwortLabel = new JLabel("E-Mail");
        
                JLabel unternehmenLabel = new JLabel("Unternehmen:");
        JTextField unternehmen = new JTextField(20);
        
                JLabel beschreibungLabel = new JLabel("Beschreibung:");
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        JLabel lblhftstuttgartde = new JLabel("@hft-stuttgart.de");
        JTextArea beschreibungTextArea = new JTextArea(5, 20);
        beschreibungTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
        scrollPane.setViewportView(beschreibungTextArea);
        passwort = new JPasswordField(20);
        JButton registrierenButton = new JButton("Registrieren");
        registrierenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DatenabrufStudent student = new DatenabrufStudent();
				String [] namen = name.getText().split(",");
				student.einlesen(Integer.parseInt(matrikelnummer.getText()), namen[0], namen[1], email.getText(), anmeldename.getText(), passwort.getText(), beschreibungTextArea.getText(), unternehmen.getText());
				Registration.main(null);
				dispose();
				
			}
        	
        });
        registrierenButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        registrierenButton.setBackground(new Color(0, 128, 255));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(30, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
        				.addComponent(beschreibungLabel)
        				.addComponent(unternehmen, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
        				.addComponent(unternehmenLabel)
        				.addComponent(passwortLabel)
        				.addComponent(matrikelnummer, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
        				.addComponent(benutzernameLabel)
        				.addComponent(emailLabel)
        				.addComponent(passwort, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
        				.addComponent(matrikelnummerLabel)
        				.addComponent(nameLabel)
        				.addComponent(anmeldename, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
        					.addGroup(gl_panel.createSequentialGroup()
        						.addComponent(email, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
        						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(lblhftstuttgartde))
        					.addComponent(name, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18749))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(272)
        			.addComponent(registrierenButton)
        			.addContainerGap(18770, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(nameLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(anmeldename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(matrikelnummerLabel)
        			.addGap(4)
        			.addComponent(passwort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(emailLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(benutzernameLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(matrikelnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwortLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblhftstuttgartde))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(unternehmenLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(unternehmen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(beschreibungLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
        			.addGap(35)
        			.addComponent(registrierenButton)
        			.addGap(46))
        );
       
        panel.setLayout(gl_panel);
        
        JLabel lblNewLabel = new JLabel("Registrierung");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        scrollPane_1.setColumnHeaderView(lblNewLabel);
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        new Registrierung();
    }
}
