package customer;

import java.util.ArrayList;

public class CustomerList {
	
	private ArrayList<Customer> customerList;
	private static CustomerList instance = new CustomerList(); 
	
	private CustomerList(){
		this.customerList = new ArrayList<Customer>();
	}
	
	public static CustomerList getInstance(){
		return instance;
	}
	
	public ArrayList<Customer> getCustomerList(){
		return this.customerList;
	}

	@Override
	public String toString() {
		StringBuilder userInfo = new StringBuilder();
		
		for(int i = 0; i <this.customerList.size(); i++){
			userInfo.append(this.customerList.get(i).toString());
			userInfo.append("\n");
		}
		
		return userInfo.toString();
	}
	
	
	
}
