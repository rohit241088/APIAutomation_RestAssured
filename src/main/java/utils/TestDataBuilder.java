package utils;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

public class TestDataBuilder extends excelUtils {

	private Map<Object,Object>keyValue=null;
	
	public TestDataBuilder(String excelLocation) throws IOException {
		super(excelLocation);
		// TODO Auto-generated constructor stub
	}
	
	
	public Map<Object,Object> returnKeyValueData(String sheetName){
		if(getSheet()==null) {
			setSheet(sheetName);
			
		}
		if(!getSheet().getSheetName().equalsIgnoreCase(sheetName)) {
			setSheet(sheetName);
		}
		if(keyValue==null) {
			keyValue=keyValueMap();
		}
		return keyValue;
		
	}
	
	public Object getValueForKey(String  sheetName,String key) {
		Object object=null;
		Map<Object,Object>map=this.returnKeyValueData(sheetName);
		if(map.containsKey(key))
		{
			object=map.get(key);
		}
		return object;
		
	}
	
	
	

}
