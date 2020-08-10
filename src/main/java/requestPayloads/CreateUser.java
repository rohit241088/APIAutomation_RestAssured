package payloads;

import java.io.IOException;
import java.util.Map;

public class CreateUser extends utils.TestDataBuilder {
public CreateUser(String excelLocation) throws IOException {
		super(excelLocation);
		// TODO Auto-generated constructor stub
	}
private Map<Object,Object>keyValues=returnKeyValueData(CreateUser.class.getSimpleName());
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

}
