/*
 * googlehashcode2018_qualification - Copyright (C) 2018 404 Solution Not Found team's
 *
 * googlehashcode2018_qualification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *   
 * googlehashcode2018_qualification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *   
 * You should have received a copy of the GNU General Public License
 * along with googlehashcode2018_qualification.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.univaq.google.hashcode.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import it.univaq.google.hashcode.model.Coordinate;
import it.univaq.google.hashcode.model.ProblemInstance;
import it.univaq.google.hashcode.model.Ride;
import it.univaq.google.hashcode.model.Solution;
import it.univaq.google.hashcode.model.Vehicle;

public class IOUtil {

	private static final String SEPARATOR = " ";
	private static final String NEW_LINE = "\n";

	public static ProblemInstance parseInput(File inputFile) throws IOException {
		List<String> inputLines = FileUtils.readLines(inputFile, Charsets.ISO_8859_1);

		ProblemInstance problemInstance = new ProblemInstance();

		String[] elementFirstLine = inputLines.get(0).split(SEPARATOR);
		problemInstance.setRows(Integer.parseInt(elementFirstLine[0]));
		problemInstance.setColumns(Integer.parseInt(elementFirstLine[1]));
		problemInstance.setVehicles(Integer.parseInt(elementFirstLine[2]));
		int rides = Integer.parseInt(elementFirstLine[3]);
		problemInstance.setBonus(Integer.parseInt(elementFirstLine[4]));
		problemInstance.setSteps(Integer.parseInt(elementFirstLine[5]));

		for (int i = 0; i< rides; i++) {
			String[] rideLine = inputLines.get(i+1).split(SEPARATOR);
			Ride ride = new Ride();
			ride.setId(i);
			ride.setStart(new Coordinate(Integer.parseInt(rideLine[0]), Integer.parseInt(rideLine[1])));
			ride.setEnd(new Coordinate(Integer.parseInt(rideLine[2]), Integer.parseInt(rideLine[3])));
			ride.setEarliestStart(Integer.parseInt(rideLine[4]));
			ride.setLatestFinish(Integer.parseInt(rideLine[5]));
			problemInstance.getRides().add(ride);	
		}		
		
		return problemInstance;

	}

	public static void generateOutput(Solution solution, File outFile) throws IOException {
		System.out.println("- SOLUTION Score: " + solution.getScore());
		
		// create solution to submit
		StringBuilder solutionString = new StringBuilder();
		
		for (Vehicle vehicle : solution.getVehicles()) {
			solutionString.append(vehicle.getRideDone().size());
			for (Ride ride : vehicle.getRideDone()) {
				solutionString.append(SEPARATOR + ride.getId());
			}
			solutionString.append(NEW_LINE);
		}

		FileUtils.writeStringToFile(outFile, solutionString.toString(), Charsets.ISO_8859_1);
	}

}
