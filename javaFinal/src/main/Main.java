package main;


import phase.Phase;
import station.Station;
import ticketSeller.TicketSeller;
import trainSystem.TrainSystem;
import customerList.EndCustomerList;
import customerList.StartCustomerList;
import dijkstra.Dijkstra;
import fileIO.CustomerFileReader;
import fileIO.ResultFileWriter;

public class Main {

	public static void main(String[] args) {
		
		Dijkstra dij = Dijkstra.getInstance();
		StartCustomerList initialCustomers = StartCustomerList.getInstance();
		EndCustomerList travelEndCustomers = EndCustomerList.getInstance();
		CustomerFileReader cfr = CustomerFileReader.getInstance();
		Phase phase = Phase.getInstance();
		Station station = Station.getInstance();
		TrainSystem trainSystem = TrainSystem.getInstance();
		
	
		dij.DijkstraMain();
		initialCustomers.setStartCustomerList(cfr);
		

		while( travelEndCustomers.getEndPeopleCount() < initialCustomers.getInitialTotalCustomerCount() ){

			int currentPhase = phase.getPhase();
			
			trainSystem.arrivalCheck(currentPhase);
			initialCustomers.moveCustomerToLineQueue(currentPhase,station);
			station.operate(currentPhase);
			
			
			System.out.println(station);
			
			phase.nextPhase();
			
	
		}
		

		ResultFileWriter resultFileCreate = ResultFileWriter.getInstance();
		resultFileCreate.writeFile();
	
		
	}

}
