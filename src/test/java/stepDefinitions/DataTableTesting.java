package stepDefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import utils.Builder;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)

public class DataTableTesting extends Builder {



	  @Given("^I am on reqress website$")
	    public void i_am_on_reqres_website() throws Throwable {
   
    	
    }

	  @When("^I hit api \"([^\"]*)\" with user as parameter (.+)$")
	    public void i_hit_api_something_with_user_as_parameter(String parameter, String strArg1) throws Throwable {
    	Map<String,String>query=new HashMap<>();
		query.put("id", strArg1);
    	callAPI(parameter, null, "get", "https://reqres.in/", null, query, null, null);
    	
    }


	  @Then("^I should get response value as (.+)$")
	    public void i_should_get_response_value_as(String parameteraccepted) throws Throwable {
    	checkValue(getResponse(),"data.first_name", parameteraccepted);
    }

}

