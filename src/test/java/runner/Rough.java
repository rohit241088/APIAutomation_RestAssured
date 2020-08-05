package runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;


public class Rough {
	 static FileOutputStream out;
	
	  public static void main(String[] args) { // TODO Auto-generated method stub
	 //Method to build request on basis of parameters passed, if query, path,headers,file parameters is not available for request, pass null as value
		try {
			 out=new FileOutputStream(new File("logging.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintStream stream=new PrintStream(out);
		
		RequestSpecBuilder requestBuild=new RequestSpecBuilder().setContentType("application/json").setBaseUri("https://www.google.co.in").addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream));//.setUrlEncodingEnabled(false);
		 RequestSpecification request=requestBuild.build();
	given().spec(request).log().all().get().then().log().all();
			
		
	}
	
}
