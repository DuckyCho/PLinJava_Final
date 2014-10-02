package batcher;

import java.util.ArrayList;

import queue.Queue;
import ticketSeller.TicketSeller;

// 각 역의 LineQueue에서 ticketSellerQueue로 Customer를 옮기는 역할
// 정해진 Logic에 따라 customer를 ticketSellerQueue로 Customer로 옮김
public interface Ibatcher {
	
	public abstract void moveCustomerToSellerQueue(int currentPhase, Queue LineQueue,
			ArrayList<TicketSeller> sellers); 
}
