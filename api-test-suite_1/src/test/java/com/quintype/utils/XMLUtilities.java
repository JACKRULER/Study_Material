package com.quintype.utils;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;

public class XMLUtilities {
	
//	public static void getData()
//	{
//		String endPoint= "http://platform.staging.quintype.io/stories.rss?tag=something";
//    //    registerParser("application/xml", Parser.XML);
//
//		XmlPath response =  given().expect().statusCode(200).log().everything()
//                .when().get(endPoint).xmlPath();
//
//        // This will return all the stories in the RSS feed - first 7 nodes are RSS feed's metadata
//        System.out.println("ASD");
//
//       System.out.println("SSS =>"+response.get().get("title"));
// //       return 
////        System.out.println("ZZZ => "+response.response().xmlPath().get().children().get(0).children());
//	}
	
	public static ExtractableResponse<Response> getData() 
	{
		String endPoint= "http://platform.staging.quintype.io/stories.rss?tag=something";
		ExtractableResponse<Response> response = given().header("Content-Type", "text/xml")
				.accept("application/json")
				.when().get(endPoint)
				.then().statusCode(200).extract();
		
		System.out.println("XML: "+response.asString());
//		System.out.println("Response => "+response);
		return response;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Start !!");
		ExtractableResponse<Response> response ;
        	response = getData();
        	getValue(response);
		        System.out.println("End !!");
	}
	
	public static void getValue(ExtractableResponse<Response> response)
	{
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder()
					.parse(new InputSource(new StringReader(response.asString())));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeList errNodes = doc.getElementsByTagName("channel");
		if (errNodes.getLength() > 0) {
		    Element err = (Element)errNodes.item(0);
		    System.out.println(err.getElementsByTagName("title")
		                          .item(0)
		                          .getTextContent());
		} else { 
		        // success
		}
		
	}

}
