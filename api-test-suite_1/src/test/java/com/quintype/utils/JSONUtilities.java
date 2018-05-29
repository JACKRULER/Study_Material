package com.quintype.utils;

import com.google.gson.internal.LinkedTreeMap;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class JSONUtilities {

	// This method return the object of json.
	public static JSONObject getJSONFileObject(String jsonFilePath)
	{
		JSONParser jsonParser = new JSONParser();
    	Object object = null;
    	JSONObject jsonObject = null;
		try {
			object = jsonParser.parse(new FileReader(jsonFilePath));
	    	jsonObject = (JSONObject) object;	    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return jsonObject;
	}
	
	//
	public static Object getInnerJSONData(String jsonFilePath, String innerJSONName)
	{
		JSONParser parser = new JSONParser();
		Object innerJSON = null;
    	Object object;
		try {
			object = parser.parse(new FileReader(jsonFilePath));
	    	JSONObject jsonObject = (JSONObject) object;
	    	innerJSON = jsonObject.get(innerJSONName);
	    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return innerJSON;
	}
	public static JSONArray getInnerJSONAtrrayData(JSONObject object, String innerJSONArrayName)
	{
		JSONArray innerArray = null;
	    innerArray = (JSONArray) object.get(innerJSONArrayName);
		return innerArray;
	}
	public static JSONObject objectToJSONObject(Object object){
		Object json = null;
		JSONObject jsonObject = null;
		try {
			json = new JSONTokener(object.toString()).nextValue();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (json instanceof JSONObject) {
			jsonObject = (JSONObject) json;
		}
		return jsonObject;
	}
	public static JSONArray getInnerJSONArrayData(String jsonFilePath, String innerJSONArrayName)
	{
		JSONParser parser = new JSONParser();
		JSONArray innerArray = null;
		try {
	    	Object obj = parser.parse(new FileReader(jsonFilePath));
	    	JSONObject jsonObject = (JSONObject) obj;
	    	innerArray = (JSONArray) jsonObject.get(innerJSONArrayName);
	    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return innerArray;
	}
	public static String getJSONArrayValue(JSONArray jsonArray, int array, String name)
	{
    	JSONObject json = (JSONObject) jsonArray.get(array);
    	return (String) json.get(name);
	}

	public static org.json.JSONArray mapToJSONArray(Map object) throws JSONException
	{
		org.json.JSONObject jsonObj = new org.json.JSONObject(object);
		org.json.JSONArray jsonArr = new org.json.JSONArray("["+jsonObj.toString()+"]");
		return jsonArr;
	}

	public static Map getJSONArrayToMap(JSONArray jsonArray, int array)
	{
    	Map json = (Map) jsonArray.get(array);
    	return json;
	}

	public static String getJSONArrayValue1(ArrayList jsonArray, int array, String name)
	{
		LinkedTreeMap json = (LinkedTreeMap) jsonArray.get(array);
		return (String) json.get(name);
	}
	
	// Below methods are for getting the data from API response
	
	// This method return the inner json from a specified response.
	@SuppressWarnings("rawtypes")
	public static Map getInnerJSON(Map response, String key)
	{
		return ((Map) response.get(key));
	}

	
	public static ArrayList getInnerJSON1(Map response, String key)
	{
		return ((ArrayList) response.get(key));
	}
	
	// This method return the value of a field present in specified json response.
	@SuppressWarnings("rawtypes")
	public static String getValueFromResponse(Map response, String elementName)
	{
		return response.get(elementName).toString().split("\\.")[0];
	}
	
	// This method return the value of a field present inside a array of json on a specific index.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getArrayValue(Map response, String arrayName, int index, String elementName)
	{
		return ((ArrayList<Map>)response.get(arrayName)).get(index).get(elementName).toString().split("\\.")[0];
	}
	public static String getActualArrayValue(Map response, String arrayName, int index, String elementName)
	{
		return ((ArrayList<Map>)response.get(arrayName)).get(index).get(elementName).toString();
	}

	public static Object getArrayValueToMap(Map response, String arrayName, int index, String elementName)
	{
		return ((ArrayList<Map>)response.get(arrayName)).get(index).get(elementName);
	}

	/*public static Object getArrayFromArray(ArrayList<Map> response, String arrayName, int index)
	{
		return response.get(index).get(arrayName);
	}*/
	// This method return the Array from the specified JSON response.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Map> getArray(Map response, String arrayName)
	{
		return ((ArrayList<Map>)response.get(arrayName));
	}
	
	//This method returns values of an element in Json
	@SuppressWarnings("rawtypes")
	public static String getJsonArrayValue1(JSONObject response,String elementName,int index)
	{
		return (String) ((ArrayList)response.get(elementName)).get(index);
	}


}
