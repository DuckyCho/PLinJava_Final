package station;

import java.util.ArrayList;

import phase.Phase;
import queue.FlushQueue;
import queue.Queue;
import ticketSeller.TicketSeller;
import trainSystem.Train;
import trainSystem.TrainSystem;
import batcher.Ibatcher;
import batcher.PriorityQueueBatcher;
import batcher.RandomBatcher;
import batcher.RoundRobinBatcher;
import customer.Customer;
import define.Define;

public class Station implements Runnable {
	public String name;
	public Queue lineQueue;
	public Ibatcher batcher;
	public ArrayList<TicketSeller> ticketSellers;
	public FlushQueue platformQueue;
	public int totalCustomerInStation;
	
	public Station(String name){
		
		this.name = name;
		
		this.lineQueue = new Queue();
		
		switch(Define.batcherType){
			
			case Define.RoundRobinBatcher : 
				this.batcher = new RoundRobinBatcher();
				break;
							
			case Define.PriorityQueueBatcher : 
				this.batcher = new PriorityQueueBatcher();
				break;
				
			case Define.RandomBatcher : 
				this.batcher = new RandomBatcher();
				break;
			
		}
		
		this.ticketSellers = new ArrayList<TicketSeller>();
		
		for(int i = 0 ; i < Define.theNumberOfTicketSeller; i++){
			ticketSellers.add(new TicketSeller(i));
		}
		
		this.platformQueue = new FlushQueue();
		
		this.totalCustomerInStation = 0;
	}
	
	
	public void operate(int currentPhase){
		this.batcher.moveCustomerToSellerQueue(currentPhase ,this.lineQueue, this.ticketSellers);
		for(int i = 0  ; i < this.ticketSellers.size() ; i ++){
			this.ticketSellers.get(i).operate(this.platformQueue, currentPhase);
		}
		this.moveCustomerToTrain(currentPhase,this.platformQueue);
	}
	
	public void moveCustomerToTrain(int currentPhase, FlushQueue platformQueue){
		
		if(currentPhase % Define.trainInterval == 0 ){
			ArrayList<Customer> tmpClist = platformQueue.dequeueAll();
			TrainSystem ts = TrainSystem.getInstance();
			
			for(int i = 0 ; i < tmpClist.size(); i ++){
				Train newTrain = new Train(currentPhase,tmpClist.get(i));
				this.totalCustomerInStation--;
				ts.addTrainToTrainList(newTrain);
			}
		}
		else{
			platformQueue.increaseCustomerTimeInQueue(Define.totalWaitingTime);
			platformQueue.increaseCustomerTimeInQueue(Define.trainWaitingTime);
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder stationInfo = new StringBuilder();
		
		stationInfo.append(String.format("%12s",(this.name+"역")));
		stationInfo.append(Define.tab);
		stationInfo.append("LineQueue 대기 인원 : ");
		stationInfo.append(lineQueue.getSize());
		stationInfo.append(Define.tab);
		
		for(int i = 0 ; i < this.ticketSellers.size(); i++){
			int sellerWaiting = ticketSellers.get(i).sellerQueue.getSize();
			stationInfo.append("Seller");
			stationInfo.append(i);
			stationInfo.append(" 대기 인원 : ");
			stationInfo.append(sellerWaiting);
			stationInfo.append(Define.tab);	
		}
		
		stationInfo.append("Platform 대기 인원 : ");
		stationInfo.append(platformQueue.getSize());
		stationInfo.append(Define.tab);
		
		stationInfo.append("총 대기 인원 : ");
		stationInfo.append(this.totalCustomerInStation);
		stationInfo.append(Define.tab);
		
		return stationInfo.toString();
	}


	@Override
	public void run() {
		Phase phase = Phase.getInstance();
		this.operate(phase.getPhase());
		
	}
}
