package sortierung;

import java.util.Comparator;

import objekte.Student;

public class MyComparator5 implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.getMatrikelnr()-o2.getMatrikelnr();
	}
}
