package batcher;

import java.util.ArrayList;

import queue.Queue;
import ticketSeller.TicketSeller;
import customer.Customer;
import define.Define;

public class PriorityQueueBatcher implements Ibatcher{

	@Override
	public void moveCustomerToSellerQueue(int currentPhase, Queue lineQueue,
			ArrayList<TicketSeller> sellers) {
		

		for(int j = 0 ; j < Define.theNumberOfTicketSeller && !lineQueue.isEmpty()  ; j++){
		
			TicketSeller tmpSeller = sellers.get(j);
			
			if(tmpSeller.sellerQueue.isEmpty()){
			
				int previousMaxCustomerWaitingTime = -1;
				int previousMaxWaitingCustomerPos = -1;
				
									
				for(int i = 0 ; i < lineQueue.getSize() && !lineQueue.isEmpty() ; i++ ){
					
					int customerWaitingTime = lineQueue.getCustomer(i).getCustomerTimeStatus(Define.ticketWaitingTime);	
					
					if( previousMaxCustomerWaitingTime < customerWaitingTime ){
						previousMaxCustomerWaitingTime = customerWaitingTime;
						previousMaxWaitingCustomerPos = i;
					}
					
					else
						continue;
					
				}
				
				
				Customer maxWaitingCustomer = lineQueue.queue.remove(previousMaxWaitingCustomerPos);
				maxWaitingCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, currentPhase);
				tmpSeller.sellerQueue.enqueue(maxWaitingCustomer);
			}
		
		}
		
		if ( !lineQueue.isEmpty()){
			lineQueue.increaseCustomerTimeInQueue(Define.ticketWaitingTime);
			lineQueue.increaseCustomerTimeInQueue(Define.totalWaitingTime);
		}
	}
}
