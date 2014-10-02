package fileIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import customer.Customer;
import customerList.EndCustomerList;
import define.Define;

public class ResultFileWriter {
	private FileWriter fw;
	private BufferedWriter bw;
	private static ResultFileWriter instance = new ResultFileWriter(Define.resultFile);
	
	
	private ResultFileWriter(String fileName){
		try {
			this.fw = new FileWriter(fileName);
		} catch (Exception e) {
			System.out.println(Define.fileReadErrorMessage);
		}
		
		this.bw = new BufferedWriter(fw);	
	}
	
	
	
	public static ResultFileWriter getInstance(){
		return instance;
	}
	
	
	
	public void writeFile() {
		StringBuilder newLine = new StringBuilder();
		EndCustomerList endCustomer = EndCustomerList.getInstance();
		StringBuilder index = new StringBuilder();
		
		index.append("Id");
		index.append(Define.comma);
		index.append("Name");
		index.append(Define.comma);
		index.append("StartTime");
		index.append(Define.comma);
		index.append("TicketOpTime");
		index.append(Define.comma);
		index.append("StartStation");
		index.append(Define.comma);
		index.append("EndStation");
		index.append(Define.comma);
		index.append("ticketWaitingTime");
		index.append(Define.comma);
		index.append("trainWaitingTime");
		index.append(Define.comma);
		index.append("totalWaitingTime");
		index.append(Define.comma);
		index.append("totalTravelTime");
		index.append(Define.comma);
		index.append("endTime");
		index.append(Define.newline);
		
		try {
			this.bw.write(index.toString());
			this.bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while( endCustomer.getEndPeopleCount() != 0 ){
			Customer tmp = endCustomer.dequeueCustomer();
			
			newLine.append(tmp.getId());
			newLine.append(Define.comma);
			newLine.append(tmp.getName());
			newLine.append(Define.comma);
			newLine.append(tmp.getStartTime());
			newLine.append(Define.comma);
			newLine.append(tmp.getTicketOpTime());
			newLine.append(Define.comma);
			newLine.append(tmp.getStartStation());
			newLine.append(Define.comma);
			newLine.append(tmp.getEndStation());
			newLine.append(Define.comma);
			newLine.append(tmp.getCustomerTimeStatus(Define.ticketWaitingTime));
			newLine.append(Define.comma);
			newLine.append(tmp.getCustomerTimeStatus(Define.trainWaitingTime));
			newLine.append(Define.comma);
			newLine.append(tmp.getCustomerTimeStatus(Define.totalWaitingTime));
			newLine.append(Define.comma);
			newLine.append(tmp.getCustomerTimeStatus(Define.totalTravelTime));
			newLine.append(Define.comma);
			newLine.append(tmp.getCustomerTimeStatus(Define.endTime));
			newLine.append(Define.newline);
			try {
				this.bw.write(newLine.toString());
				this.bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			newLine = new StringBuilder();
			
		}
		
		
	}
}
