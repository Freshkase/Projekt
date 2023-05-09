package objekte;

public class Person {
	private String nachname;
	private String vorname;
	private String email;
	private String kennwort;
	private String anmeldename;

	public Person(String nachname, String vorname, String email, String kennwort, String anmeldename) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
		this.kennwort = kennwort;
		this.anmeldename = anmeldename;
	}

	public Person(String nachname, String vorname, String anmeldename) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.anmeldename = anmeldename;
	}

	public Person(String email) {
		this.email = email;
	}

	public String getVorname() {
		return vorname;
	}

	public String getEmail() {
		return email;
	}

	public String getAnmeldename() {
		return anmeldename;
	}

	public String getKennwort() {
		return kennwort;
	}

	public String getNachname() {
		return nachname;
	}
}
