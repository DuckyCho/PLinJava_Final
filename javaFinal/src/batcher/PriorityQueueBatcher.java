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
		
			ArrayList<Integer> sellersWaitingTime = this.getSellersWaitingTime(currentPhase,sellers);
			
			for(int p = 0 ; p < sellersWaitingTime.size() ; p++){
				int minPos = 0;
				
				for(int i = 0 ; i < sellersWaitingTime.size(); i++){
					minPos = sellersWaitingTime.get(minPos) < sellersWaitingTime.get(i) ? minPos : i;			
				}
				
				int previousCusTrainWait = Define.infinity;
				int valuePersonNum = 0;
				
				for(int j = 0  ; j < lineQueue.getSize() ; j++){
					Customer tmpCus = lineQueue.getCustomer(j);
					int tmpCusTrainWait =  Define.trainInterval - ( ( currentPhase + sellersWaitingTime.get(minPos) + tmpCus.getTicketOpTime() ) % Define.trainInterval );
					previousCusTrainWait = previousCusTrainWait+sellersWaitingTime.get(minPos) > tmpCusTrainWait+sellersWaitingTime.get(minPos) ? tmpCusTrainWait : previousCusTrainWait;
					valuePersonNum = previousCusTrainWait+sellersWaitingTime.get(minPos) > tmpCusTrainWait+sellersWaitingTime.get(minPos) ? j : valuePersonNum;
				}
				
				if( !lineQueue.queue.isEmpty() ){
					Customer tmpCustomer = lineQueue.queue.remove(valuePersonNum);
					TicketSeller tmpSeller = sellers.get(minPos);
					
					if(tmpSeller.sellerQueue.isEmpty())
						tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, currentPhase);
					else{
						Customer last = tmpSeller.sellerQueue.getCustomer(tmpSeller.sellerQueue.getSize()-1);
						int startT = last.getCustomerTimeStatus(Define.ticketOpStartTime) + last.getTicketOpTime();
						tmpCustomer.setCustomerTimeStatus(Define.ticketOpStartTime, startT);
					}
					
					tmpSeller.sellerQueue.enqueue(tmpCustomer);
					sellersWaitingTime.set(minPos, Define.infinity);
				}
		}
	}
	
	
	
	public ArrayList<Integer> getSellersWaitingTime(int currentPhase, ArrayList<TicketSeller> sellers){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i = 0 ; i < Define.theNumberOfTicketSeller ; i++){
			TicketSeller tmpSeller = sellers.get(i);
			if(tmpSeller.sellerQueue.isEmpty()){
				result.add(0);
			}
			else{
				int lastIndex = tmpSeller.sellerQueue.getSize()-1;
				Customer tmpCus = tmpSeller.sellerQueue.getCustomer(lastIndex);
				int waitingTime = tmpCus.getCustomerTimeStatus(Define.ticketOpStartTime) + tmpCus.getTicketOpTime() - currentPhase;
				result.add(waitingTime);
			}
		}
		
		return result;
	}

}
