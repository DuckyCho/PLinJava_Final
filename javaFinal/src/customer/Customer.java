package customer;

import java.util.ArrayList;
import define.Define;


public class Customer {
	
	private int id;
	private String name;
	private int startTime;
	private int ticketOpTime;
	private String startStation;
	private String endStation;
	
	public ArrayList<Integer> customerTimeStatus;
	
	
	public Customer(int id, String name, int startTime, int ticketOpTime, String startStation, String endStation){
		
		
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.ticketOpTime = ticketOpTime;
		this.startStation = startStation;
		this.endStation = endStation;
	
		
		this.customerTimeStatus = new ArrayList<Integer>();
		for(int i = 0 ; i < Define.customerTimeStatusOption ; i++){
			this.customerTimeStatus.add(0);
		}
	
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	public int getStartTime(){
		return this.startTime;
	}
	
	public int getTicketOpTime(){
		return this.ticketOpTime;
	}
	
	public void setTicketOpTime(int value){
		this.ticketOpTime =value;
	}
	
	
	public String getStartStation(){
		return this.startStation;
	}
	
	public String getEndStation(){
		return this.endStation;
	}
	
	public void setCustomerTimeStatus(int type, int value){
		this.customerTimeStatus.set(type,value);
	}
	
	public int getCustomerTimeStatus(int type){
		 return customerTimeStatus.get(type);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(Define.tab);
		sb.append(this.name);
		sb.append(Define.tab);
		sb.append(this.startTime);
		sb.append(Define.tab);
		sb.append(this.ticketOpTime);
		sb.append(Define.tab);
		sb.append(String.format("%10s",this.startStation));
		sb.append(Define.tab);
		sb.append(String.format("%10s",this.endStation));
		sb.append(Define.tab);
		
		sb.append("ticketWaitingTime : ");
		sb.append(this.customerTimeStatus.get(0));
		sb.append(Define.tab);
		
		sb.append("trainWaitingTime : ");
		sb.append(this.customerTimeStatus.get(1));
		sb.append(Define.tab);
		
		sb.append("totalWaitingTime : ");
		sb.append(this.customerTimeStatus.get(2));
		sb.append(Define.tab);
		
		sb.append("totalTravelTime : ");
		sb.append(this.customerTimeStatus.get(3));
		sb.append(Define.tab);
		
		sb.append("endTime : ");
		sb.append(this.customerTimeStatus.get(4));
		sb.append(Define.tab);
		
		
		
		return sb.toString();
	}
	
	
}
