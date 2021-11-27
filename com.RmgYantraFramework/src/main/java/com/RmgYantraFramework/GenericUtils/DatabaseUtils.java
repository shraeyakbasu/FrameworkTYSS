package com.RmgYantraFramework.GenericUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DatabaseUtils 
{
	Connection conn=null;
	ResultSet result=null;
	
	public void Createconnections() throws SQLException
	{
		Driver driver= new Driver();
				DriverManager.registerDriver(driver);
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	}
	
	public ResultSet Executequery(String query) throws SQLException
	{
		result=conn.createStatement().executeQuery(query);
		return result;
	}
	
	public String ExecutequeryandGetResult(String query,int column,String expecteddata) throws SQLException
	{
		Boolean flag=false;
		result=conn.createStatement().executeQuery(query);
		while(result.next())
		{
			if(result.getString(column).equals(expecteddata))
			{
				flag=true;
				break;
			}
		}
		if(flag==true)
		{
			System.out.println(expecteddata +" found ");
			return expecteddata;
		}
		else
		{
			System.out.println(expecteddata +" not found ");
			return expecteddata;
		}
		
	}
	
	
	public void Endconnections() throws SQLException
	{
		conn.close();
	}

}
