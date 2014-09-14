package fileReader;

import java.io.IOException;

import customer.CustomerList;

public class CustomerFileReaderTest extends junit.framework.TestCase{

	public static void testCreate() throws IOException{
		CustomerFileReader cfr = CustomerFileReader.getInstance();
		CustomerList cl = CustomerList.getInstance();
		
		new MakeCustomerList(cfr, cl);
		System.out.println(cl);
	
	}
}
