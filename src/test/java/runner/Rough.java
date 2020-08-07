package runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import io.restassured.filter.OrderedFilter;
import  io.restassured.filter.OrderedFilter.*;

public class Rough {
	 static FileOutputStream out;
	
	  public static void main(String[] args) { // TODO Auto-generated method stub
		  Response resopnse;
	 //Method to build request on basis of parameters passed, if query, path,headers,file parameters is not available for request, pass null as value
		  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		//  Cookie someCookie = new Cookie.Builder("some_cookie", "some_value").setSecured(true).setComment("some comment").build();
		  given().baseUri("https://reqres.in").request(Method.GET,"/search").then();
		
	}
	
}
