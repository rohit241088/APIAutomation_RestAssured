package utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.simple.JSONObject;

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
		runPreCheck(sheetName);
		if(keyValue==null) {
		//	keyValue=testData.keyValueMap();
		}
		return keyValue;
		
	}
	
	
	
	public static Object getValueForKey(String  sheetName,String key) {
		Object object=null;
		Map<Object,Object>map=returnKeyValueData(sheetName);
		if(map.containsKey(key))
		{
			object=map.get(key);
		}
		return object;
		
	}
	
	private static void runPreCheck(String sheetName) {
		if(testData==null) {
			setTestData();
		}
		if(testData.getSheet()==null) {
			testData.setSheet(sheetName);
			
		}
		if(!testData.getSheet().getSheetName().equalsIgnoreCase(sheetName)) {
			testData.setSheet(sheetName);
		}
	}
	
	private static int getAllFields(Object object) {
		int totalFields=0;
		if(object.getClass().getDeclaredFields()!=null) {
			System.out.println(object.getClass().getSimpleName());
			Field[] fields=object.getClass().getDeclaredFields();
			for(int i=0;i<fields.length;i++) {
				totalFields++;
			}
		}
		return totalFields;
		
	}
	public static Field[] getAllFieldsMembers(Object object) {
	return object.getClass().getDeclaredFields();
		
	}
	public static Parameter[] getAllParameter(Object object,String methodName) throws NoSuchMethodException, SecurityException {
		return object.getClass().getDeclaredMethod(methodName, object.getClass()).getParameters();
			
		}
	
	public static Method[] getAllMethods(Object object) {
		return object.getClass().getDeclaredMethods();
			
		}
	
	
	public static Object[][] requestData(Object object){
		String className=object.getClass().getSimpleName();
		int setColumn=-1;
		List<Object>KeysList=new ArrayList<>();
		List<Object>ValuesList=new ArrayList<>();
		int runMode=-1;
		Object[][] dataArray=null;
		int totalFields=getAllFields(object);
		runPreCheck(className);
		Sheet sheet=testData.getSheet(); 
		Map<String,Integer> columnHeader=testData.getHeaderMap(testData.getSheet());
		Iterator<String>iterator=columnHeader.keySet().iterator();
		while(iterator.hasNext()) {
		String headerValue=iterator.next();
		if(headerValue.equalsIgnoreCase("Keys")) {
			setColumn=columnHeader.get(headerValue);
		}
		if(headerValue.contains("Runmode")) {
			runMode=columnHeader.get(headerValue);
		}
		if(setColumn!=-1&&runMode!=-1)
			break;
		}
		if((setColumn!=-1)&&(runMode!=-1)) {
			for(int i=1;i<=sheet.getLastRowNum();i++) {
				if(String.valueOf(testData.returnCellValue(i, setColumn)).contains("Set")) {
					if(String.valueOf(testData.returnCellValue(i, runMode)).equalsIgnoreCase("true")||
							Boolean.valueOf(String.valueOf(testData.returnCellValue(i, runMode)).equalsIgnoreCase("true"))) {
						
						int totalCounter=i+totalFields;
						
						for(int k=i+1;k<=totalCounter;k++) {
						
							KeysList.add(testData.returnCellValue(k, columnHeader.get("Keys")));
							ValuesList.add(testData.returnCellValue(k, columnHeader.get("Values")));
						i=k;
							
					}
						
				}
				
			}
			
		}
			int totalRows=(KeysList.size())/totalFields;
			Object[] valuesArray=ValuesList.toArray();
			int totalColumns=totalFields;
			int counter=0;
			dataArray=new Object[totalRows][totalColumns];
			for(int i=0;i<totalRows;i++) {
				for(int j=0;j<totalColumns;j++) {
					dataArray[i][j]=valuesArray[counter];
					counter++;
				}
			}
			
			
		}
		return dataArray;
	
	}
	private static Map<Object,Object> keyValueMap(String sheetName){
		int KeysColumn=-1;
		int valueColumn=-1;
		Map<Object,Object>map=new HashMap<>();
		runPreCheck(sheetName);
		Sheet testDataSheet=testData.getSheet();
			for(int i=0;i<testDataSheet.getRow(0).getLastCellNum();i++) {
				if(testDataSheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase("Keys")) {
					KeysColumn=i;
				}
				if(testDataSheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase("Values")) {
					valueColumn=i;
				}
				
			}
			for(int j=1;j<=testDataSheet.getLastRowNum();j++) {
				map.put(testData.returnCellValue(j, KeysColumn), testData.returnCellValue(j, valueColumn));
			}
		
		return map;
	}
	
	

}
