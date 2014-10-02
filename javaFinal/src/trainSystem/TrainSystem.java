package trainSystem;


import java.util.ArrayList;
import customer.Customer;
import customerList.EndCustomerList;
import define.Define;


public class TrainSystem {
	
	public ArrayList<Train> TrainList;
	private static TrainSystem instance = new TrainSystem(); 
	
	private TrainSystem(){
		this.TrainList = new ArrayList<Train>();
	}
	
	public static TrainSystem getInstance(){
		return TrainSystem.instance;
	}
	
	public void addTrainToTrainList(Train t){
		this.TrainList.add(t);
	}
	
	public void arrivalCheck(int currentPhase){
		
		for(int  i = 0 ; i < this.TrainList.size() ; i++){
			Train tmpTrain = this.TrainList.get(i);
			
			if(tmpTrain.endTime == currentPhase){
				Train tmpTrain2 = this.TrainList.remove(i);
				i--;
				ArrayList<Customer> tmpClist = tmpTrain2.customerList.dequeueAll();
				EndCustomerList ecl = EndCustomerList.getInstance();
				
				
				for(int k = 0 ; k < tmpClist.size() ; k++){
					Customer end = tmpClist.remove(k);
					end.setCustomerTimeStatus(Define.endTime, currentPhase);
					end.setCustomerTimeStatus(Define.totalTravelTime, currentPhase - end.getStartTime());
					ecl.enqueueCustomer(end);
				
				}
			}
			
			
		}
	}
	

}
