package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import objekte.Professor;

public class DatenabrufProfessor {

	public ArrayList<Professor> ausgeben() {

		Connection conn = null;

		String url = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		ArrayList<Professor> ausgabe = new ArrayList<>();
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url + dbName, username, password);

			Statement anweisung = conn.createStatement();
			ResultSet rs = anweisung.executeQuery(
					"SELECT Professoren_ID, Nachname, Vorname, E_Mail, Kennwort, Anmeldename FROM professoren");

			while (rs.next()) {
				ausgabe.add(new Professor(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6)));
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ausgabe;

	}

	public void BesuchsberichtErstellen(String Besuchsbericht, int nummer) {

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
			String uebergabe = "UPDATE STUDENTEN SET Besuchsbericht = '" + Besuchsbericht + "' WHERE Matrikelnummer = "
					+ nummer;
			anweisung.executeUpdate(uebergabe);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
