package projekt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DatenabrufStudent {

		
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
			ResultSet rs = anweisung.executeQuery("SELECT studenten.Anmeldename, studenten.Nachname, studenten.Vorname, studenten.Matrikelnummer, studenten.E_Mail, studenten.Name_Unternehmen, studenten.Bericht, studenten.Tätigkeitsnachweis, studenten.BPS_Vortrag, professoren.Professoren_ID, professoren.Nachname, professoren.Vorname FROM studenten, professoren WHERE studenten.Professoren_ID=professoren.Professoren_ID");

			while (rs.next()) {
				ausgabe.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), new Professor(Integer.parseInt(rs.getString(10)), rs.getString(11), rs.getString(12))));
			}
			
			
			conn.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ausgabe;

	}
	

}
