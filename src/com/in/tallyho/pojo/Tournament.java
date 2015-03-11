package com.in.tallyho.pojo;

public class Tournament {

	private int id;
	private String location;
	private String startDate;
	private int status;

	public Tournament(int id, String location, String startDate, int staus) {
		this.id = id;
		this.location = location;
		this.startDate = startDate;
		this.status = staus;
	}

	public int getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getStatus() {
		String statusText = "Ongoing";
		switch (status) {
		case 2:
			statusText = "Inviting";
			break;

		case 3:
			statusText = "Upcoming";
			break;
		case 4:
			statusText = "Ongoing";
			break;
		default:
			break;
		}
		return statusText;
	}
}
