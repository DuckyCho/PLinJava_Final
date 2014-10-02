package ticketSeller;

import queue.Iqueue;
import queue.TimerQueue;
import customer.Customer;
import define.Define;


public class TicketSeller {

	public TimerQueue sellerQueue;
	private int sellerNum;
	
	public TicketSeller(int i){
		this.sellerNum = i;
		sellerQueue = new TimerQueue();
	}
	
	public void operate(Iqueue platformQueue, int phase){
		if(!this.sellerQueue.isEmpty()){
			moveCustomerToPlatformQueue(platformQueue,phase);
			
			if(this.sellerQueue.getSize() > 1){
				increaseCustomerWaitingTime();
				
			}
		}
	}
	
	
	
	public void increaseCustomerWaitingTime(){
		this.sellerQueue.increaseCustomerTimeInQueue(Define.ticketWaitingTime);
		this.sellerQueue.increaseCustomerTimeInQueue(Define.totalWaitingTime);
	}
	
		
	public void moveCustomerToPlatformQueue(Iqueue s1, int phase){
		
		Customer tmp = this.sellerQueue.getCustomer(Define.first);
		int leavingTime = tmp.getCustomerTimeStatus(Define.ticketOpStartTime) + tmp.getTicketOpTime();
		
		if( leavingTime == phase){
			tmp = this.sellerQueue.dequeue();
			s1.enqueue(tmp);
		}
		
	}
}
