package objekte;

/**
 * jegliche Informationen über das einem Student zugeordnete Unternehmen
 */
public class Unternehmen {
	private String name;
	private String anschrift;
	private String url;
	private String email;
	private String betreuer;
	private String telefon;
	private String bereich;
	private String thema;
	private String beschreibung;
	private String anmeldenamedesstudenten;

	// verschiedene Konstruktoren je nach Bedarf
	public Unternehmen(String name) {
		this.name = name;
	}

	public Unternehmen(String name, String anschrift, String url, String email, String betreuer, String telefon,
			String bereich, String beginn, String ende, String thema, String beschreibung,
			String anmeldesnamedesstudenten) {
		super();
		this.name = name;
		this.anschrift = anschrift;
		this.url = url;
		this.email = email;
		this.betreuer = betreuer;
		this.telefon = telefon;
		this.bereich = bereich;
		this.thema = thema;
		this.beschreibung = beschreibung;
		this.anmeldenamedesstudenten = anmeldesnamedesstudenten;
	}

	public String getName() {
		return name;
	}

	public String getAnschrift() {
		return anschrift;
	}

	public String getUrl() {
		return url;
	}

	public String getEmail() {
		return email;
	}

	public String getBetreuer() {
		return betreuer;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getBereich() {
		return bereich;
	}

	public String getThema() {
		return thema;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public String getanmeldesnamedesstudenten() {
		return anmeldenamedesstudenten;
	}

	@Override
	public String toString() {
		return "Unternehmen [name=" + name + "]";
	}

}
