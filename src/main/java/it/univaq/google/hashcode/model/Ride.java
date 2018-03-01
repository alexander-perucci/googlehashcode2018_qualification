package it.univaq.google.hashcode.model;

public class Ride {
	private int id;
	private Coordinate start;
	private Coordinate end;

	private int earliestStart;
	private int latestFinish;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

	public Coordinate getEnd() {
		return end;
	}

	public void setEnd(Coordinate end) {
		this.end = end;
	}

	public int getEarliestStart() {
		return earliestStart;
	}

	public void setEarliestStart(int earliestStart) {
		this.earliestStart = earliestStart;
	}

	public int getLatestFinish() {
		return latestFinish;
	}

	public void setLatestFinish(int latestFinish) {
		this.latestFinish = latestFinish;
	}

}
