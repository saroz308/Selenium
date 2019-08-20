package demo;

import org.testng.annotations.Test;



public class DemoDataProvider {

	
	@Test(dataProvider = "getData", dataProviderClass = in.amazon.tests.TestDataReader.class)
	public void printTestData(String emailId, String password) {
		System.out.println("Email Id - " + emailId);
		System.out.println("Password - " + password);
	}
}
