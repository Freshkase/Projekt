package objekte;

public class Professor {
	private int id;
	private String nachname;
	private String vorname;
	private String email;
	private String kennwort;
	private String anmeldename;
	
	public Professor(int id, String nachname, String vorname) {
		super();
		this.id = id;
		this.nachname = nachname;
		this.vorname = vorname;
	}
	
	public Professor(int id, String nachname, String vorname, String email, String kennwort, String anmeldename) {
		super();
		this.id = id;
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
		this.kennwort = kennwort;
		this.anmeldename = anmeldename;
	}
	
	
	public String getNachname() {
		return nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getKennwort() {
		return kennwort;
	}

	public String getAnmeldename() {
		return anmeldename;
	}
	
	
	
	
}
