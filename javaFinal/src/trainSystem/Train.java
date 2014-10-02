package trainSystem;

import queue.FlushQueue;
import customer.Customer;
import define.Define;
import dijkstra.Dijkstra;

public class Train {
	public String startStation;
	public String endStation;
	public int startTime;
	public int endTime;
	public FlushQueue customerList;
	
	public Train(int currentPhase,Customer c1){
		
		this.startTime = currentPhase;
		this.customerList =  new FlushQueue();
		this.customerList.enqueue(c1);
		this.startStation = c1.getStartStation();
		this.endStation = c1.getEndStation();
		
		int startStationNum = Define.station.get(startStation);
		int endStationNum = Define.station.get(endStation);
		int duration = Dijkstra.shortest_Path.get(startStationNum).get(endStationNum);
		
		this.endTime = duration + this.startTime;
		
	}
}
