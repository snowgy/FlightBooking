package flightbooking;

import java.util.ArrayList;

import database.Admins;
import database.Passengers;

public class Passenger {
	private int passengerID;
	private String realName;
	private String identityID;
	private String password;
	private ArrayList<Order> passengerOrders = new ArrayList<>(); 
	public Passenger(String realname, String identityID, String password){
		this.realName = realname;
		this.identityID = identityID;
		this.password = password;
	}
	public String getRealName(){
		return realName;
	}
	public static boolean login(String realname, String password){
		boolean flag = false;
		Passenger passenger = Passengers.get(realname);
		if(passenger != null && passenger.password.equals(password))
			flag = true;
		return flag;
	}
	
	public ArrayList<Order> getPassengerOrders(){
		return passengerOrders;
	}
}
