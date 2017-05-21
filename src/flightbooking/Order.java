package flightbooking;

import java.util.Date;

public class Order {
	private Passenger passenger;
	private int seat;
	private Flight flight;
	private Date createDate;
	private OrderStatus orderStatus = OrderStatus.UNPAID;
	enum OrderStatus{PAID,UNPAID}
	public Order(Passenger passenger, int seat, Flight flight) {
		
		this.passenger = passenger;
		this.seat = seat;
		this.flight = flight;
		createDate = new Date();
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
