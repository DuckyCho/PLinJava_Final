package customerList;

import java.io.IOException;
import java.util.ArrayList;

import queue.Iqueue;
import queue.Queue;
import station.Station;
import station.StationList;
import customer.Customer;
import define.Define;
import fileIO.CustomerFileReader;

public class StartCustomerList {
	
	private Queue startCustomer;
	private int initialTotalCustomerCount;
	private static StartCustomerList instance = new StartCustomerList();
	
	private StartCustomerList(){
		 this.startCustomer = new Queue();
		 this.initialTotalCustomerCount = 0;
	}
	
	public static StartCustomerList getInstance(){
		return StartCustomerList.instance;
	}
	
	public int getRestPeopleCount(){
		return this.startCustomer.getSize();
	}
	
	public int getInitialTotalCustomerCount(){
		return this.initialTotalCustomerCount;
	}
	
	public void moveCustomerToLineQueue(int phase, StationList sl ){
		
		Customer tmp;
		int customerStartTime;
		String startStationName;
		Iqueue startStationLineQueue;
		
		while ( !this.startCustomer.isEmpty() ){ 
			
			tmp = this.startCustomer.getCustomer(Define.first);
			customerStartTime = tmp.getStartTime();
			startStationName = tmp.getStartStation();
			
			
			try{
				int stationOrder = Define.station.get(startStationName);
				Station startStation = sl.getStation(stationOrder);
				startStationLineQueue = startStation.lineQueue;
				if(customerStartTime == phase){
					tmp = this.startCustomer.dequeue();
					startStationLineQueue.enqueue(tmp);
					sl.totalCustomer++;
					startStation.totalCustomerInStation++;
				}
				
				else
					break;
			
			}catch(NullPointerException e){
			
				this.startCustomer.dequeue();
				
			}
			
			
			
			
			
		}
		
	}
	
	public Queue getStartCustomerList(){
		return this.startCustomer;
	}
	
		
	public void setStartCustomerList(CustomerFileReader cfr){
		ArrayList<String[]> customerInfo;
		
		try {
			customerInfo = cfr.readFile();
			
			Customer tmp;
			
			for(int i = 0 ; i < customerInfo.size() ; i ++){
				this.initialTotalCustomerCount++;
				
				int id = Integer.valueOf(customerInfo.get(i)[Define.id]); 
				String name = customerInfo.get(i)[Define.name];
				int startTime = Integer.valueOf(customerInfo.get(i)[Define.startTime]);
				int ticketOpTime = Integer.valueOf(customerInfo.get(i)[Define.ticketOpTime]);
				String startStation = customerInfo.get(i)[Define.startStation];
				String endStation = customerInfo.get(i)[Define.endStation];
				
				tmp = new Customer(	id,name,startTime,ticketOpTime,startStation,endStation);
				
				this.startCustomer.enqueue(tmp);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
}
