package utils;

import java.io.IOException;
import java.util.Map;

public class TestDataBuilder extends excelUtils {

	
	
	public TestDataBuilder(String excelLocation) throws IOException {
		super(excelLocation);
		// TODO Auto-generated constructor stub
	}
	
	public Object getValueForKey(String  sheetName,String key) {
		Object object=null;
		setSheet(sheetName);
		Map<Object,Object>keyValue=keyValueMap();
		if(keyValue.containsKey(key)){
			object= keyValueMap().get(key);
		}
		return object;
		
	}
	
	
	

}
