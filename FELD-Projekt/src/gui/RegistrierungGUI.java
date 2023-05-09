package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import datenbank.DatenabrufStudent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrierungGUI extends JFrame {
	private JTextField nachname;
	private JTextField vorname;
	private JTextField nummer;
	private JTextField mail;
	private JTextField beschreibung;
	private JTextField unternehmenname;
	private JTextField firmenanschrift;
	private JTextField url;
	private JTextField firmenbetreuer;
	private JTextField abteilung;
	private JTextField telefon;
	private JTextField mailunternehmen;
	private JTextField beginn;
	private JTextField themenbereich;
	private JTextField ende;
	private JPasswordField kennwort;

	public RegistrierungGUI() throws HeadlessException {
		setTitle("FELD");
		final JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 255));

		final JScrollPane scroll = new JScrollPane(panel);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel nachnamefix = new JLabel("Nachname");
		nachnamefix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel vornamefix = new JLabel("Vorname");
		vornamefix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel matrikelnummerfix = new JLabel("Matrikelnummer");
		matrikelnummerfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel mailfix = new JLabel("E-Mail");
		mailfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel indexmail = new JLabel("@hft-stuttgart.de");
		indexmail.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel unternehmen = new JLabel("Daten zum Unternehmen:");

		JLabel persoenlich = new JLabel("Persönliche Daten:");

		nachname = new JTextField();
		nachname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		nachname.setColumns(9);

		vorname = new JTextField();
		vorname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		vorname.setColumns(10);

		nummer = new JTextField();
		nummer.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		nummer.setColumns(10);

		mail = new JTextField();
		mail.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		mail.setColumns(10);

		JLabel unternehmenfix = new JLabel("Unternehmen");
		unternehmenfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel firmenanschriftfix = new JLabel("Firmenanschrift");
		firmenanschriftfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel urlfix = new JLabel("URL (des Unternehmens)");
		urlfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel firmenbetreuerfix = new JLabel("Firmenbetreuer/in");
		firmenbetreuerfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel abteilungfix = new JLabel("Abteilung");
		abteilungfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel telefonfix = new JLabel("Telefon");
		telefonfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel emailfix = new JLabel("E-Mail");
		emailfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel zeitraumfix = new JLabel("Zeitraum");
		zeitraumfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel themenbereichfix = new JLabel("Themenbereich");
		themenbereichfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel taetigkeitfix = new JLabel("Kurzbeschreibung der geplanten Tätigkeit");
		taetigkeitfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		beschreibung = new JTextField();
		beschreibung.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		beschreibung.setColumns(20);

		JButton button = new JButton("Registrieren");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatenabrufStudent db = new DatenabrufStudent();
				String email = mail.getText() + "@hft-stuttgart.de";
				db.einlesen(nummer.getText(), nachname.getText(), vorname.getText(), email, mail.getText(),
						kennwort.getText(), unternehmenname.getText(), firmenanschrift.getText(), url.getText(),
						mailunternehmen.getText(), firmenbetreuer.getText(), telefon.getText(), abteilung.getText(),
						beginn.getText(), ende.getText(), themenbereich.getText(), beschreibung.getText());
				AnmeldungGUI anmeldung = new AnmeldungGUI();
				anmeldung.main(null);
				dispose();
			}
		});

		JLabel kennwortfix = new JLabel("Kennwort");
		kennwortfix.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		unternehmenname = new JTextField();
		unternehmenname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		unternehmenname.setColumns(10);

		firmenanschrift = new JTextField();
		firmenanschrift.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		firmenanschrift.setColumns(10);

		url = new JTextField();
		url.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		url.setColumns(10);

		firmenbetreuer = new JTextField();
		firmenbetreuer.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		firmenbetreuer.setColumns(10);

		abteilung = new JTextField();
		abteilung.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		abteilung.setColumns(10);

		telefon = new JTextField();
		telefon.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		telefon.setColumns(10);

		mailunternehmen = new JTextField();
		mailunternehmen.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		mailunternehmen.setColumns(10);

		beginn = new JTextField();
		beginn.setText("JJJJ-MM-TT");
		beginn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		beginn.setColumns(10);

		themenbereich = new JTextField();
		themenbereich.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		themenbereich.setColumns(10);

		ende = new JTextField();
		ende.setText("JJJJ-MM-TT");
		ende.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		ende.setColumns(10);

		JLabel lblNewLabel = new JLabel("-");

		kennwort = new JPasswordField();
		kennwort.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(matrikelnummerfix).addComponent(persoenlich)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(nachnamefix).addComponent(mailfix)
																.addComponent(vornamefix).addComponent(kennwortfix))
														.addGap(54)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																.addComponent(kennwort).addComponent(vorname)
																.addComponent(nummer).addComponent(mail)
																.addComponent(nachname, GroupLayout.DEFAULT_SIZE, 224,
																		Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(indexmail)))
										.addContainerGap(203, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
										.addComponent(button).addGap(203))
								.addGroup(gl_panel.createSequentialGroup().addComponent(beschreibung, 422, 422, 422)
										.addGap(203))
								.addGroup(gl_panel.createSequentialGroup().addComponent(taetigkeitfix)
										.addContainerGap(419, Short.MAX_VALUE))
								.addGroup(
										gl_panel.createSequentialGroup().addComponent(unternehmen).addContainerGap(465,
												Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup().addComponent(themenbereichfix)
												.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
												.addComponent(themenbereich, GroupLayout.PREFERRED_SIZE, 224,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup().addComponent(telefonfix)
												.addPreferredGap(ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
												.addComponent(telefon, GroupLayout.PREFERRED_SIZE, 224,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup().addComponent(abteilungfix)
												.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
												.addComponent(abteilung, GroupLayout.PREFERRED_SIZE, 224,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(unternehmenfix).addComponent(firmenanschriftfix))
												.addGap(66)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addComponent(firmenanschrift, GroupLayout.PREFERRED_SIZE, 224,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(unternehmenname, GroupLayout.PREFERRED_SIZE, 224,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(urlfix).addComponent(firmenbetreuerfix))
												.addGap(24)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addComponent(firmenbetreuer, GroupLayout.PREFERRED_SIZE, 224,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(url, GroupLayout.PREFERRED_SIZE, 224,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(emailfix).addComponent(zeitraumfix))
												.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_panel.createSequentialGroup().addComponent(beginn)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(lblNewLabel)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(ende, GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE))
														.addComponent(mailunternehmen, Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE, 224,
																GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(259, Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(persoenlich)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(nachnamefix).addComponent(
						nachname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(vornamefix).addComponent(
						vorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(matrikelnummerfix).addComponent(
						nummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(mailfix).addComponent(indexmail))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(kennwortfix).addComponent(
						kennwort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(29).addComponent(unternehmen).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(unternehmenfix)
						.addComponent(unternehmenname, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(firmenanschriftfix)
						.addComponent(firmenanschrift, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(urlfix).addComponent(url,
						GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(firmenbetreuerfix)
						.addComponent(firmenbetreuer, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(abteilungfix)
						.addComponent(abteilung, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(telefonfix)
						.addComponent(telefon, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(emailfix)
						.addComponent(mailunternehmen, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(zeitraumfix)
						.addComponent(ende, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(beginn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(themenbereichfix)
						.addComponent(themenbereich, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(taetigkeitfix).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(beschreibung, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(button).addGap(11)));
		panel.setLayout(gl_panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel registrierung = new JLabel("Registrierung");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
						.addComponent(registrierung, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(18, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(registrierung).addGap(18)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(545, Short.MAX_VALUE)));

		getContentPane().setLayout(groupLayout);
		setSize(500, 500);
		setVisible(true);
	}

	public static void main(final String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new RegistrierungGUI().setVisible(true);
			}
		});
	}
}