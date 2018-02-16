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
package it.univaq.google.hashcode.model;

public class Solution {
	private long score;
	
	// FIX: add attributes needed for the solution
	
	public Solution(long score) {
		this.score = score;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
}
