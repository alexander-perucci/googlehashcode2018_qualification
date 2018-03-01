package it.univaq.google.hashcode.model;

public class Vehicle {
	private Coordinate currentPosition;
	private Coordinate previousPosition;
	private Coordinate nextPosition;

	private Ride currentRide;
	private Ride previousRide;
	private Ride nextRide;

	public Coordinate getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Coordinate currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Coordinate getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(Coordinate previousPosition) {
		this.previousPosition = previousPosition;
	}

	public Coordinate getNextPosition() {
		return nextPosition;
	}

	public void setNextPosition(Coordinate nextPosition) {
		this.nextPosition = nextPosition;
	}

	public Ride getCurrentRide() {
		return currentRide;
	}

	public void setCurrentRide(Ride currentRide) {
		this.currentRide = currentRide;
	}

	public Ride getPreviousRide() {
		return previousRide;
	}

	public void setPreviousRide(Ride previousRide) {
		this.previousRide = previousRide;
	}

	public Ride getNextRide() {
		return nextRide;
	}

	public void setNextRide(Ride nextRide) {
		this.nextRide = nextRide;
	}

}
