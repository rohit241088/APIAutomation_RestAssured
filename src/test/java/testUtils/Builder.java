package testUtils;
import java.io.File;
import static io.restassured.RestAssured.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.joda.time.LocalDate;
import org.json.simple.JSONObject;
import org.junit.Assert;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import requestPayloads.CreateUser;
import runner.Rough;
import utils.RequestDataBuilder;
public class Builder extends RequestDataBuilder {
	

	RequestSpecBuilder requestBuild=null;
//	static Response response=null;
	RequestSpecification request=null;
	Response response=null;
	OutputStream out=null;
	PrintStream stream=null;
	public Response getResponse() {
		return response;
	}
	
	

	public RequestSpecification getRequest() {
		return request;
	}
	
	
	
	//Method to build request on basis of parameters passed, if query, path,headers,file parameters is not available for request, pass null as value
	public Response callAPI(String resource,Object body,String MethodType,String baseURI, Map<String,String> pathParameter,Map<String,String> queryParameters,Map<String,String>headers,File file) {
		
		
		try {
			if(out==null) {
			out=new FileOutputStream(new File("logging.txt"+System.nanoTime()));
			stream=new PrintStream(out);
		}
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		requestBuild=new RequestSpecBuilder().setBaseUri(baseURI).setContentType("application/json").addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream)).setUrlEncodingEnabled(false);
		request=requestBuild.build();
		if(pathParameter!=null) {
			request=requestBuild.addPathParams(pathParameter).build();
		}
		if(queryParameters!=null) {
			request=requestBuild.addQueryParams(queryParameters).build();
		}
		if(headers!=null) {
			request=requestBuild.addHeaders(headers).build();
		}
		if(file!=null) {
			request=requestBuild.addMultiPart(file).build();
		}
		if(MethodType.equalsIgnoreCase("get")) {
			response=given().log().all().spec(request).when().get(resource);
		}
		if(MethodType.equalsIgnoreCase("post")) {
			response=given().log().all().spec(request).body(body).when().post(resource);
		}
		if(MethodType.equalsIgnoreCase("put")) {
			response=given().log().all().spec(request).body(body).when().put(resource);
		}
		if(MethodType.equalsIgnoreCase("delete")) {
			response=given().log().all().spec(request).body(body).when().delete();
		}
		return response;
	}
	public JsonPath getJson() {
		JsonPath js=new JsonPath(response.asString());
		return js;
		
	}
	public static JSONObject getJsonObject(Class cls,String key1,String key2) throws NoSuchMethodException, SecurityException {
		Field[] fields=RequestDataBuilder.getAllFieldsMembers(CreateUser.getInstance());
		Parameter[]parameters=cls.getMethod("getJsonObject",Class.class, String.class,String.class).getParameters();
		JSONObject json=new JSONObject();
			for(int i=0;i<fields.length;i=i+2) {
				json.put(fields[i].getName(),key1);
				json.put(fields[i+1].getName(),key2);
				
				
				
			}
			return json;
			
	}
	
	public boolean checkIfExistAllValuesInJsonResponse(Response response, List<String> listOfValues) {
		boolean allexist=true;
		Iterator<String>iterator=listOfValues.iterator();
		while(iterator.hasNext()) {
			if(!response.asString().contains(iterator.next())) {
				allexist= false;
			}
		}
		return allexist;
	}
	
	public boolean checkAllzValuesMappingInJson(Response response, Map<Object,Object> valuesMap) {
		boolean allexist=true;
		Iterator<Object>iterator=valuesMap.keySet().iterator();
		JsonPath js=new JsonPath(response.asString());
		while(iterator.hasNext()) {
			String key=iterator.next().toString();
			String value=valuesMap.get(key).toString();
			if(!js.getString(key).toString().equalsIgnoreCase(value));
			allexist=false;
			}
		
		return allexist;
	}
	
	public boolean checkValue(Response response,String pathOf,String expectedValue) {
		JsonPath js=new JsonPath(response.asString());
		if(js.getString(pathOf).toString().equalsIgnoreCase(expectedValue)) 
			return true;
		
		else
			return false;
	}
	
	
	
	public ResponseSpecification responseSpec(int statusCode ) {
		
		ResponseSpecification res=new ResponseSpecBuilder()
		.expectStatusCode(statusCode).setDefaultParser(defaultParser.JSON).expectContentType("application/json")
		.expectStatusLine("HTTP/1.1 200 OK").build();
		
		
		return res;
		
	}
	
	
	
	}