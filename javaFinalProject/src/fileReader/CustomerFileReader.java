package fileReader;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import define.Define;

public class CustomerFileReader {
	private FileReader fis;
	private BufferedReader br;
	private static CustomerFileReader instance = new CustomerFileReader(Define.customerInfoFile);
	
	
	private CustomerFileReader(String fileName){
		try {
			this.fis = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.out.println(Define.fileReadErrorMessage);
		}
		
		this.br = new BufferedReader(fis);	
	}
	
	
	
	public static CustomerFileReader getInstance(){
		return instance;
	}
	
	
	
	public ArrayList<String[]> readFile() throws IOException{
		String line;
		ArrayList<String[]> userInfo = new ArrayList<String[]>();
		
		//delete index Row
		br.readLine();
		
		while( (line = br.readLine()) != null){
			
			if( !line.split(",")[Define.customerId].equals(Define.emptyString))
				userInfo.add(line.split(","));
			
		}

		return userInfo;
		
	}
}
