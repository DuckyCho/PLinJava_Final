package station;

import java.util.ArrayList;
import java.util.Iterator;

import phase.Phase;
import define.Define;

public class StationList  {
	private ArrayList<Station> stationList;
	private static StationList instance = new StationList();
	public int totalCustomer;
	
	private StationList(){
		this.stationList = new ArrayList<Station>();
		this.totalCustomer = 0;
	}
	
	public static StationList getInstance(){
		return StationList.instance;
	}
	
	public Station getStation(int i){
		return this.stationList.get(i);
	}
	
	public void setStation(){
		Iterator<String> stationNameList = Define.station.keySet().iterator();
		int i = 0;
		
		while( i < Define.station.size()){
			this.stationList.add(null);
			i++;
		}
		
		while(stationNameList.hasNext()){
		
			String stationName = stationNameList.next();
			
			int order = Define.station.get(stationName);
			
			Station newStation = new Station(stationName);
			
			this.stationList.set( order, newStation);
			
			
		}
		
		
	}
	
	public void operate(int currentPhase) {
		ArrayList<Thread> threadArr = new ArrayList<Thread>();
		for(int i = 0 ; i < this.stationList.size() ; i ++){
			Thread t =  new Thread(this.stationList.get(i));
			t.run();
			threadArr.add(t);
		}
		
		for(int i = 0 ; i < threadArr.size(); i++){
			try {
				threadArr.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printStationInfo(){
		Phase p = Phase.getInstance();
		System.out.println();
		System.out.println("CurrentPhase : "+ p.getPhase());
		for(int i = 0 ; i < this.stationList.size(); i++){
			System.out.println(this.stationList.get(i));
		}
	}
}
