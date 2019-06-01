package algorithmsImplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import point.Point;

public class Solver {
	private List<Point> randomSolution;
	private List<Point> startingList;
	private Random random;

	public Solver(List<Point> list) {
		startingList = list;
		randomSolution = list;
		random = new Random();
	}

	public void solveWithHC() {
		generateRandomSolution();
		Point.printMatrix(randomSolution);
		int randVal = getTotalValue(randomSolution);
		System.out.println(randVal);
		List<Point> newSolution = getBestneighbourSolution(randomSolution);
		Point.printMatrix(newSolution);
		int solVal = getTotalValue(newSolution);
		System.out.println(solVal);
		if (solVal > randVal) {
			System.out.println("HC finished");
			Point.printMatrix(newSolution);
			System.out.println(getTotalValue(newSolution));
		} else {
			while (getTotalValue(newSolution) < getTotalValue(randomSolution)) {
				Point.printMatrix(newSolution);
				System.out.println(getTotalValue(newSolution));
				randomSolution = newSolution;
				newSolution = getBestneighbourSolution(randomSolution);
			}
		}

	}

	public void solveWithAnnealing() {

		int temperature = 400;
		int iterationsWithNoEffect = 0;
		int limit = 500;

		generateRandomSolution();
		Point.printMatrix(randomSolution);
		while (iterationsWithNoEffect < limit) {
			List<Point> candidate = getNeigbourSolution(randomSolution);
			// List<Point> candidate = getBestneighbourSolution(randomSolution);
			if (getTotalValue(candidate) < getTotalValue(randomSolution)) {
				randomSolution = candidate;
				iterationsWithNoEffect = 0;
			} else {
				int candidateTotalValue = getTotalValue(candidate);
				int randSolTotalValue = getTotalValue(randomSolution);
				double probBarier = calculateProbability(randSolTotalValue, candidateTotalValue, temperature);
				// System.out.println(probBarier);
				int intoProbability = getWithProbability(probBarier);
				if (intoProbability == 0) {
					randomSolution = candidate;
					// iterationsWithNoEffect=0;
				} else {
					iterationsWithNoEffect++;

				}
				System.out.println(iterationsWithNoEffect);
			}

			temperature -= temperature / 8;
			Point.printMatrix(randomSolution);
			System.out.println(getTotalValue(randomSolution));
		}
		

	}

	private void generateRandomSolution() {
		Collections.shuffle(randomSolution);
	}

	public int getTotalValue(List<Point> solution) {
		int totalDistance = 0;
		for (int i = 0; i < solution.size() - 1; i++) {
			totalDistance += solution.get(i).travelMap.get(solution.get(i + 1));

		}
		totalDistance += solution.get(solution.size() - 1).travelMap.get(solution.get(0));
		return totalDistance;
	}

	private List<Point> getBestneighbourSolution(List<Point> neigbouringSolution) {
		List<Point> bestSolution = new ArrayList<>(neigbouringSolution);
		List<Point> currentSolution = new ArrayList<>(neigbouringSolution);
		for (int i = 0; i < neigbouringSolution.size() - 1; i++) {
			for (int j = 1; j < neigbouringSolution.size(); j++) {
				Collections.swap(currentSolution, i, j);
				if (getTotalValue(currentSolution) < getTotalValue(bestSolution)) {
					bestSolution = currentSolution;

				}
				currentSolution = new ArrayList<>(neigbouringSolution);

			}
		}
		return bestSolution;
	}

	private List<Point> getNeigbourSolution(List<Point> neigbouringSolution) {
		List<Point> list = new ArrayList<>(neigbouringSolution);
		Collections.swap(list, random.nextInt(list.size()), random.nextInt(list.size()));
		return list;
	}

	private double calculateProbability(int fCurr, int fCand, int temperature) {
		return Math.pow(Math.E, -(Math.abs(fCurr - fCand) / (double) temperature));
	}

	private int getWithProbability(double currentLimit) {
		double randomValue = Math.random();
		if (currentLimit > 1) {
			return 1;
		}
		if (randomValue <= currentLimit) {
			return 0;
		} else
			return 1;

	}
}
