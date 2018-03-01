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

import java.util.ArrayList;
import java.util.List;

import it.univaq.google.hashcode.ISolvable;
import it.univaq.google.hashcode.model.Coordinate;
import it.univaq.google.hashcode.model.ProblemInstance;
import it.univaq.google.hashcode.model.Ride;
import it.univaq.google.hashcode.model.Solution;
import it.univaq.google.hashcode.model.Vehicle;
import it.univaq.google.hashcode.util.ProblemUtil;

public class GreedySolvableImplLor implements ISolvable {

	
	@Override
	public Solution getSolution(ProblemInstance problemInstance) {
		
		// FIX: calculate the solution
		
		Solution sol = new Solution();
		
		List<Vehicle> veicoli = new ArrayList<Vehicle>();
		
		for(int i=0; i<problemInstance.getVehicles(); i++) {
			Vehicle vei = new Vehicle();
			veicoli.add(vei);
			vei.setCurrentPosition(new Coordinate(0, 0));
		}	
		
		//instant T
		for(int i = 0; i < problemInstance.getSteps(); i++) {
			
			List<Vehicle> veicoliLiberi = new ArrayList<Vehicle>();
			for(Vehicle vei : veicoli) {
				if(vei.getCurrentRide() == null)
					veicoliLiberi.add(vei);	
			}
			
			
			
			for(Vehicle vei : veicoliLiberi) {
				for(Ride ride : problemInstance.getRides()) {
					
				}
			}
			
			
		}
		
		return new Solution(ProblemUtil.calculateScore());
	}
	
	
	public Ride getBestRide(Vehicle vei, int curTime, ProblemInstance problemInstance) {
		
		
		
		return null;
	}
	
	public int getDistanceTo(Vehicle vei, Coordinate destPos) {
		return Math.abs(vei.getCurrentPosition().x-destPos.x)+Math.abs(vei.getCurrentPosition().y-destPos.y);
	}
	
}
