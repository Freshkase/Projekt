package mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * diese Klasse Mail2 erstellt und versendet eine E-Mail an das Prüfungsamt,
 * wenn das BPS abgeschlossen ist (ausgelöst durch das PPA)
 */
public class Mail2 {

	private static Message prepareMessage(Session session, String myAccount, String empfaenger) throws Exception {
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(myAccount));

		// Empfänger der Mail festlegen
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
		message.setSubject("FELD");

		// Multipart-Message ("Wrapper") erstellen
		Multipart multipart = new MimeMultipart();

		// Body-Part setzen:
		BodyPart messageBodyPart = new MimeBodyPart();

		// Textteil des Body-Parts
		messageBodyPart.setText(
				"Sehr geehrtes Prüfungsamt, \n\nhiermit übersenden wir Ihnen die vollständige Liste aller Praxissemesterabsolventen zur Überprüfung und Eintragung des erfolgreichen Bestehens.\n\nMit freundlichen Grüßen\ndas FELD-Team\n\nDiese E-Mail wurde autogeneriert. ");

		// Body-Part dem Multipart-Wrapper hinzufügen
		multipart.addBodyPart(messageBodyPart);

		// Anhang einfügen
		MimeBodyPart attachmentPart = new MimeBodyPart();
		attachmentPart.attachFile("Ergebnisse_BPS.csv");
		multipart.addBodyPart(attachmentPart);

		// Message fertigstellen, indem sie mit dem Multipart-Content ausgestattet wird
		message.setContent(multipart);

		return message;
	}

	public void send() {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.web.de");
		properties.put("mail.smtp.port", "587");

		String myAccount = "feld-projekt@web.de";
		String myPassword = "feld#0922";

		// hier wird die Mail-Adresse des Prüfungsamts eingetragen
		String empfaenger = "22pole1bwi@hft-stuttgart.de";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccount, myPassword);
			}
		});

		// Message-Objekt erzeugen und senden!
		try {
			Message message = prepareMessage(session, myAccount, empfaenger);
			Transport.send(message); // E-Mail senden!
			System.out.println("E-Mail erfolgreich versendet!");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}