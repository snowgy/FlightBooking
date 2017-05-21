package flightbooking;

import database.Admins;
import database.flights;
import flightbooking.Flight.FlightStatus;

public class Admin {
	private String username = "root";
	private String password = "snow1234";

	public Admin(String username, String password){
			this.username = username;
			this.password = password;
		}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static boolean login(String username, String password){
		boolean flag = false;
		Admin admin = Admins.get(username);
		if(admin != null && admin.password.equals(password))
			flag = true;
		return flag;
	}
	
	public Admin createAdmin(String username, String password){
		Admin admin = new Admin(username, password);
		Admins.add(admin);
		return admin;
	}
	public Flight createFlight(String flightID, String startTime, String arrivalTime, String startCity, String arrivalCity,
			int price, int currentPassenger, int seatCapacity, FlightStatus flightStatus)
	{
		Flight flight = new Flight(flightID, startTime, arrivalTime, startCity, arrivalCity,
			price, currentPassenger, seatCapacity, flightStatus);
		flights.flights.add(flight);
		return flight;
	}
}
