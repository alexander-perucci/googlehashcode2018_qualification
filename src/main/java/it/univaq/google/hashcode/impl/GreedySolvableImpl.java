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

public class GreedySolvableImpl implements ISolvable {

	
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
				if(vei.getCurrentRide() == null && vei.getNextRide() == null)
					veicoliLiberi.add(vei);	
			}
			
			
			//assegno i veicoli liberi alle rides disponibili
			//considerando però se posso prenderla, ossia se T<ride.starttime
			int ss = veicoliLiberi.size();
			for(int k=0; k<ss; k++) {
				Vehicle vei = veicoliLiberi.get(k);
				List<VehicleDistance> veiDis = new ArrayList<VehicleDistance>();
				
				for(Ride ride : problemInstance.getRides()) {
					int distance = getDistanceTo(vei, ride.getStart());
					int pathLength = getDistanceGeneric(ride.getStart(), ride.getEnd());
					int bonus = problemInstance.getBonus() * (ride.getLatestFinish()-(i+distance+pathLength));
					//if(i + distance >= ride.getEarliestStart()) {
						VehicleDistance vd = new VehicleDistance(vei, distance, ride, bonus);
						veiDis.add(vd);
					//}
				}
				
				VehicleDistance vdMin = new VehicleDistance(null, Integer.MAX_VALUE, null, Integer.MIN_VALUE);
				for(VehicleDistance vd : veiDis) {
					if(vd.bonus > vdMin.bonus) {//i have a new min
						vdMin = vd;
					}
				}
				
				try {
					if(vdMin.vehicle != null) {
						vdMin.vehicle.setNextRide(vdMin.ride);
						problemInstance.getRides().remove(vdMin.ride);
						veicoliLiberi.remove(k);
						ss = veicoliLiberi.size();
					}
				}
				catch(Exception e) {
					//i can have no ride to assign
				}
			}
			
			
			int ud = 0;
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
					try {
						Ride toGoNear = null;
						int bestDis = Integer.MAX_VALUE;
						for(Ride rid : problemInstance.getRides()) {
							if(getDistanceTo(vei, rid.getStart()) < bestDis) {
								bestDis = getDistanceTo(vei, rid.getStart());
								toGoNear = rid;
							}
							
						}

						vei.setCurrentPosition(getNextVeichleStep(vei, toGoNear.getStart()));
					}
					catch(Exception e) {
						
					}
					
					/*
					if(ud == 0) {
						vei.setCurrentPosition(getNextVeichleStep(vei, new Coordinate(vei.getCurrentPosition().x+1, vei.getCurrentPosition().y)));
						ud = 1;
					}
					else if(ud == 1) {
						vei.setCurrentPosition(getNextVeichleStep(vei, new Coordinate(vei.getCurrentPosition().x, vei.getCurrentPosition().y+1)));
						ud = 0;
					}
					*/
					
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
					if(vei.getCurrentPosition().x == vei.getNextRide().getStart().x && vei.getCurrentPosition().y == vei.getNextRide().getStart().y) {
						//è finito il tragitto, il veicolo è libero
						vei.setCurrentRide(vei.getNextRide());
						vei.setNextRide(null);
						
					}
				}
				
			}

			
			
		}

		sol.setVehicles(veicoli);
		
		
		return sol;
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
	
	public int getDistanceGeneric(Coordinate startPos, Coordinate destPos) {
		return Math.abs(startPos.x-destPos.x)+Math.abs(startPos.y-destPos.y);
	}
	
}