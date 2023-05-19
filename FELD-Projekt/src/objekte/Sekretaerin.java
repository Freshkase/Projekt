package objekte;

/**
 * Kind-Klasse
 */
public class Sekretaerin extends Person {
	private int id;

	// Konstruktor
	public Sekretaerin(int id, String nachname, String vorname, String email, String kennwort, String anmeldename) {
		super(nachname, vorname, email, kennwort, anmeldename);
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
