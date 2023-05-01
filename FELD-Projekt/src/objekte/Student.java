package objekte;

public class Student extends Person {
	//Eigenschaften
	private int matrikelnummer;
	private String unternehmen;
	private String bericht;
	private String tätigkeitsnachweis;
	private String vortrag;
	private String beginn;
	private String ende;
	private Professor prof;
	
	public Student(String email) {
		super(email);
	}

	public Student(String anmeldename, String nachname, String vorname, int matrikelnummer, String email, String unternehmen, String bericht, String tätigkeitsnachweis, String vortrag, Professor prof, String kennwort, String beginn, String ende) {
		super(nachname, vorname, email, kennwort, anmeldename);
		this.unternehmen = unternehmen;
		this.matrikelnummer = matrikelnummer;
		this.bericht = bericht;
		this.tätigkeitsnachweis = tätigkeitsnachweis;
		this.vortrag = vortrag;
		this.prof = prof;
		
		String beginnRichtig[] = beginn.split("-");
		this.beginn = beginnRichtig[2] + "." + beginnRichtig[1] + "." + beginnRichtig[0];
		
		String endeRichtig[] = ende.split("-");
		this.ende = endeRichtig[2] + "." + endeRichtig[1] + "." + endeRichtig[0];
	}

	public int getMatrikelnr() {
		return matrikelnummer;
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
