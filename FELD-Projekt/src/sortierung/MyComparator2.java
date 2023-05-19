package sortierung;

import java.util.Comparator;

import objekte.Student;

/**
 * Sortierung aufsteigend nach ProfessorenID
 * Generell: Comparatoren werden benötigt aufgrund der Darstellung der Buttons innerhalb der Tabelle
 */

public class MyComparator2 implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.getProf().getId() - o2.getProf().getId();
	}

}
