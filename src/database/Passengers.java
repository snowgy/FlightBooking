package database;

import java.util.ArrayList;

import flightbooking.Admin;
import flightbooking.Passenger;

public class Passengers {
	public static ArrayList<Passenger> passengers = new ArrayList<>();
	static{
		Passenger test = new Passenger("gongyue", "11611908", "12345");
		passengers.add(test);
	}
	public static void add(Passenger passenger){
		passengers.add(passenger);
	}
	public static Passenger get(String realname){
		Passenger test = null;
		for(int i=0; i<passengers.size(); i++){
			if(passengers.get(i).getRealName().equals(realname))
				test = passengers.get(i);
		}
		
		return test;
	}
}
