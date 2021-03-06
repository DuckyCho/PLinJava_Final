package queue;

import java.util.ArrayList;

import customer.Customer;
import define.Define;


public class Queue implements Iqueue{
	
	public ArrayList<Customer> queue;
	
	public Queue(){
		this.queue = new ArrayList<Customer>();
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
		return this.queue.get(i);
	}
	
	@Override
	public void increaseCustomerTimeInQueue(int i, int from) {
		int tmp;
		
		for(int k = from ; k < this.queue.size(); k ++){
			tmp = this.queue.get(k).customerTimeStatus.get(i);
			this.queue.get(k).customerTimeStatus.set(i, tmp+1);
		}
		
	}
}
