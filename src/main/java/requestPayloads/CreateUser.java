package requestPayloads;

import java.util.Map;

import org.testng.annotations.DataProvider;

import utils.RequestDataBuilder;

public class CreateUser  {

//private Map<Object,Object>keyValues=RequestDataBuilder.returnKeyValueData(CreateUser.class.getSimpleName());
private String name;
private String job;
public String getName() {
	return name;
}


public void setName() {
	this.name="name";
	
}
public String getJob() {
	return job;
}
public void setJob() {
	this.job="job";
	
}


public static Object getInstance() {
	// TODO Auto-generated method stub
	CreateUser user=new CreateUser();
	
	return user;
}


@DataProvider(name="returnRequestData")
public static Object[][] returnRequestData(){
	return RequestDataBuilder.requestData(new CreateUser());
}



}
