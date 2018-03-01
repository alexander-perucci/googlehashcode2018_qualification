package it.univaq.google.hashcode.impl;

import java.util.ArrayList;
import java.util.List;

import it.univaq.google.hashcode.ISolvable;
import it.univaq.google.hashcode.model.BestRidePick;
import it.univaq.google.hashcode.model.Coordinate;
import it.univaq.google.hashcode.model.ProblemInstance;
import it.univaq.google.hashcode.model.Ride;
import it.univaq.google.hashcode.model.Solution;
import it.univaq.google.hashcode.model.Vehicle;

public class GianlucaSolvableImpl implements ISolvable {

	private List<Ride> leftRides;

	private List<Vehicle> vehicles;

	private int bonus;

	@Override
	public Solution getSolution(ProblemInstance problemInstance) {

		this.bonus = problemInstance.getBonus();

		this.vehicles = new ArrayList<>();
		for (int i = 0; i < problemInstance.getVehicles(); i++) {
			Vehicle vehicle = new Vehicle();
			vehicle.setCurrentPosition(new Coordinate(0, 0));
			vehicles.add(vehicle);
		}

		this.leftRides = problemInstance.getRides();

		Solution solution = new Solution();
		solution.setVehicles(vehicles);

		while (true) {
			BestRidePick pick = findBestAssignment();
			if (pick == null) {
				break;
			}
			Ride currentAssigningRide = pick.getRide();
			Vehicle currentAssigningVehicle = pick.getVehicle();
			leftRides.remove(currentAssigningRide);

			currentAssigningVehicle.getRideDone().add(currentAssigningRide);
			currentAssigningVehicle.setCurrentPosition(currentAssigningRide.getEnd());
			currentAssigningVehicle.setCurrentStep(pick.getFinishTime());
		}

		return solution;
	}
	
	
	private BestRidePick findBestAssignment() {
		Ride selectedRide = null;
		Vehicle selectedVehicle = null;
		int score = 0;
		int finishTime = 0;

		for (Vehicle vehicle : this.vehicles) {

			for (Ride ride : this.leftRides) {
				int bestStartingTime = calculateBestStartingTime(vehicle, ride);
				int rideDistance = calculateDistance(ride.getStart(), ride.getEnd());
				int bestEndTime = rideDistance + bestStartingTime;
				if (bestEndTime <= ride.getLatestFinish()) {
					int rideScore = rideDistance + (ride.getLatestFinish()-bestEndTime)*bonus;
					if (rideScore>score) {
						selectedRide = ride;
						selectedVehicle = vehicle;
						score = rideScore;
						finishTime = bestEndTime;
					}
				}
			}
		}
		
		return new BestRidePick(selectedVehicle, selectedRide, score, finishTime);
	}

	private static int calculateBestStartingTime(Vehicle vehicle, Ride ride) {
		int vehicleStep = vehicle.getCurrentStep();
		Coordinate vehicleCoordinate = vehicle.getCurrentPosition();

		Coordinate startingRideCoordinate = ride.getStart();

		int distanceToReach = calculateDistance(vehicleCoordinate, startingRideCoordinate);
		int bestStarting = vehicleStep + distanceToReach;
		return bestStarting >= ride.getEarliestStart() ? bestStarting : ride.getEarliestStart(); 
	}

	private static int calculateDistance(Coordinate start, Coordinate end) {
		return Math.abs(end.x-start.x)+Math.abs(end.y-start.y);
	}
}
