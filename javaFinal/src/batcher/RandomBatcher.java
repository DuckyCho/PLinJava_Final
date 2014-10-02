package batcher;

import java.util.ArrayList;
import java.util.Random;

import queue.Queue;
import ticketSeller.TicketSeller;
import customer.Customer;
import define.Define;

public class RandomBatcher implements Ibatcher{

	@Override
	public void moveCustomerToSellerQueue(int currentPhase, Queue lineQueue,
			ArrayList<TicketSeller> sellers) {
		
		Customer tmpCustomer;
		TicketSeller tmpSeller;
		Random rd = new Random();
		
		while(!lineQueue.isEmpty()){
			int randomNum = rd.nextInt(Define.theNumberOfTicketSeller);
			
			tmpSeller = sellers.get(randomNum);
			tmpCustomer = lineQueue.dequeue();
			
			if(tmpSeller.sellerQueue.isEmpty())
				tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, currentPhase);
			else{
				Customer last = tmpSeller.sellerQueue.getCustomer(tmpSeller.sellerQueue.getSize()-1);
				int startT = last.getCustomerTimeStatus(Define.ticketOpStartTime) + last.getTicketOpTime();
				tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, startT);
			}
			
			tmpSeller.sellerQueue.enqueue(tmpCustomer);
				
		}
		
		
	}

}
