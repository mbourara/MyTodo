package model;

import jdbc.Todo;

@SuppressWarnings("rawtypes")
public class NameComparator implements java.util.Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		String n1 = ((Todo) o1).getTitre();
		String n2 = ((Todo) o2).getTitre();
		return n1.compareTo(n2); 
	}

}
