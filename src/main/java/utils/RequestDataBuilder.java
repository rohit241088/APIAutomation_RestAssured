package utils;

import java.io.IOException;
import java.util.Map;

public class RequestDataBuilder {

	private static Map<Object,Object>keyValue=null;
private static excelUtils testData=null;
	
	
private static void setTestData() {
	try {
		testData=new excelUtils(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\TestData.xlsx");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public static excelUtils object() {
	return testData;
}
	public static Map<Object,Object> returnKeyValueData(String sheetName){
		if(testData==null) {
			setTestData();
		}
		if(testData.getSheet()==null) {
			testData.setSheet(sheetName);
			
		}
		if(!testData.getSheet().getSheetName().equalsIgnoreCase(sheetName)) {
			testData.setSheet(sheetName);
		}
		if(keyValue==null) {
			keyValue=testData.keyValueMap();
		}
		return keyValue;
		
	}
	
	public Object getValueForKey(String  sheetName,String key) {
		Object object=null;
		Map<Object,Object>map=returnKeyValueData(sheetName);
		if(map.containsKey(key))
		{
			object=map.get(key);
		}
		return object;
		
	}
	
	
	

}
