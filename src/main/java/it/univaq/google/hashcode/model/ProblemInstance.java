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

import java.util.LinkedList;
import java.util.List;

public class ProblemInstance {

	private List<Ride> rides;
	private int rows;
	private int columns;
	private int vehicles;
	private int bonus;
	private long steps;

	public ProblemInstance() {
		rides = new LinkedList<>();
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getVehicles() {
		return vehicles;
	}

	public void setVehicles(int vehicles) {
		this.vehicles = vehicles;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public long getSteps() {
		return steps;
	}

	public void setSteps(long steps) {
		this.steps = steps;
	}

}
