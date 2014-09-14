package fileReader;

import java.io.IOException;
import java.util.ArrayList;

import customer.Customer;
import customer.CustomerList;
import define.Define;

public class MakeCustomerList {

	
	public MakeCustomerList(CustomerFileReader cfr, CustomerList cl) throws IOException{
		ArrayList<String[]> userInfo = cfr.readFile();
		ArrayList<Customer> customerList = cl.getCustomerList();
		String[] user;
		int id;
		String name;
		int enqueueTime;
		int ticketingTime;
		String startStation;
		String endStation;
		
		for(int i = 0 ; i < userInfo.size() ; i++){
			user = userInfo.get(i);
			
			id = Integer.valueOf(user[Define.customerId]);
			name = user[Define.customerName];
			enqueueTime = Integer.valueOf(user[Define.enqueueTime]);
			ticketingTime = Integer.valueOf(user[Define.ticketingTime]);
			startStation = user[Define.startStation];
			endStation = user[Define.endStation];
			
			customerList.add(new Customer(id,name,enqueueTime,ticketingTime,startStation,endStation));
		}
	}
}
