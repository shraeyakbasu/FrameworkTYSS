package com.RmgYantraFramework.GenericUtils;

import java.sql.SQLException;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class Baseclass 
{
	public DatabaseUtils dbutil=new DatabaseUtils();
	
	
	@BeforeSuite
	public void createdbconn() throws SQLException
	{
	   
		dbutil.Createconnections();
	}
	
	
	@AfterSuite
	public void enddbconn() throws SQLException
	{
		dbutil.Endconnections();
	}
}
