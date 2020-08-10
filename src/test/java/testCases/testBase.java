package testCases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import testUtils.Builder;
import utils.TestDataBuilder;

public class testBase {
	private Builder buildRequest=null;
@BeforeTest
public void setUpTest()  {
	try {
		buildRequest=new Builder("");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
	}


	public Builder getBuildRequest() {
	return buildRequest;
}



	@AfterTest
	public void tearDown() {
		try {
			buildRequest.getIn().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
