package flightbooking;

import java.text.SimpleDateFormat;
import java.util.Date;

import database.flights;

public class Flight {
	private String flightID;
	private String StartTime;
	private String ArrivalTime;
	private String StartCity;
	private String ArrivalCity;
	private int price;
	private int currentPassenger;
	private int seatCapacity;
	private FlightStatus flightStatus = FlightStatus.UNPUBLISHED;
	
	public enum FlightStatus{UNPUBLISHED, PUBLISHED, TERMINATED, FULL, DELETED,}
	public Flight(String flightID, String startTime, String arrivalTime, String startCity, String arrivalCity,
			int price, int currentPassenger, int seatCapacity, FlightStatus flightStatus) {
		super();
		this.flightID = flightID;
		setStartTime(startTime);
		setArrivalTime(arrivalTime);
		StartCity = startCity;
		ArrivalCity = arrivalCity;
		this.price = price;
		this.currentPassenger = currentPassenger;
		this.seatCapacity = seatCapacity;
		setFlightStatus(flightStatus);
	}
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		SimpleDateFormat dayTimeFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm");
		try{
			Date start = dayTimeFormat.parse(startTime);
			if(start.getTime() >= System.currentTimeMillis()+2*60*60*1000){
				this.StartTime = startTime;
			}
			else{
				System.out.println("Time is illegal");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public String getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		try{
			SimpleDateFormat dayTimeFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm");
			Date arrival = dayTimeFormat.parse(arrivalTime);
			Date start = dayTimeFormat.parse(StartTime);
			if(arrival.after(start))
				this.ArrivalTime = arrivalTime;
			else
				System.out.println("Time is illegal");
		}catch(Exception e){
			System.out.println("Check your timeformat");
		}
	}
	public String getStartCity() {
		return StartCity;
	}
	public void setStartCity(String startCity) {
		StartCity = startCity;
	}
	public String getArrivalCity() {
		return ArrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		ArrivalCity = arrivalCity;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCurrentPassenger() {
		return currentPassenger;
	}
	public void setCurrentPassenger(int currentPassenger) {
		this.currentPassenger = currentPassenger;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	public FlightStatus getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(FlightStatus flightStatus) {
		SimpleDateFormat dayTimeFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm");
		try{
			Date start = dayTimeFormat.parse(getStartTime());
			if(start.getTime() <= System.currentTimeMillis()+2*60*60*1000){
				this.flightStatus = FlightStatus.TERMINATED;
			}
			else if(getCurrentPassenger()>=getSeatCapacity()){
				this.flightStatus = FlightStatus.FULL;
			}
			
			else this.flightStatus = flightStatus;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	
	}
	
	public boolean validateFlightID(String flightID){
		boolean flag = true;
		for(int i=0; i<flights.size();i++){
			if(flights.get(i).getFlightID().equals(flightID)){
				System.out.println("your id is repeated");
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public boolean validateSeatCapacity(int seatCapacity){
		boolean flag = true;
		if(seatCapacity<=currentPassenger){
			System.out.println("your seat capacity is less than current passenger.");
			flag = false;
			}
		return flag;
	}
	
	public boolean validateStartTime(String startTime){
		boolean flag = false;
		SimpleDateFormat dayTimeFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm");
		try{
			Date start = dayTimeFormat.parse(startTime);
			if(start.getTime() >= System.currentTimeMillis()+2*60*60*1000){
				flag = true;
			}
			else{
				System.out.println("StartTime is illegal");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}
	public boolean validateArrivalTime(String arrivalTime){
		boolean flag = false;
		try{
			SimpleDateFormat dayTimeFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm");
			Date arrival = dayTimeFormat.parse(arrivalTime);
			Date start = dayTimeFormat.parse(StartTime);
			if(arrival.after(start))
				flag = true;
			else
				System.out.println("ArrivalTime is illegal");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}
	public String toString(){
		return String.format("1.flightID:%s\t2.StartTime:%s\t3.ArrivalTime:%s\t4.StartCity:%s\n5.ArrivalCity:%s\t6.price:%d\t7.current:%d\t8.capacity:%d\t9.Status:%s\n", 
				flightID, StartTime,ArrivalTime,StartCity,ArrivalCity,price,currentPassenger,seatCapacity,flightStatus);
	}
	
	
	
	
}
