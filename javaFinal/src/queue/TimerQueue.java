package queue;

import java.util.ArrayList;

import customer.Customer;


public class TimerQueue implements Iqueue{

	private ArrayList<Customer> queue;
	private int timer;
	
	public TimerQueue(){
		this.queue = new ArrayList<Customer>();
	}
	
	public void setTimer(int value){
		this.timer = value;
	}
	
	public void decreaseTimer(){
		this.timer --;
	}
	
	
	@Override
	public Customer dequeue() {
		Customer dequeued = null;
		
		if(this.timer == 0)
			dequeued = this.queue.remove(0);
		
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
	public void increaseCustomerTimeInQueue(int i) {
		int tmp;
		
		for(int k = 1 ; k < this.queue.size(); k ++){
			tmp = this.queue.get(k).customerTimeStatus.get(i);
			this.queue.get(k).customerTimeStatus.set(i, tmp+1);
		}
		
	}
	
}
