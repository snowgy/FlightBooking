package flightbooking;

import java.util.ArrayList;
import java.util.Scanner;

import database.Admins;
import database.Passengers;
import database.flights;
import flightbooking.Flight.FlightStatus;

public class Menu {
	public int number = 1;
	Scanner input = new Scanner(System.in);
	public void showFlightInfo(int flag){
		System.out.println("------FlightInfo------");
		System.out.printf("%s\t%s\t%s\n", "ÐòºÅ","º½°àºÅ","Æð·É³ÇÊÐ");
		if(flag==1){
		for(int i=0; i< flights.size(); i++){
			if(flights.get(i).getFlightStatus() == FlightStatus.PUBLISHED)
			System.out.printf("%d\t%s\t%s\n",i+1,flights.get(i).getFlightID(),
					flights.get(i).getStartCity());
			}}
		else{
			for(int i=0; i< flights.size(); i++){
				System.out.printf("%d\t%s\t%s\n",i+1,flights.get(i).getFlightID(),
						flights.get(i).getStartCity());}
		}
		}
	public void showOrderInformation(ArrayList<Order> orders){
		for(int i=0; i<orders.size(); i++){
			System.out.printf("%s\t%s\t%s\t%s\n","number","realname","seat","flightID");
			System.out.printf("%d\t%s\t%d\t%s\n",i+1,orders.get(i).getPassenger().getRealName(),
					orders.get(i).getSeat(),orders.get(i).getFlight().getFlightID());
		}
	}
	public void adminLogin(){
		System.out.println("--------Admin Login In-------");
		System.out.println("username:");
		String username = input.next();
		System.out.println("password:");
		String password = input.next();
		if(Admin.login(username, password))
			admin(Admins.get(username));
		else {
			System.out.println("Sorry,your username or password is incorrect");
			index();
		}
	}
	public void passengerLogin(){
		System.out.println("--------Passenger Login In-------");
		System.out.println("username:");
		String username = input.next();
		System.out.println("password:");
		String password = input.next();
		if(Passenger.login(username, password))
			passenger(Passengers.get(username));
		else {
			System.out.println("Sorry,your username or password is incorrect");
			index();
		}
	}
	public void register(){
		System.out.println("------Register-----");
		System.out.println("realname:");
		String username = input.next();
		System.out.println("password:");
		String password = input.next();
		System.out.println("identityID:");
		String identityID = input.next();
		Passenger passenger = new Passenger(username, identityID, password);
		Passengers.add(passenger);
	}
	public void createFlight(Admin admin){
		FlightStatus flightStatus;
		System.out.print("FlightID:");
		String flightID = input.next();
		System.out.print("startTime(format:2017-05-07+13:00):");
		String startTime = input.next();
		System.out.print("startCity:");
		String startCity = input.next();
		System.out.print("arrivalTime(format:2017-05-07+16:00):");
		String arrivalTime = input.next();
		System.out.print("arrivalCity:");
		String arrivalCity = input.next();
		System.out.print("flghtStatus(PUBLISH:1 UNPUBLISH:0)");
		if(input.nextInt()==1) 
			flightStatus = FlightStatus.PUBLISHED;
		else flightStatus = FlightStatus.UNPUBLISHED;
		System.out.print("price:");
		int price = input.nextInt();
		System.out.print("currentPassenger:");
		int currentPassenger = input.nextInt();
		System.out.print("seatCapacity:");
		int seatCapacity = input.nextInt();
		admin.createFlight(flightID, startTime, arrivalTime, startCity, arrivalCity, price, currentPassenger, seatCapacity, flightStatus);
	}
	public void bookPage(Passenger passenger){
		System.out.println("1.Boook 2.Query 3.Cancel");
		switch(input.nextInt()){
		case 1:
			System.out.println("Please choose a flight:");
			int flightnumber = input.nextInt();
			Flight flight = flights.get(flightnumber-1);
			System.out.println("Please input seat number:");
			int seat = input.nextInt();
			Order order = new Order(passenger, seat, flight);
			passenger.getPassengerOrders().add(order);
			flight.setCurrentPassenger(flight.getCurrentPassenger()+1);
			break;
		case 2:
			System.out.println("You can input flightID or City:");
			String s = input.next();
			for(int i=0; i<flights.size(); i++){
				flight = flights.get(i);
				if(flight.getFlightStatus()==FlightStatus.PUBLISHED && (flight.getFlightID().contains(s)||flight.getStartCity().contains(s)||flight.getArrivalCity().contains(s)))
					System.out.println(flight);
			
			}
			break;
		case 3:
			passenger(passenger);
			break;
		}
	}
	public void deleteFlight(){
		System.out.println("Please choose a flight to delete:");
		int number = input.nextInt();
		Flight flight = flights.get(number-1);
		if(flight.getFlightStatus()==FlightStatus.UNPUBLISHED)
		flight.setFlightStatus(FlightStatus.DELETED);
		else System.out.println("Sorry,you can't delete this flight.");
	
	}
	public void editFlight(){
		System.out.println("Please choose a flight to edit:");
		int number = input.nextInt();
		Flight flight = flights.get(number-1);
		if(flight.getFlightStatus()==FlightStatus.TERMINATED&&flight.getFlightStatus()==FlightStatus.DELETED){
			System.out.println("Sorry,you can't edit this flight.");
			return;
			}
		else if(flight.getFlightStatus()==FlightStatus.UNPUBLISHED){
			while(true){
			System.out.println(flight);
			System.out.println("Please choose a attribute you want to change("
					+ "enter -1 to exit):");
			number = input.nextInt();
			switch(number){
			case -1:
				return;
			case 1:
				System.out.println("flightID:");
				String flightID = input.next();
				if(flight.validateFlightID(flightID))
					flight.setFlightID(flightID);
				break;
			case 2:
				System.out.println("StartTime:");
				String startTime = input.next();
				if(flight.validateStartTime(startTime))
					flight.setStartTime(startTime);
				break;
			case 3:
				System.out.println("ArrivalTime:");
				String arrivalTime = input.next();
				if(flight.validateStartTime(arrivalTime))
					flight.setStartTime(arrivalTime);
				break;
			case 4:
				System.out.println("StartCity:");
				String startCity = input.next();
				flight.setStartCity(startCity);
				break;
			case 5:
				System.out.println("ArrivalCity:");
				String arrivalCity = input.next();
				flight.setArrivalCity(arrivalCity);
				break;
			case 6:
				System.out.println("Price:");
				int price = input.nextInt();
				flight.setPrice(price);
				break;
			case 7:
				System.out.println("Current:");
				int current = input.nextInt();
				flight.setCurrentPassenger(current);
				break;
			case 8:	
				System.out.println("Capacity:");
				int capacity = input.nextInt();
				if(flight.validateSeatCapacity(capacity))
					flight.setSeatCapacity(capacity);
				break;
			case 9:
				System.out.println("Status(1.PUBLISHED 2.UNPUNLISHED :");
				number = input.nextInt();
				switch(number){
				case 1: flight.setFlightStatus(FlightStatus.PUBLISHED); break;
				case 2: flight.setFlightStatus(FlightStatus.UNPUBLISHED); break;
				}
			}
			}
		}
		else{
			while(true){
			System.out.printf("1.Price:%d\n2.Capacity:%d\n",flight.getPrice(),flight.getSeatCapacity());
			System.out.println("Choose the arribute you want to change(enter -1 to exit):");
			number = input.nextInt();
			switch(number){
			case -1:
				return;
			case 1:
				System.out.println("Price:");
				int price = input.nextInt();
				flight.setPrice(price);
			case 2:
				System.out.println("Capacity:");
				int capacity = input.nextInt();
				if(flight.validateSeatCapacity(capacity))
					flight.setSeatCapacity(capacity);
				break;
			}
		  }
		}
	}
	public void userManagement(Admin admin){
		System.out.println("-----User Management-----");
		System.out.println("1.edit info 2.create new admin");
		number = input.nextInt();
		switch(number){
		case 1:
			System.out.println("username:");
			admin.setUsername(input.next());
			System.out.println("password:");
			admin.setPassword(input.next());
			break;
		case 2:
			System.out.println("username:");
			String username = input.next();
			System.out.println("password:");
			String password = input.next();
			admin.createAdmin(username, password);
			break;
		}
		
	}
	
