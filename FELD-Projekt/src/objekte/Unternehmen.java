package objekte;

public class Unternehmen {
	private String name;
	private String anschrift;
	private String url;
	private String email;
	private String betreuer;
	private String telefon;
	private String bereich;
	private String beginn;
	private String ende;
	private String thema;
	private String beschreibung;
	
	public Unternehmen(String name, String anschrift, String url, String email, String betreuer, String telefon,
			String bereich, String beginn, String ende, String thema, String beschreibung) {
		super();
		this.name = name;
		this.anschrift = anschrift;
		this.url = url;
		this.email = email;
		this.betreuer = betreuer;
		this.telefon = telefon;
		this.bereich = bereich;
		this.beginn = beginn;
		this.ende = ende;
		this.thema = thema;
		this.beschreibung = beschreibung;
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

	public String getBeginn() {
		return beginn;
	}

	public String getEnde() {
		return ende;
	}

	public String getThema() {
		return thema;
	}

	public String getBeschreibung() {
		return beschreibung;
	}
	
	
}
