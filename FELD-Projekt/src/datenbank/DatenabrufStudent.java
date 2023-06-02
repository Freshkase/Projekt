package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import objekte.Professor;
import objekte.Student;
import objekte.Unternehmen;
import sortierung.MyComparator2;

public class DatenabrufStudent {

	/**
	 * Methode, um Informationen über aus der Tabelle "Student" aus der Datenbank
	 * auszulesen (jegliche Informationen über den Student + Informationen über den
	 * zugeteilten Professor mittels Fremdschlüssel)
	 * 
	 * @return ArrayList vom Typ Student
	 */
	public ArrayList<Student> ausgeben() {

		Connection conn = null;

		String url = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		ArrayList<Student> ausgabe = new ArrayList<>();
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url + dbName, username, password);

			Statement anweisung = conn.createStatement();
			ResultSet rs = anweisung.executeQuery(
					"SELECT studenten.Anmeldename, studenten.Nachname, studenten.Vorname, studenten.Matrikelnummer, studenten.E_Mail, studenten.Name_Unternehmen, studenten.Bericht, studenten.Tätigkeitsnachweis, studenten.BPS_Vortrag, professoren.Professoren_ID, professoren.Nachname, professoren.Vorname, studenten.kennwort, studenten.Beginn_BPS, studenten.Ende_BPS, studenten.Besuchsbericht, professoren.Anmeldename FROM studenten, professoren WHERE studenten.Professoren_ID=professoren.Professoren_ID");

