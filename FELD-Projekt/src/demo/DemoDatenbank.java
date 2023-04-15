package demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DemoDatenbank {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		String url="jdbc:mysql://3.69.96.96:3306/";
		String dbName="db1";
		String driver="com.mysql.cj.jdbc.Driver";
		String username="db1";
		String password="!db1.wip23?";
		
		
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url+dbName,username,password);
			System.out.println("Connected to the database");
			
			Statement anweisung = conn.createStatement();
			ResultSet rs = anweisung.executeQuery("SELECT Professoren_ID, Nachname, Vorname, E_Mail, Anmeldename, Kennwort FROM professoren");
			
			System.out.printf("%-15s %-15s %-10s %-40s %-20s %-10s", "Professoren-ID", "Nachname", "Vorname", "E-Mail", "Anmeldename", "Kennwort");
			System.out.println();
			
			while (rs.next()) {
				if(rs.getInt(1) != 0) {
				System.out.printf("%-15s %-15s %-10s %-40s %-20s %-10s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
				System.out.println();
				}
			}
			
			System.out.println();
			ResultSet rs2 = anweisung.executeQuery("Select studenten.Nachname, studenten.Vorname, studenten.E_Mail, studenten.Name_Unternehmen, professoren.Nachname, professoren.Vorname from professoren,studenten where studenten.Professoren_ID=professoren.Professoren_ID");
			
			System.out.printf("%-28s %-30s %-15s %-15s", "Student", "E-Mail", "Unternehmen", "Betreuer");
			System.out.println();
			
			while (rs2.next()) {
				if (rs2.getString(5)==null) {
				System.out.printf("%-28s %-30s %-15s %-15s", rs2.getString(1) + ", " + rs2.getString(2), rs2.getString(3), rs2.getString(4), "auswaehlen");
				} else {
					System.out.printf("%-28s %-30s %-15s %-15s", rs2.getString(1) + ", " + rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5) + ", " + rs2.getString(6));	
				}
				System.out.println();
			}
			
			conn.close();
			System.out.println("Disconnected from database");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
