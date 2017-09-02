package com.team2502.robot2017.trajectory.util;

import java.util.StringTokenizer;

import com.team2502.robot2017.trajectory.Trajectory;
import com.team2502.robot2017.trajectory.Path;


/**
 *
 * @author Jared341
 */
public class PathFileReader {

	public static double parseFormattedDouble(String string) {
		// Find the decimal point
		int decimal = string.indexOf('.');

		if (decimal == -1 || decimal + 1 >= string.length()) {
			// The string is not formatted correctly.
			return 0.0;
		}

		long whole_part = Long.parseLong(string.substring(0, decimal));
		long fractional_part = Long.parseLong(string.substring(decimal + 1,
				string.length()));

		double divisor = Math.pow(10, string.length() - decimal - 1);
		double sign = ((string.indexOf('-') == -1) ? 1.0 : -1.0);

		return (double)whole_part + (double)fractional_part*sign / divisor;
	}

	public Path deserialize(String serialized) {
		StringTokenizer tokenizer = new StringTokenizer(serialized, "\n");
		System.out.println("Parsing path string...");
		System.out.println("String has " + serialized.length() + " chars");
		System.out.println("Found " + tokenizer.countTokens() + " tokens");

		String name = tokenizer.nextToken();
		int num_elements = Integer.parseInt(tokenizer.nextToken());

		Trajectory left = new Trajectory(num_elements);
		for (int i = 0; i < num_elements; ++i) {
			Trajectory.Segment segment = new Trajectory.Segment();
			StringTokenizer line_tokenizer = new StringTokenizer(
					tokenizer.nextToken(), " ");

			segment.pos = parseFormattedDouble(line_tokenizer.nextToken());
			segment.vel = parseFormattedDouble(line_tokenizer.nextToken());
			segment.acc = parseFormattedDouble(line_tokenizer.nextToken());
			segment.jerk = parseFormattedDouble(line_tokenizer.nextToken());
			segment.heading = parseFormattedDouble(line_tokenizer.nextToken());
			segment.dt = parseFormattedDouble(line_tokenizer.nextToken());
			segment.x = parseFormattedDouble(line_tokenizer.nextToken());
			segment.y = parseFormattedDouble(line_tokenizer.nextToken());

			left.setSegment(i, segment);
		}
		Trajectory right = new Trajectory(num_elements);
		for (int i = 0; i < num_elements; ++i) {
			Trajectory.Segment segment = new Trajectory.Segment();
			StringTokenizer line_tokenizer = new StringTokenizer(
					tokenizer.nextToken(), " ");

			segment.pos = parseFormattedDouble(line_tokenizer.nextToken());
			segment.vel = parseFormattedDouble(line_tokenizer.nextToken());
			segment.acc = parseFormattedDouble(line_tokenizer.nextToken());
			segment.jerk = parseFormattedDouble(line_tokenizer.nextToken());
			segment.heading = parseFormattedDouble(line_tokenizer.nextToken());
			segment.dt = parseFormattedDouble(line_tokenizer.nextToken());
			segment.x = parseFormattedDouble(line_tokenizer.nextToken());
			segment.y = parseFormattedDouble(line_tokenizer.nextToken());

			right.setSegment(i, segment);
		}

		System.out.println("...finished parsing path from string.");
		return new Path(name, new Trajectory.Pair(left, right));
	}

}
