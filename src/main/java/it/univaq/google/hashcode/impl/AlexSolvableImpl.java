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

public class AlexSolvableImpl implements ISolvable {

	@Override
	public Solution getSolution(ProblemInstance problemInstance) {
		Solution solution = new Solution();
		List<Ride> ridesDone = new ArrayList<>();

		for (int i = 0; i < problemInstance.getVehicles(); i++) {
			//
			Coordinate fistPosition = new Coordinate(0, 0);
			Vehicle vehicle = new Vehicle();
			solution.getVehicles().add(vehicle);
			vehicle.setCurrentPosition(fistPosition);
			int currentTime = 0;

			while (true) {
				Ride rideSelected = getRide(problemInstance.getRides(), ridesDone, currentTime, vehicle);
				if (rideSelected == null) {
					break;	
				}
				ridesDone.add(rideSelected);
				currentTime += getDistance(rideSelected) + timeToArrive(rideSelected, vehicle);
				vehicle.getRideDone().add(rideSelected);
				vehicle.setCurrentPosition(rideSelected.getEnd());
				
			}

		}

		return solution;
	}

	private Ride getRide(List<Ride> rides, List<Ride> ridesDone, int actualTime, Vehicle vehicle) {
		Ride best = rides.get(0);
		for (int i = 1; i < rides.size(); i++) {
			if (!ridesDone.contains(rides.get(i))) {
				best = getRideDistMin(best, rides.get(i), actualTime, vehicle);
			}
		}
		if (ridesDone.contains(best) || actualTime >= best.getEarliestStart() + timeToArrive(best, vehicle)) {
			return null;
		} else {
			return best;
		}

	}

	private Ride getRideDistMin(Ride first, Ride second, int actualTime, Vehicle vehicle) {
		int distanceFirst = getDistance(first);
		int timeToArriveFirst = timeToArrive(first, vehicle);
		boolean firstIsOk = actualTime >= first.getEarliestStart() + timeToArriveFirst;

		int distanceSecond = getDistance(second);
		int timeToArriveSecond = timeToArrive(second, vehicle);
		boolean secondIsOk = actualTime >= second.getEarliestStart() + timeToArriveSecond;

		if (firstIsOk && secondIsOk) {
			return (distanceFirst + timeToArriveFirst < distanceSecond + timeToArriveSecond) ? first : second;
		} else if (secondIsOk == false) {
			return first;
		}

		return first;

		// return (getDistance(first, actualTime) <= getDistance(second, actualTime)) ?
		// first : second;
	}


	private int getDistance(Ride ride) {
		return Math.abs(ride.getEnd().x - ride.getStart().x) + Math.abs(ride.getEnd().y - ride.getStart().y);
	}

	private int timeToArrive(Ride ride, Vehicle vehicle) {
		return Math.abs(vehicle.getCurrentPosition().x - ride.getStart().x)
				+ Math.abs(vehicle.getCurrentPosition().y - ride.getStart().y);
	}

}
