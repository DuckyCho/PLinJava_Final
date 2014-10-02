package customerList;

import queue.Queue;
import customer.Customer;

public class EndCustomerList {
	
	private Queue endCustomer;
	private static EndCustomerList instance = new EndCustomerList();
	
	private EndCustomerList(){
		 this.endCustomer = new Queue();
	}
	
	
	public static EndCustomerList getInstance(){
		return EndCustomerList.instance;
	}
	
	public void enqueueCustomer(Customer c1){
		this.endCustomer.enqueue(c1);
	}
	
	public Customer dequeueCustomer(){
		return this.endCustomer.dequeue();
	}
	
	
	public int getEndPeopleCount(){
		return this.endCustomer.getSize();
	}
}
