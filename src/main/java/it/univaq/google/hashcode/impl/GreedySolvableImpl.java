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
package it.univaq.google.hashcode.impl;

import it.univaq.google.hashcode.ISolvable;
import it.univaq.google.hashcode.model.ProblemInstance;
import it.univaq.google.hashcode.model.Solution;
import it.univaq.google.hashcode.util.ProblemUtil;

public class GreedySolvableImpl implements ISolvable {

	@Override
	public Solution getSolution(ProblemInstance problemInstance) {
		
		// FIX: calculate the solution
		
		return new Solution(ProblemUtil.calculateScore());
	}
	
}
