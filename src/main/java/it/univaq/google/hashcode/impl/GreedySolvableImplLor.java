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
import it.univaq.google.hashcode.model.VehicleDistance;
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
			
			
			//assegno i veicoli liberi alle rides disponibili
			for(Ride ride : problemInstance.getRides()) {
				List<VehicleDistance> veiDis = new ArrayList<VehicleDistance>();
				for(Vehicle vei : veicoliLiberi) {
					int distance = getDistanceTo(vei, ride.getStart());
					VehicleDistance vd = new VehicleDistance(vei, distance, ride);
					veiDis.add(vd);
				}
				
				VehicleDistance vdMin = new VehicleDistance(null, Integer.MAX_VALUE, null);
				for(VehicleDistance vd : veiDis) {
					if(vd.distance < vdMin.distance) {//i have a new min
						vdMin = vd;
					}
				}

				//remove the ride from list
				vdMin.vehicle.setNextRide(vdMin.ride);
				problemInstance.getRides().remove(vdMin.ride);
				
			}
			
			for(Vehicle vei : veicoli) {
				//se il veicolo ha una curRide o nextRide lo muovo di uno verso la rideEnd o rideStart, altrimenti?
				if(vei.getCurrentRide() != null) {
					//go to the end
					vei.setCurrentPosition(getNextVeichleStep(vei, vei.getCurrentRide().getEnd()));
				}
				else if(vei.getNextRide() != null) {
					//go to the start of this ride
					vei.setCurrentPosition(getNextVeichleStep(vei, vei.getNextRide().getStart()));
				}
				else {
					//TODO
					
				}
			}
			
			//controllo se sono all inizio o alla fine di qualcosa
			for(Vehicle vei : veicoli) {
				if(vei.getCurrentRide() != null) {
					if(vei.getCurrentPosition().x == vei.getCurrentRide().getEnd().x && vei.getCurrentPosition().y == vei.getCurrentRide().getEnd().y) {
						//è finito il tragitto, il veicolo è libero
						vei.getRideDone().add(vei.getCurrentRide());
						vei.setCurrentRide(null);
						vei.setNextRide(null);
						
					}
				}
				else if(vei.getNextRide() != null) {
					if(vei.getCurrentPosition().x == vei.getCurrentRide().getStart().x && vei.getCurrentPosition().y == vei.getNextRide().getEnd().y) {
						//è finito il tragitto, il veicolo è libero
						vei.getRideDone().add(vei.getCurrentRide());
						vei.setCurrentRide(null);
						vei.setNextRide(null);
						
					}
				}
				
			}

			
			
		}
		
		return new Solution(ProblemUtil.calculateScore());
	}
	
	
	public Coordinate getNextVeichleStep(Vehicle vei, Coordinate dest) {
		Coordinate coord = vei.getCurrentPosition();
		
		if(vei.getCurrentPosition().x > dest.x)
			coord.x -= 1;
		else if(vei.getCurrentPosition().x < dest.x) 
			coord.x += 1;
		else if(vei.getCurrentPosition().y > dest.y) 
			coord.y -= 1;
		else if(vei.getCurrentPosition().y < dest.y) 
			coord.y += 1;

		return coord;
		
	}
	
	public Ride getBestRide(Vehicle vei, int curTime, ProblemInstance problemInstance) {
		
		
		
		return null;
	}
	
	public int getDistanceTo(Vehicle vei, Coordinate destPos) {
		return Math.abs(vei.getCurrentPosition().x-destPos.x)+Math.abs(vei.getCurrentPosition().y-destPos.y);
	}
	
}
