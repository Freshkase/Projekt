package projekt;
import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Test extends JFrame {
	private JPasswordField passwordField;

    public Test() {
        // Setzt den Titel des Fensters
        setTitle("Registrierungsformular");
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportBorder(null);
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_1.setBorder(null);

        // Erstellt das Group Layout und fügt die Komponenten hinzu
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
        JTextField nameTextField = new JTextField(20);
        
                // Erstellt die Komponenten für das Formular
                JLabel nameLabel = new JLabel("Anmeldename:");
        
                JLabel matrikelnummerLabel = new JLabel("Kennwort:");
        JTextField matrikelnummerTextField = new JTextField(10);
        JTextField emailTextField = new JTextField(20);
        
                JLabel emailLabel = new JLabel("Name, Vorname:");
        JTextField benutzernameTextField = new JTextField(20);
        
                JLabel benutzernameLabel = new JLabel("Matrikelnummer");
        
                JLabel passwortLabel = new JLabel("E-Mail");
        
                JLabel unternehmenLabel = new JLabel("Unternehmen:");
        JTextField unternehmenTextField = new JTextField(20);
        
                JLabel beschreibungLabel = new JLabel("Beschreibung:");
        
                // Erstellt den Registrierungs-Button
                JButton registrierenButton = new JButton("Registrieren");
                
                        // Fügt einen ActionListener zum Registrierungs-Button hinzu
                        registrierenButton.addActionListener(e -> {
                            // Hier könnten Sie die Benutzerdaten speichern oder validieren
                            JOptionPane.showMessageDialog(this, "Sie haben sich erfolgreich registriert!");
                        });
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        JLabel lblhftstuttgartde = new JLabel("@hft-stuttgart.de");
        
        passwordField = new JPasswordField(20);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(283)
        			.addComponent(registrierenButton)
        			.addContainerGap(16645, Short.MAX_VALUE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(30, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(passwortLabel)
        				.addComponent(benutzernameTextField, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
        				.addComponent(benutzernameLabel)
        				.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
        				.addComponent(emailLabel)
        				.addComponent(matrikelnummerLabel)
        				.addComponent(nameLabel)
        				.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
        				.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
        				.addComponent(unternehmenLabel)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(matrikelnummerTextField, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblhftstuttgartde))
        				.addComponent(unternehmenTextField, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
        				.addComponent(beschreibungLabel)
        				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
        			.addGap(18749))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(nameLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(matrikelnummerLabel)
        			.addGap(11)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(emailLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(benutzernameLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(benutzernameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwortLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(matrikelnummerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblhftstuttgartde))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(unternehmenLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(unternehmenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(beschreibungLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
        			.addGap(25)
        			.addComponent(registrierenButton)
        			.addGap(24))
        );
        JTextArea beschreibungTextArea = new JTextArea(5, 20);
        beschreibungTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
        scrollPane.setViewportView(beschreibungTextArea);
        panel.setLayout(gl_panel);
        
        JLabel lblNewLabel = new JLabel("Registeriung");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        scrollPane_1.setColumnHeaderView(lblNewLabel);
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Setzt die Größe und sichtbarkeit des Fensters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        // Erstellt eine neue Instanz des Registrierungsformulars
        new Test();
    }
}
