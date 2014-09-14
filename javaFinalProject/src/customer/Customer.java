package customer;

import define.Define;

public class Customer {
	private int id;
	public String name;
	public int enqueueTime;
	public int ticketingTime;
	public String startStation;
	public String endStation;
	
	
	public Customer(int id, String name, int enqueueTime, int ticketingTime, String startStation, String endStation){
		this.id = id;
		this.name = name;
		this.enqueueTime = enqueueTime;
		this.ticketingTime = ticketingTime;
		this.startStation = startStation;
		this.endStation = endStation;
	}
	
	
	@Override
	public String toString() {
		StringBuilder userInfo = new StringBuilder();
		
		userInfo.append(String.format("%-12s%s",id,Define.tab));
		userInfo.append(String.format("%-12s%s",name,Define.tab));
		userInfo.append(String.format("%-12s%s",enqueueTime,Define.tab));
		userInfo.append(String.format("%-12s%s",startStation,Define.tab));
		userInfo.append(String.format("%-12s%s",endStation,Define.tab));
			
		return userInfo.toString();
	}

}
