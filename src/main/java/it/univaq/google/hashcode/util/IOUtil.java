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

import it.univaq.google.hashcode.model.ProblemInstance;
import it.univaq.google.hashcode.model.Solution;

public class IOUtil {

	private static final String SEPARATOR = " ";
	private static final String NEW_LINE = "\n";

	
	public static ProblemInstance parseInput(File inputFile) throws IOException {
		List<String> inputLines = FileUtils.readLines(inputFile, Charsets.ISO_8859_1);

		String[] elementFirstLine = inputLines.get(0).split(SEPARATOR);
		// int nVideo = Integer.parseInt(elementFirstLine[0]);
		// int nEndpoint = Integer.parseInt(elementFirstLine[1]);
		// int nRequest = Integer.parseInt(elementFirstLine[2]);
		// int nCacheServer = Integer.parseInt(elementFirstLine[3]);
		// int chacheSize = Integer.parseInt(elementFirstLine[4]);

		ProblemInstance problemInstance = new ProblemInstance();

		String[] elementSecondLine = inputLines.get(1).split(SEPARATOR);
		for (int i = 0; i < elementSecondLine.length; i++) {
		}

		int lineToRead = 3;
		
		return problemInstance;

	}

	public static void generateOutput(Solution solution, File outFile) throws IOException {
		System.out.println("- SOLUTION Score: " + solution.getScore());
		
		// create solution to submit
		StringBuilder solutionString = new StringBuilder();

		// FIX: 
		
		FileUtils.writeStringToFile(outFile, solutionString.toString(), Charsets.ISO_8859_1);
	}

}
