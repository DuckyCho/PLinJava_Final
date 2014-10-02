package batcher;

import java.util.ArrayList;

import queue.Queue;
import ticketSeller.TicketSeller;
import customer.Customer;
import define.Define;

public class RoundRobinBatcher implements Ibatcher {

	public int sellerNum;
	
	public RoundRobinBatcher(){
		this.sellerNum = 0;
	}
	
	@Override
	public void moveCustomerToSellerQueue(int currentPhase, Queue lineQueue,
			ArrayList<TicketSeller> sellers) {
		
		Customer tmpCustomer;
		TicketSeller tmpSeller;
		
		
		while ( !lineQueue.isEmpty()){
				this.sellerNum = this.sellerNum % Define.theNumberOfTicketSeller;
				tmpSeller = sellers.get(this.sellerNum); 
				tmpCustomer = lineQueue.dequeue();
				if(tmpSeller.sellerQueue.isEmpty())
					tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, currentPhase);
				else{
					Customer last = tmpSeller.sellerQueue.getCustomer(tmpSeller.sellerQueue.getSize()-1);
					int startT = last.getCustomerTimeStatus(Define.ticketOpStartTime) + last.getTicketOpTime();
					tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, startT);
				}
				
				tmpSeller.sellerQueue.enqueue(tmpCustomer);
				this.sellerNum++;
			}
				
		}
	
}
