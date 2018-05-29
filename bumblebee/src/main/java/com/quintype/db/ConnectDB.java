package com.quintype.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.quintype.api.PublisherAPI;
import com.quintype.util.AssociatePropertyFile;


public class ConnectDB {
	public static Connection connection = null;
    public static Statement statement = null;	
	
	public static void establishDBConnection()
	{
		Properties dbPropertyFile = AssociatePropertyFile.associatePropertyFile("dbPath", true);
		
		String hostName = dbPropertyFile.getProperty("Host_Name");
	    int portNo = Integer.parseInt(dbPropertyFile.getProperty("Port_No"));
	    String dbName = dbPropertyFile.getProperty("DB_Name");
	    String dbUsername = dbPropertyFile.getProperty("DB_Username");
	    String dbPassword = dbPropertyFile.getProperty("DB_Password");
	    try {
		    Class.forName("org.postgresql.Driver");
		    connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":"+portNo+"/"+dbName , dbUsername, dbPassword);	    	
		    connection.setAutoCommit(false);		    
	    }catch (Exception e) 
		{
	    	e.printStackTrace();
	        System.exit(1);
	    }
	}
	public static void createEntitiesType()
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String publisherID = Integer.toString(publisherAPI.getPublisherID());
	    try {
			statement = connection.createStatement();
			String sql = "INSERT INTO entity_type (name, template, publisher_id) VALUES ('person1', '{\"type\":\"person1\",\"properties\":[{\"type\":\"string\",\"display\":\"Name\",\"key\":\"name\"},{\"type\":\"string\",\"display\":\"Email\",\"key\":\"email\"},{\"type\":\"array\",\"display\":\"Photo\",\"key\":\"photo\",\"array-element-type\":\"image\"}]}', "+publisherID+" ), ('company', '{\"type\":\"company\",\"properties\":[{\"type\":\"string\",\"display\":\"Name\",\"key\":\"name\"},{\"type\":\"string\",\"display\":\"Year of Incorporation\",\"key\":\"company-incorporation-year\"},{\"type\":\"entity\",\"display\":\"Industry\",\"key\":\"company-industry\",\"entity-type\":\"industry\"},{\"type\":\"array\",\"display\":\"Photo\",\"key\":\"photo\",\"array-element-type\":\"image\"},{\"array-element-entity-type\":\"city\",\"type\":\"array\",\"display\":\"Cities\",\"key\":\"company-cities\",\"array-element-type\":\"entity\"}]}', "+publisherID+"), ('city', '{\"type\":\"city\",\"properties\":[{\"type\":\"string\",\"display\":\"City\",\"key\":\"name\"}]}', "+publisherID+" ), ('industry', '{\"type\":\"industry\",\"properties\":[{\"type\":\"string\",\"display\":\"Name\",\"key\":\"name\"}]}', "+publisherID+" );";
			statement.executeUpdate(sql);
			statement.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public static void deleteEntitiesType()
	{
		try {
			statement = connection.createStatement();
		    String sql = "delete from public.entity_type where id > 2";
		    statement.executeUpdate(sql);
		    statement.close();
		    connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public static void deleteEntities()
	{
		try {
			statement = connection.createStatement();
		    String sql = "delete from public.entity where id > 2";
		    statement.executeUpdate(sql);
		    statement.close();
		    connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public static void deleteEntitiesGraph()
	{
		try {
			statement = connection.createStatement();
			String sql = "delete from public.entity_graph";
			statement.executeUpdate(sql);
			statement.close();
			connection.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public static void closeDBConnection()
	{
		try {
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}