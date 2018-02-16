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
package it.univaq.google.hashcode;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import it.univaq.google.hashcode.impl.GreedySolvableImpl;
import it.univaq.google.hashcode.model.ProblemInstance;
import it.univaq.google.hashcode.model.Solution;
import it.univaq.google.hashcode.util.IOUtil;
import it.univaq.google.hashcode.util.ProblemUtil;

public class GenerateSolutionTest {
	public static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test" + File.separatorChar + "resources" + File.separatorChar;
	public static final String INPUT_TEST_RESOURCES = TEST_RESOURCES + "input" + File.separatorChar;
	public static final String OUTPUT_TEST_RESOURCES= TEST_RESOURCES + "output" + File.separatorChar;
	public static final String OUTPUT_GENERATED_SOLUTION = "." + File.separatorChar + "generated_solution" + File.separatorChar;
	public static final String INPUT_FILE_EXTENSION = ".in";
	public static final String OUTPUT_FILE_EXTENSION = ".out";

	@Test
	public void test_kittens() {
		try {
			generateSolution("kittens");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_me_at_the_zoo() {
		try {
			generateSolution("me_at_the_zoo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_trending_today() {
		try {
			generateSolution("trending_today");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_videos_worth_spreading() {
		try {
			generateSolution("videos_worth_spreading");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateSolution(String fileName) throws IOException {
		System.out.println("RUNNING: " + fileName);
		
		long start = System.currentTimeMillis();
		
		// create problem instance
		ProblemInstance problemInstance = IOUtil
				.parseInput(new File(INPUT_TEST_RESOURCES + fileName + INPUT_FILE_EXTENSION));

		// instance with correct implementation
		ISolvable solvable = new GreedySolvableImpl();

		// get solution 
		Solution solution = solvable.getSolution(problemInstance);
		
		// generate output file to submit
		IOUtil.generateOutput(solution, new File(OUTPUT_GENERATED_SOLUTION + fileName + "_"
				+ solution.getScore() + "_" + ProblemUtil.getActualTime(".") + OUTPUT_FILE_EXTENSION));
		
		System.out.println("END RUNNING: " + fileName + "  -  " + (System.currentTimeMillis() - start) + " ms \n");
	}

}
