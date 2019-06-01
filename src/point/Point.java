package point;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Point implements Comparable<Point> {
	

	public String name;
	public Point next;
	public Map<Point,Integer> travelMap;
	
	public Point(String name) {
		this.name=name;
		travelMap = new TreeMap<>();
		next=null;
	}

	
	


	public Point travelTo(Point destination) {
		next=destination;
		return destination;
	}
	
	@Override
	public int compareTo(Point o) {
		
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return name;
	}
	
	public static void printMatrix(List<Point> list) {
		System.out.print("     ");
		for (Point point : list) {
			System.out.print(point + "     ");
		}
		System.out.println();
		for (Point point : list) {
			System.out.println(point + "   " + point.travelMap);
		}
		
	}
	
}
