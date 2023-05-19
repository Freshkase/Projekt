package sortierung;

import java.util.Comparator;

import objekte.Student;

//Sortierung umgedreht-alphabetisch nach Tätigkeitsnachweis
//Generell: Comparatoren werden benötigt aufgrund der Darstellung der Buttons innerhalb der Tabelle
public class MyComparator4 implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o2.getTätigkeitsnachweis().compareTo(o1.getTätigkeitsnachweis());
	}

}
