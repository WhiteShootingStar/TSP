package utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import point.Point;

public class Utilities {

	public static List<Point> generateGraph(String filePath) {
		List<Point> list = new ArrayList<>();
		try {
			List<String> matrixRow = Files.readAllLines(Paths.get(filePath));
			for (char i = 'a'; i < 'a' + matrixRow.size(); i++) {
				Point point = new Point(i + "");
				list.add(point);
			}
			for (Point point : list) {
				int index = list.indexOf(point);
				String rowForThisPoint = matrixRow.get(index);
				int[] splitted = Arrays.stream(rowForThisPoint.split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
				for (int i = 0; i < splitted.length; i++) {
					point.travelMap.put(list.get(i), splitted[i]);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
