package it.univaq.google.hashcode.model;

public class VehicleDistance {

	public Vehicle vehicle;
	public int distance;
	public int bonus;
	public Ride ride;
	
	public VehicleDistance(Vehicle vehicle, int distance, Ride ride, int bonus) {
		this.vehicle = vehicle;
		this.distance = distance;
		this.ride = ride;
		this.bonus = bonus;
	}

	
}
