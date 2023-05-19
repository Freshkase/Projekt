package sortierung;

import java.util.Comparator;

import objekte.Student;

//Sortierung umgedreht-alphabetisch nach Besuchsbericht (für "PPANachGUI")
//Generell: Comparatoren werden benötigt aufgrund der Darstellung der Buttons innerhalb der Tabelle
public class MyComparator3 implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o2.getBesuchsbericht().compareTo(o1.getBesuchsbericht());
	}

}
