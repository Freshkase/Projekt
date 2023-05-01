package objekte;

public class Professor extends Person {
	private int id;
	
	public Professor(String nachname, String vorname, String email, String kennwort, String anmeldename) {
		super(nachname, vorname, email, kennwort, anmeldename);
		// TODO Auto-generated constructor stub
	}

	public Professor(int id, String nachname, String vorname) {
		super(nachname, vorname);
		this.id = id;
	}
	
	public Professor(int id, String nachname, String vorname, String email, String kennwort, String anmeldename) {
		super(nachname, vorname, email, kennwort, anmeldename);
		this.id = id;
	}

	public Professor(String email) {
		super(email);
	}

	public int getId() {
		return id;
	}
	
	
	
	
}
