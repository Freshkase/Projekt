package objekte;

import java.util.Objects;

/**
 * Kind-Klasse
 */
public class Student extends Person {
	private int matrikelnummer;
	private Unternehmen unternehmen;
	private String bericht;
	private String tätigkeitsnachweis;
	private String vortrag;
	private String beginn;
	private String ende;
	private String besuchsbericht;
	private Professor prof;

	// verschiedene Konstruktoren je nach Bedarf
	public Student(String email) {
		super(email);
	}

	public Student(String anmeldename, String nachname, String vorname, int matrikelnummer, String email,
			Unternehmen unternehmen, String bericht, String tätigkeitsnachweis, String vortrag, Professor prof,
			String kennwort, String beginn, String ende, String besuchsbericht) {
		super(nachname, vorname, email, kennwort, anmeldename);
		this.unternehmen = unternehmen;
		this.matrikelnummer = matrikelnummer;
		this.bericht = bericht;
		this.tätigkeitsnachweis = tätigkeitsnachweis;
		this.vortrag = vortrag;
		this.prof = prof;
		this.besuchsbericht = besuchsbericht;

		// Formatierung vom Datum Beginn BPS und Ende BPS (in Datenbank amerikanisch
		// formatiert)
		String beginnRichtig[] = beginn.split("-");
		this.beginn = beginnRichtig[2] + "." + beginnRichtig[1] + "." + beginnRichtig[0];

		String endeRichtig[] = ende.split("-");
		this.ende = endeRichtig[2] + "." + endeRichtig[1] + "." + endeRichtig[0];
	}

	public int getMatrikelnr() {
		return matrikelnummer;
	}

	public Unternehmen getUnternehmen() {
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

	public String getBesuchsbericht() {
		return besuchsbericht;
	}

	// Gleichheit des Objekts Student anhand Matrikelnummer
	@Override
	public int hashCode() {
		return Objects.hash(matrikelnummer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return matrikelnummer == other.matrikelnummer;
	}

}
