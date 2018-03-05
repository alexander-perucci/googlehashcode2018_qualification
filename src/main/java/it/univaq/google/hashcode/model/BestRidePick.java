package it.univaq.google.hashcode.model;

public class BestRidePick {

	private Vehicle vehicle;
	private Ride ride;

	private int score;

	private int finishTime;

	public BestRidePick(Vehicle vehicle, Ride ride, int finishTime, int score) {
		super();
		this.vehicle = vehicle;
		this.ride = ride;
		this.score = score;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
}
