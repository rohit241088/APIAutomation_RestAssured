package utils;
import java.io.File;
import java.util.Map;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
public class RequestBuilder {
	static RequestSpecBuilder requestBuild=null;
	static Response response=null;
	static RequestSpecification request=null;
	
	public static RequestSpecification getRequest() {
		return request;
	}
	
	//Method to build request on basis of parameters passed, if query, path,headers,file parameters is not available for request, pass null as value
	public static Response buildRequest(String resource,String requestType,String baseURI, Map<String,String> pathParameter,Map<String,String> queryParameters,Map<String,String>headers,File file) {
		
		
		requestBuild=new RequestSpecBuilder().setBaseUri(baseURI);
		if(pathParameter!=null) {
			requestBuild.addPathParams(pathParameter).log(LogDetail.ALL);
		}
		if(queryParameters!=null) {
			requestBuild.addQueryParams(queryParameters);
		}
		if(headers!=null) {
			requestBuild.addHeaders(headers);
		}
		if(file!=null) {
			requestBuild.addMultiPart(file);
		}
		
			if(requestType.trim().equalsIgnoreCase("Get")) {
				request=given().spec(requestBuild.build()).log().all();
					response=request.when().get(resource);
			}
			if(requestType.trim().equalsIgnoreCase("delete")) {
				request=given().spec(requestBuild.build()).log().all();
				response=request.when().delete(resource);			}
			if(requestType.trim().equalsIgnoreCase("post")) {
				request=given().spec(requestBuild.build()).log().all();
				response=request.when().post(resource);			}
			if(requestType.trim().equalsIgnoreCase("put")) {
			request=requestBuild.build();
			response=request.when().put(resource);
			}
		return response;
	}
	
	}