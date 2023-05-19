package objekte;

/**
 * Kind-Klasse
 */
public class Professor extends Person {
	private int id;

	// verschiedene Konstruktoren je nach Bedarf
	public Professor(String nachname, String vorname, String email, String kennwort, String anmeldename) {
		super(nachname, vorname, email, kennwort, anmeldename);
		// TODO Auto-generated constructor stub
	}

	public Professor(int id, String nachname, String vorname, String anmeldename) {
		super(nachname, vorname, anmeldename);
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
