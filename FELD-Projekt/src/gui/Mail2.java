package gui;

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

public class Mail2 {

	private static Message prepareMessage(Session session, String myAccount, String empfaenger)
			throws Exception {
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(myAccount));

		
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
		message.setSubject("FELD");
		// Multipart-Message ("Wrapper") erstellen
		Multipart multipart = new MimeMultipart();
		// Body-Part setzen:
		BodyPart messageBodyPart = new MimeBodyPart();
		// Textteil des Body-Parts
		messageBodyPart.setText("Sehr geehrtes Prüfungsamt, \n\nhiermit übersenden wir Ihnen die vollstände Liste aller Praxissemesterabsolventen \n\nzur Überprüfung und Eintragung des erfolgreichen Bestehends.\\n\\nMit freundlichen Grüßen\\ndas FELD-Team\\n\\nDiese E-Mail wurde autogeneriert. ");		// Body-Part dem Multipart-Wrapper hinzufügen
		multipart.addBodyPart(messageBodyPart);
		// Message fertigstellen, indem sie mit dem Multipart-Content ausgestattet wird
		MimeBodyPart attachmentPart = new MimeBodyPart();
		attachmentPart.attachFile("Mails.csv");
		multipart.addBodyPart(attachmentPart);
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
		String empfaenger = "lea.posselt@web.de";
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