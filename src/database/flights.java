package database;

import java.util.ArrayList;

import flightbooking.Flight;
import flightbooking.Flight.FlightStatus;

public class flights {
	public static ArrayList<Flight> flights = new ArrayList<>();
	static{
		Flight flight1 = new Flight("SZ0001", "2017-05-25+15:00", "2017-05-25+16:00", "BeiJing", "ShangHai", 1000, 30, 100, FlightStatus.UNPUBLISHED);
		Flight flight2 = new Flight("SZ0002", "2017-05-25+16:00", "2017-05-25+17:00", "ShangHai", "Beijing", 1000, 30, 100, FlightStatus.UNPUBLISHED);
		Flight flight3 = new Flight("SZ0003", "2017-05-25+17:00", "2017-05-25+18:00", "Beijing", "ShenZhen", 1000, 30, 100, FlightStatus.PUBLISHED);
		Flight flight4 = new Flight("SZ0004", "2017-05-25+18:00", "2017-05-25+19:00", "ShenZhen", "BeiJing", 1000, 30, 100, FlightStatus.PUBLISHED);
		Flight flight5 = new Flight("SZ0005", "2017-05-25+19:00", "2017-05-25+20:00", "ShangHai", "ShenZhen", 1000, 30, 100, FlightStatus.PUBLISHED);
		Flight flight6 = new Flight("SZ0006", "2017-05-25+20:00", "2017-05-25+21:00", "ShenZhen", "ShangHai", 1000, 30, 100,FlightStatus.PUBLISHED);
		Flight flight7 = new Flight("SZ0007", "2017-05-25+20:00", "2017-05-25+21:00", "ShenZhen", "ShangHai", 1000, 30, 100,FlightStatus.UNPUBLISHED);
		flights.add(flight1);
		flights.add(flight2);
		flights.add(flight3);
		flights.add(flight4);
		flights.add(flight5);
		flights.add(flight6);
		flights.add(flight7);
	}
	public static Flight get(int i){
		return flights.get(i);
	}
	public static int size(){
		return flights.size();
	}
}
