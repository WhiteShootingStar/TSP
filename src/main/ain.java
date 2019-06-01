package main;

import java.util.Iterator;
import java.util.List;

import algorithmsImplementation.Solver;
import point.Point;
import utils.Utilities;

public class ain {

	public static void main(String[] args) {
		List<Point> list = Utilities.generateGraph("tsp_data.txt");
		System.out.println("init liost");
		Point.printMatrix(list);
		
		Solver solver = new Solver(list);
		
		System.out.println(solver.getTotalValue(list));
		solver.solveWithAnnealing();
	}

}
