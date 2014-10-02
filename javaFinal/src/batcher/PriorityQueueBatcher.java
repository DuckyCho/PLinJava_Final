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
			
				int previousMaxCustomerWaitingTime = 0;
				int previousMaxWaitingCustomerPos = 0;
				int previousTrainWaitingTime = Define.infinity;
									
				for(int i = 0 ; i < lineQueue.getSize() && !lineQueue.isEmpty() ; i++ ){
					
					Customer tmpCustomer = lineQueue.getCustomer(i);
					int customerWaitingTime = tmpCustomer.getCustomerTimeStatus(Define.ticketWaitingTime);	
					int trainWaitingTime = Define.trainInterval - ( ( currentPhase + tmpCustomer.getTicketOpTime() ) % Define.trainInterval ); 
							
					if( (1/ (previousMaxCustomerWaitingTime+1) )* ( previousMaxCustomerWaitingTime+previousTrainWaitingTime )  > (1/ (customerWaitingTime+1))*  (customerWaitingTime + trainWaitingTime) ){
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
			lineQueue.increaseCustomerTimeInQueue(Define.ticketWaitingTime,Define.first);
			lineQueue.increaseCustomerTimeInQueue(Define.totalWaitingTime,Define.first);
		}
	}
}
