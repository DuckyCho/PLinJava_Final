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
			int beforMoveCount = platformQueue.getSize();
			moveCustomerToPlatformQueue(platformQueue,phase);
			int afterMoveCount = platformQueue.getSize();
			
			if( !this.sellerQueue.isEmpty() &&
				afterMoveCount != beforMoveCount){
				increaseCustomerWaitingTime(Define.first);
				
			}
			else{
				increaseCustomerWaitingTime(Define.second);
			}
		}
	}
	
	
	
	public void increaseCustomerWaitingTime(int from){
		this.sellerQueue.increaseCustomerTimeInQueue(Define.ticketWaitingTime,from);
		this.sellerQueue.increaseCustomerTimeInQueue(Define.totalWaitingTime,from);
	}
	
		
	public void moveCustomerToPlatformQueue(Iqueue s1, int phase){
		
		Customer tmp = this.sellerQueue.getCustomer(Define.first);
		int leavingTime = tmp.getCustomerTimeStatus(Define.ticketOpStartTime) + tmp.getTicketOpTime();
		
		if( leavingTime == phase){
			tmp = this.sellerQueue.dequeue();
			s1.enqueue(tmp);
		}
		
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("TicketSeller NO."+this.sellerNum+Define.newline);
		
		if(this.sellerQueue.isEmpty())
			result.append("empty");
		else{
			for(int i =0 ; i < this.sellerQueue.getSize() ; i++){
				result.append(this.sellerQueue.getCustomer(i).toString() + Define.newline);
			}
		}
		
		result.append(Define.newline);
		return result.toString();
	}
	
	
	
}
