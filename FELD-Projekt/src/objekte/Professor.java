package objekte;

public class Professor {
	private int id;
	private String nachname;
	private String vorname;
	
	public Professor(int id, String nachname, String vorname) {
		super();
		this.id = id;
		this.nachname = nachname;
		this.vorname = vorname;
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
	
	
	
	
}
