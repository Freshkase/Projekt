package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import objekte.Student;

public class DatenabrufStatus {
	
	/**
	 * Methode, um den Status der Zuteilung aus der Datenbank auszulesen (Während Zuteilung = 0, Nach Zuteilung = 1)
	 * je nach Status der Zuteilung werden auf unterschiedliche GUI's zugegriffen ("...WaehrendGUI" oder "...NachGUI")
	 * @return int
	 */
	public int ausgeben() {

		Connection conn = null;

		String url = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db1";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "db1";
		String password = "!db1.wip23?";

		int ausgabe = 0;
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url + dbName, username, password);

			Statement anweisung = conn.createStatement();
			ResultSet rs = anweisung.executeQuery("SELECT Zuteilung FROM status");

			while (rs.next()) {
				ausgabe = Integer.parseInt(rs.getString(1));
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ausgabe;

	}

	/**
	 * Methode, um den Status in der Tabelle "Zuteilung" in der Datenbank zu ändern
	 * diese Methode wird ausgeführt, wenn in "PPAWaehrendGUI" der "Zuteilung beenden" Button gedrückt wird
	 * @param wert (Status der Zuteilung 0 oder 1)
	 */
	public void aendern(int wert) {

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
			String uebergabe = "UPDATE STATUS SET Zuteilung = " + wert;
			anweisung.executeUpdate(uebergabe);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

