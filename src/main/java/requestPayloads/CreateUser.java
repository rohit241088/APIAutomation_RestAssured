package requestPayloads;

import java.util.Map;

import utils.RequestDataBuilder;

public class CreateUser extends RequestBodyBuilder  {

private Map<Object,Object>keyValues=RequestDataBuilder.returnKeyValueData(CreateUser.class.getSimpleName());
private String name;
private String job;
public String getName() {
	return name;
}


public void setName() {
	this.name=keyValues.get("name").toString();
	
}
public String getJob() {
	return job;
}
public void setJob() {
	this.job=keyValues.get("job").toString();
	
}


@Override
public CreateUser buildRequest() {
	// TODO Auto-generated method stub
	this.setName();
	this.setJob();
	return this;
}







}
