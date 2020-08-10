package payloads;

import java.io.IOException;

import utils.TestDataBuilder;

public class CreateUser extends TestDataBuilder {
public CreateUser(String excelLocation) throws IOException {
		super(excelLocation);
		// TODO Auto-generated constructor stub
	}
private String className=CreateUser.class.getSimpleName();
private String name;
private String job;
public String getName() {
	return name;
}
public void setName() {
	this.name=(String) getValueForKey(className, name);
}
public String getJob() {
	return job;
}
public void setJob() {
	this.job=(String) getValueForKey(className, job);
}

}
