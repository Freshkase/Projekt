package objekte;

public class Student {
	//Eigenschaften
	private int matrikelnummer;
	private String nachname;
	private String vorname;
	private String email;
	private String anmeldename;
	private String kennwort;
	private String unternehmen;
	private String bericht;
	private String tätigkeitsnachweis;
	private String vortrag;
	private String beginn;
	private String ende;
	private Professor prof;
	
	
	public Student(String anmeldename, String nachname, String vorname, int matrikelnummer, String email, String unternehmen, String bericht, String tätigkeitsnachweis, String vortrag, Professor prof, String kennwort, String beginn, String ende) {
		super();
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
		this.anmeldename = anmeldename;
		this.unternehmen = unternehmen;
		this.matrikelnummer = matrikelnummer;
		this.bericht = bericht;
		this.tätigkeitsnachweis = tätigkeitsnachweis;
		this.vortrag = vortrag;
		this.prof = prof;
		this.kennwort = kennwort;
		
		String beginnRichtig[] = beginn.split("-");
		this.beginn = beginnRichtig[2] + "." + beginnRichtig[1] + "." + beginnRichtig[0];
		
		String endeRichtig[] = ende.split("-");
		this.ende = endeRichtig[2] + "." + endeRichtig[1] + "." + endeRichtig[0];
	}

	@Override
	public String toString() {
		return matrikelnummer + " " + nachname + " " + vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public int getMatrikelnr() {
		return matrikelnummer;
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

	public String getUnternehmen() {
		return unternehmen;
	}

	public String getBericht() {
		return bericht;
	}

	public String getTätigkeitsnachweis() {
		return tätigkeitsnachweis;
	}

	public String getVortrag() {
		return vortrag;
	}

	public Professor getProf() {
		return prof;
	}

	public String getBeginn() {
		return beginn;
	}

	public String getEnde() {
		return ende;
	}


	
	
	


	
	
	
	
	
	
	
}
