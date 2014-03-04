package model;

import jdbc.Todo;

@SuppressWarnings("rawtypes")
public class PriorityComparator implements java.util.Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		int p1 = ((Todo) o1).getDegreImportance();
		int p2 = ((Todo) o2).getDegreImportance();
		if(p1>p2) return -1;
		else if(p1==p2) return 0;
		else return 1;		
	}
}
