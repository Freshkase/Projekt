package sortierung;

import java.util.Comparator;

import objekte.Student;

public class MyComparator3 implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o2.getBesuchsbericht().compareTo(o1.getBesuchsbericht());
	}

}