	public void index(){
		System.out.println("-------index-------");
		System.out.println("Please input a number:");
		System.out.println("1.Login for admin 2.Login for passenger 3.Register ");
		number = input.nextInt();
		switch(number){
		case 1:
			adminLogin();
			break;
		case 2:
			passengerLogin();
			break;
		case 3:
			register();
			index();
		}
	}
	public void admin(Admin admin){
		System.out.println("-------Admin------");
		System.out.println("Please input a number");
		System.out.println("1.Create Flight 2.Query Flight 3.User Management 4.Log out");
		number = input.nextInt();
		switch(number){
		case 1:
			createFlight(admin);
			admin(admin);
			break;
		case 2:
			showFlightInfo(0);
			System.out.println("1.Create Flight 2.Edit Flight 3.Delete Flight 4.Log out");
			System.out.println("Please choose a number:");
			number = input.nextInt();
			switch(number){
			case 1:
				createFlight(admin);
				break;
			case 2:
				editFlight();
				admin(admin);
				break;
			case 3:
				deleteFlight();
				admin(admin);
				break;
			case 4:
				index();
				break;
			}
			break;
		case 3:
			userManagement(admin);
			break;
		case 4:
			index();
			break;
		}
			
	}
	public void passenger(Passenger passenger){
		System.out.println("-----Passenger------");
		System.out.println("1.Query Flight 2.My Order 3.Log out");
		number = input.nextInt();
		switch(number){
		case 1:
			showFlightInfo(1);
			bookPage(passenger);
			passenger(passenger);
			break;
		case 2:
			showOrderInformation(passenger.getPassengerOrders());
			System.out.println("----Manage Orders-----");
			System.out.println("1.Cancel Orders 2.Back");
			int number = input.nextInt();
			switch(number){
			case 1:
				System.out.println("Please choose an order:");
				number = input.nextInt();
				passenger.getPassengerOrders().remove(number-1);
				passenger(passenger);
				break;
			case 2:
				passenger(passenger);
				break;
			}
			break;
		case 3:
			index();
			break;
		}
		
	}
}