			while (rs.next()) {
				ausgabe.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3),
						Integer.parseInt(rs.getString(4)), rs.getString(5), new Unternehmen(rs.getString(6)),
						rs.getString(7), rs.getString(8), rs.getString(9),
						new Professor(Integer.parseInt(rs.getString(10)), rs.getString(11), rs.getString(12),
								rs.getString(17)),
						rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16)));
			}

			Collections.sort(ausgabe, new MyComparator2());
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ausgabe;

	}

	/**
	 * Methode, um Informationen über den Studenten in die Datenbank (Tabelle
	 * "Student") einzulesen dies findet bei der Registrierung statt, da sich der
	 * Student selbstständig mit seinem BPS registriert
	 * 
	 * @param mtrn
	 * @param nachname
	 * @param vorname
	 * @param mail
	 * @param anmeldename
	 * @param kennwort
	 * @param unternehmen
	 * @param firmenanschrift
	 * @param url
	 * @param emailu          (Email des Unternehmens)
	 * @param betreuer
	 * @param telefon
	 * @param abteilung
	 * @param beginn
	 * @param ende
	 * @param themenbereich
	 * @param stelle
	 * @return
	 */
	public ArrayList<Student> einlesen(String mtrn, String nachname, String vorname, String mail, String anmeldename,
			String kennwort, String unternehmen, String firmenanschrift, String url, String emailu, String betreuer,
			String telefon, String abteilung, String beginn, String ende, String themenbereich, String stelle) {

		Connection conn = null;

		String url2 = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		ArrayList<Student> ausgabe = new ArrayList<>();
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url2 + dbName, username, password);

			Statement anweisung = conn.createStatement();
			String uebergabe = "INSERT INTO STUDENTEN (Matrikelnummer, Nachname, Vorname, E_Mail, Anmeldename, Kennwort, Name_Unternehmen, Firmenanschrift, URL_Unternehmen, E_Mail_Unternehmen, Firmenbetreuer, Telefonnummer_Unternehmen, Abteilung, Beginn_BPS, Ende_BPS, Themenbereich_BPS, Stellenbeschreibung, Professoren_ID, Bericht, Tätigkeitsnachweis, BPS_Vortrag, Besuchsbericht) VALUES ("
					+ mtrn + ", '" + nachname + "', '" + vorname + "', '" + mail + "', '" + anmeldename + "', '"
					+ kennwort + "', '" + unternehmen + "', '" + firmenanschrift + "', '" + url + "', '" + emailu
					+ "', '" + betreuer + "', '" + telefon + "', '" + abteilung + "', '" + beginn + "', '" + ende
					+ "', '" + themenbereich + "', '" + stelle + "', '0', 'nein', 'nein', 'nein', ' ')";
			anweisung.executeUpdate(uebergabe);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ausgabe;

	}

	/**
	 * Methode, um einem Studenten einen Professor zuzuordnen dies geschieht während
	 * des Zuteilungsprozesses entweder durch den Professor selbst oder durch das
	 * PPA
	 * 
	 * @param id     (ID des Professors)
	 * @param nummer (Matrikelnummer)
	 */
	public void aendern(int id, int nummer) {

		Connection conn = null;

		String url2 = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url2 + dbName, username, password);

			Statement anweisung = conn.createStatement();
			String uebergabe = "UPDATE STUDENTEN SET Professoren_ID = " + id + " WHERE Matrikelnummer = " + nummer;
			anweisung.executeUpdate(uebergabe);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Methode, um Informationen über das Unternehmen und das BPS aus der Tabelle
	 * "Student" aus der Datenbank auszulesen
	 * 
	 * @return ArrayList vom Typ Unternehmen
	 */
	public ArrayList<Unternehmen> ausUnternehmen() {
		Connection conn = null;

		String url = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		ArrayList<Unternehmen> ausgaben = new ArrayList<>();
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url + dbName, username, password);

			Statement anweisung = conn.createStatement();
			ResultSet rs = anweisung.executeQuery(
					"SELECT studenten.Name_Unternehmen, studenten.Firmenanschrift, studenten.URL_Unternehmen, studenten.E_Mail_Unternehmen, studenten.Firmenbetreuer, studenten.Telefonnummer_Unternehmen, studenten.Abteilung, studenten.Beginn_BPS, studenten.Ende_BPS, studenten.Themenbereich_BPS, studenten.Stellenbeschreibung, studenten.Anmeldename FROM studenten");

			while (rs.next()) {
				ausgaben.add(new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12)));
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ausgaben;
	}

	/**
	 * Methode, um den Status des Berichts, den der Student über sein BPS schreiben
	 * muss, in der Datenbank zu ändern diese Funktion (den Status zu ändern)
	 * besitzt der Professor
	 * 
	 * @param nummer (Matrikelnummer)
	 */
	public void einlesenbericht(int nummer) {

		Connection conn = null;

		String url2 = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url2 + dbName, username, password);

			Statement anweisung = conn.createStatement();
			String uebergabe = "UPDATE STUDENTEN SET Bericht = 'ja'  WHERE Matrikelnummer = " + nummer;
			anweisung.executeUpdate(uebergabe);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Methode, um den Status des Vortrags, den der Student halten muss, in der
	 * Datenbank zu ändern diese Funktion (den Status zu ändern) besitzt das PPA
	 * 
	 * @param nummer (Matrikelnummer)
	 */
	public void aendernVortrag(int nummer) {

		Connection conn = null;

		String url2 = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url2 + dbName, username, password);

			Statement anweisung = conn.createStatement();
			String uebergabe = "UPDATE STUDENTEN SET BPS_Vortrag = 'ja' WHERE Matrikelnummer = " + nummer;
			anweisung.executeUpdate(uebergabe);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Methode, um den Status der Abgabe des Tätigkeitsnachweises in der Datenbank
	 * zu ändern diese Funktion (den Status zu ändern) besitzt das
	 * Studierendensekretariat
	 * 
	 * @param nummer (Matrikelnummer)
	 */
	public void einlesentätigkeitsnachweis(int nummer) {

		Connection conn = null;

		String url2 = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url2 + dbName, username, password);

			Statement anweisung = conn.createStatement();
			String uebergabe = "UPDATE STUDENTEN SET Tätigkeitsnachweis = 'ja'  WHERE Matrikelnummer = " + nummer;
			anweisung.executeUpdate(uebergabe);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
