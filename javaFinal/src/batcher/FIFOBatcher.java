package batcher;

import java.util.ArrayList;

import customer.Customer;
import define.Define;
import queue.Queue;
import ticketSeller.TicketSeller;


public class FIFOBatcher implements Ibatcher{

	@Override
	public void moveCustomerToSellerQueue(int currentPhase, Queue lineQueue,
			ArrayList<TicketSeller> sellers) {
		
		Customer tmpCustomer;
		TicketSeller tmpSeller;
		
		
		if ( !lineQueue.isEmpty()){
				
			for(int i = 0 ; i < Define.theNumberOfTicketSeller ; i++){
				
				tmpSeller = sellers.get(i);
				
				if( lineQueue.isEmpty() )
					break;
				
				else if(tmpSeller.sellerQueue.isEmpty()){
					tmpCustomer = lineQueue.dequeue();
					tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, currentPhase);
					tmpSeller.sellerQueue.enqueue(tmpCustomer);
				}
				
			}
			
		}
		
		if ( !lineQueue.isEmpty()){
			lineQueue.increaseCustomerTimeInQueue(Define.ticketWaitingTime);
			lineQueue.increaseCustomerTimeInQueue(Define.totalWaitingTime);
		}
}

	
	
}
