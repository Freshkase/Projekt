package datenbank;

import java.util.Comparator;

import objekte.Student;

public class MyComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.getBesuchsbericht().compareTo(o2.getBesuchsbericht());
	}

}
