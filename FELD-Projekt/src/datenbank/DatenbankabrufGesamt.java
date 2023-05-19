package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import objekte.Professor;
import objekte.Student;
import objekte.Person;

public class DatenbankabrufGesamt {
	
	//Methode, um alle E-Mail Adressen aus den Tabellen (Studenten, Professoren, PPA, Studierendensekretariat)
	//aus der Datenbank auszulesen
	//diese Informationen werden für den Versand der autogenerierten E-Mail benötigt, wenn der Button "Zuteilung beenden" gedrückt wird
	public ArrayList<Person> ausgeben() {

		Connection conn = null;

		String url = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		ArrayList<Person> ausgabe = new ArrayList<>();
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url + dbName, username, password);

			Statement anweisung = conn.createStatement();
			ResultSet rs = anweisung.executeQuery("SELECT E_Mail FROM studenten");

			while (rs.next()) {
				ausgabe.add(new Student(rs.getString(1)));
			}

			ResultSet rs2 = anweisung.executeQuery("SELECT E_Mail FROM professoren");
			while (rs2.next()) {
				if (rs2.getString(1) != null) {
					ausgabe.add(new Professor(rs2.getString(1)));
				}
			}

			ResultSet rs3 = anweisung.executeQuery("SELECT E_Mail FROM ppa");
			while (rs3.next()) {
				ausgabe.add(new Professor(rs3.getString(1)));
			}

			ResultSet rs4 = anweisung.executeQuery("SELECT E_Mail FROM studierendensekretariat");
			while (rs4.next()) {
				ausgabe.add(new Professor(rs4.getString(1)));
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ausgabe;

	}
}
