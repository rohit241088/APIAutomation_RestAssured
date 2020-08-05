package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import utils.Builder;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitions {

	
	Builder object=new Builder();
	@Given("User is on home page")
	public void user_is_on_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		
		
	}



	@When("hit  search {string} api with {string}")
	public void hit_search_api_with(String string, String string2) {
    	
		 object.callAPI(string2, null,string, "https://reqres.in",null, null, null, null);
		// object.responseSpec(object.getResponse(), 200, 1000l, null, null);
	 
	}
    
    @Then("^Response should contains \"([^\"]*)\" and \"([^\"]*)\"$")
    public void response_should_contains_something(String strArg1,String arg2) throws Throwable {
    	object.getResponse().then().spec(object.responseSpec(200)).extract().jsonPath();
    	JsonPath json=object.getJson();
    boolean isEqual=json.getInt("data.size()")==6;
    	assertTrue(isEqual);
    	
    //assertTrue(object.getResponse().asString().contains(strArg1)&&object.getResponse().asString().contains(arg2));
    }

}
