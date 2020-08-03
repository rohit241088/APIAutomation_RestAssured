package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.RequestBuilder;

public class stepDefinitions {

	Response response=null;
	@Given("User is on home page")
	public void user_is_on_home_page() {
	    // Write code here that turns the phrase above into concrete actions
	response=RequestBuilder.buildRequest("search", "get", "https://www.google.co.in", null, null, null, null);
		System.out.println();
	}



	@When("hit api")
	public void hit_api() {
	   System.out.println("see what happened");
	 
	}

}
