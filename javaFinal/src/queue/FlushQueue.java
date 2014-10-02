package queue;

import java.util.ArrayList;
import customer.Customer;
import define.Define;


public class FlushQueue implements Iqueue {

	private ArrayList<Customer> queue;
	
	public FlushQueue(){
		this.queue = new ArrayList<Customer>();
	}
	
	public ArrayList<Customer> dequeueAll(){
		ArrayList<Customer> returnValue = new ArrayList<Customer>();
		
		for(int i  = 0 ; i < this.queue.size() ; i ++){
			returnValue.add(this.dequeue());
		}
		
		return returnValue;
	}
	
	@Override
	public Customer dequeue() {
		Customer dequeued = this.queue.remove(Define.first);
		return dequeued;
	}

	@Override
	public void enqueue(Customer c1) {
		if(c1 != null)
			this.queue.add(c1);
	}

	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	@Override
	public int getSize() {
		return this.queue.size();
	}

	@Override
	public Customer getCustomer(int i) {
		if(!this.queue.isEmpty())
			return this.queue.get(i);
		else
			return null;
	}

	@Override
	public void increaseCustomerTimeInQueue(int i) {
		int tmp;
		
		for(int k = 0 ; k < this.queue.size(); k ++){
			tmp = this.queue.get(k).customerTimeStatus.get(i);
			this.queue.get(k).customerTimeStatus.set(i, tmp+1);
		}
		
	}
	
	public void setCustomerTimeInQueue(int type, int value) {
		
		for(int k = 0 ; k < this.queue.size(); k ++){
			Customer tmp = this.queue.get(k);
			tmp.setCustomerTimeStatus(type, value);
		}
		
	}
	
	

}
