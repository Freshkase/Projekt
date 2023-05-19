package sortierung;

import java.util.Comparator;

import objekte.Student;

//Sortierung alphabetisch nach Besuchsbericht (für "ProfessorenNachGUI")
//Generell: Comparatoren werden benötigt aufgrund der Darstellung der Buttons innerhalb der Tabelle
public class MyComparator1 implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.getBesuchsbericht().compareTo(o2.getBesuchsbericht());
	}

}
