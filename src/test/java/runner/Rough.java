package runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
import payloads.CreateUser;
import utils.TestDataBuilder;

import static io.restassured.RestAssured.*;

import io.restassured.filter.OrderedFilter;
import  io.restassured.filter.OrderedFilter.*;

public class Rough {
	
	
	  public static void main(String[] args) throws IOException { // TODO Auto-generated method stub
		//  Response resopnse;
	 //Method to build request on basis of parameters passed, if query, path,headers,file parameters is not available for request, pass null as value
		//  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		//  Cookie someCookie = new Cookie.Builder("some_cookie", "some_value").setSecured(true).setComment("some comment").build();
		//  given().baseUri("https://reqres.in").request(Method.GET,"/search").then();
	
		CreateUser classObject=new CreateUser("F:\\workbase\\APIAutomation_RestAssured\\src\\test\\java\\resources\\TestData.xlsx");
	classObject.setName();
	classObject.setJob();
	  
	  
	  }
	
}
