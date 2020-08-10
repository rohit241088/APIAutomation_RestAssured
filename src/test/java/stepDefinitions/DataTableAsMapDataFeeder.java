package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testUtils.Builder;

public class DataTableAsMapDataFeeder extends Builder {




    @Given("^I am on reqres website$")
    public void i_am_on_reqres_website() throws Throwable {
  	
  }

    @When("^I hit api \"([^\"]*)\" with user as parameter$")
    public void i_hit_api_something_with_user_as_parameter(String strArg1,DataTable table) throws Throwable {
  	Map<String,String>query=new HashMap<>();
  	
  	Map<String,String>dataMap=table.asMap(String.class, String.class);
		/*
		 * Hashtable<String,String> tableJ=table.convert(String.class, false);
		 * 
		 
		 */
  
  	 query.put(("id"), dataMap.get("id"));
  	callAPI(strArg1, null, "get", dataMap.get("baseURI"), null, query, null, null);
  	System.out.println(callAPI(strArg1, null, "get", dataMap.get("baseURI"), null, query, null, null).asString());
  	
  }


    @Then("^I should get response value$")
    public void i_should_get_response_value(DataTable table) throws Throwable {
    	Map<String,String>Map=table.asMap(String.class, String.class);
    	System.out.println(table.isEmpty());
    	System.out.println(Map.toString());
  	assertTrue(checkValue(getResponse(),"data.first_name",Map.get("data.first_name")));
  	assertTrue(checkValue(getResponse(),"data.id",Map.get("data.id")));

  }

}


