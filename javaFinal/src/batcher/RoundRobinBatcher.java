package batcher;

import java.util.ArrayList;

import queue.Queue;
import ticketSeller.TicketSeller;
import customer.Customer;
import define.Define;

public class RoundRobinBatcher implements Ibatcher {

	
	
	@Override
	public void moveCustomerToSellerQueue(int currentPhase, Queue lineQueue,
			ArrayList<TicketSeller> sellers) {
		
		if( !lineQueue.isEmpty()){
			Customer tmpCustomer;
			
			for(int i = 0 ; i < Define.theNumberOfTicketSeller  && !lineQueue.isEmpty() ; i++ ){
				
				TicketSeller tmpSeller = sellers.get(i);
				
				if( currentPhase % Define.trainInterval == 0 && !tmpSeller.sellerQueue.isEmpty() ){
					
					tmpCustomer = tmpSeller.sellerQueue.getCustomer(Define.first);
					int ticketingDuration;
					ticketingDuration = currentPhase - tmpCustomer.getCustomerTimeStatus(Define.ticketOpStartTime);
					int tmpCustomerTicketOptime = tmpCustomer.getTicketOpTime();
					
					if(ticketingDuration >= Define.trainInterval &&
						tmpCustomerTicketOptime - ticketingDuration != 0 ){
						
						tmpCustomer = tmpSeller.sellerQueue.dequeue();
						tmpCustomer.setTicketOpTime(tmpCustomerTicketOptime - ticketingDuration);
						lineQueue.enqueue(tmpCustomer);
						
						tmpCustomer = lineQueue.dequeue();
						tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, currentPhase);
						tmpSeller.sellerQueue.enqueue(tmpCustomer);
						
					}
					
				}
				
				else if( tmpSeller.sellerQueue.isEmpty() ){
					tmpCustomer = lineQueue.dequeue();
					tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, currentPhase);
					tmpSeller.sellerQueue.enqueue(tmpCustomer);
				}
				
			}
			
			if ( !lineQueue.isEmpty()){
				lineQueue.increaseCustomerTimeInQueue(Define.ticketWaitingTime);
				lineQueue.increaseCustomerTimeInQueue(Define.totalWaitingTime);
			}
			
			
		}
		
		
	}
		
	
}
