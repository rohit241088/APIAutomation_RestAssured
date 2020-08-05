package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Builder;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class StepDefinitions {

	
	Builder object=new Builder();
	@Given("User is on home page")
	public void user_is_on_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		
	object.callAPI("", null, "get", "https://www.google.com", null, null, null, null);
		
	}



    @When("^hit  search \"([^\"]*)\" api with \"([^\"]*)\" and parametertype \"([^\"]*)\" and parameterValue \"([^\"]*)\"$")
	public void hit_api(String args,String args2,String paramterType,String paramterValue) {
    	Map<String, String> queryParam=new HashMap<String,String>();
    	queryParam.put(paramterType, paramterValue);
		 object.callAPI(args2, null,args, "https://www.google.com",null, queryParam, null, null);
		// object.responseSpec(object.getResponse(), 200, 1000l, null, null);
	 
	}
    
    @Then("^Response should contains \"([^\"]*)\"$")
    public void response_should_contains_something(String strArg1) throws Throwable {
    boolean value=object.getResponse().asString().split(strArg1).length!=0;
    assertTrue(value);
    }

}
