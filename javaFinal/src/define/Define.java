package define;


import java.util.HashMap;


public class Define {
	public static final String fileReadErrorMessage = "File read error!";
	public static final String customerInfoFile = "customer.csv";
	public static final String resultFile = "result.csv";
	public static final String emptyString = "";
	public static final String tab = "\t";
	public static final String comma = ",";
	public static final String newline = "\n";
	public static final int first = 0;
	public static final int trainInterval = 3;
	public static final int infinity = 999999999;
	//Customer
	public static final int id = 0;
	public static final int name = 1;
	public static final int startTime = 2;
	public static final int ticketOpTime = 3;
	public static final int startStation = 4;
	public static final int endStation = 5;
	
	public static final int customerTimeStatusOption = 6; 
	public static final int ticketWaitingTime = 0;
	public static final int trainWaitingTime = 1;
	public static final int totalWaitingTime = 2;
	public static final int totalTravelTime = 3;
	public static final int endTime = 4;
	public static final int ticketOpStartTime = 5;
	
	
	//Station
	public static final HashMap<String,Integer> station;
	static {
		station = new HashMap<String,Integer>();
		
		station.put("Seoul",0);
		station.put("Chuncheon",1);
		station.put("Wonju",2);
		station.put("Gyeongju",3);
		station.put("Daejeon",4);
		station.put("Asan",5);
		station.put("Gwangju",6);
	}
	
	public static final int theNumberOfTicketSeller = 3;
	
	public static final int RoundRobinBatcher = 0;
	public static final int PriorityQueueBatcher = 1;
	public static final int RandomBatcher = 2;
	public static final int FIFOBatcher = 3;
	
	//여기서 정책을 결정할 수 있다.
	public static final int batcherType = Define.RoundRobinBatcher;	
	
	
	
}
