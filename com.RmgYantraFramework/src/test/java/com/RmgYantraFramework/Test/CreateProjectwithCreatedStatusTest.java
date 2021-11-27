package com.RmgYantraFramework.Test;

import org.testng.annotations.Test;

import com.RmgYantraFramework.GenericUtils.Baseclass;
import com.RmgYantraFramework.GenericUtils.IEEndpoints;
import com.RmgYantraFramework.GenericUtils.JavaUtils;
import com.RmgYantraFramework.GenericUtils.Querystring;
import com.RmgYantraFramework.PojoClass.CreateProjectwithCreatedStatusPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import org.hamcrest.*;

import static io.restassured.RestAssured.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class CreateProjectwithCreatedStatusTest extends Baseclass
{
	@Test
	public void createprojectwithcreatedstatus() throws SQLException 
	{
		String expectedstatus="created";
		String actualstatus="";
		String expectedmessagename="Successfully Added";
		String expectedprojectname="samsung galaxy "+JavaUtils.getrandomdata();
		baseURI="http://localhost:5555";
		CreateProjectwithCreatedStatusPojo createproj=new CreateProjectwithCreatedStatusPojo("shraeyak basu", expectedprojectname, "created", 7);
	
		Response resp=given()
		.contentType(ContentType.JSON)
		.body(createproj)
		.when()
		.post(IEEndpoints.createprojectwithcreatedstatusenpoints);
		
		System.out.println(resp.getTimeIn(TimeUnit.MILLISECONDS));
		
		String actualprojectname=resp.jsonPath().getString("projectName");
		String actualmessagename=resp.jsonPath().getString("msg");
		
		Assert.assertEquals(expectedprojectname, actualprojectname);
		Assert.assertEquals(expectedmessagename, actualmessagename);
		
		ResultSet result=dbutil.Executequery(Querystring.createprojwithcreatedstatusquery+expectedprojectname+"'");
		while(result.next())
		{
			actualstatus=result.getString(1);
			break;
		}
		
		Assert.assertEquals(expectedstatus,actualstatus);
		
		resp.then()
		.assertThat().statusCode(201).time(Matchers.lessThanOrEqualTo(3000L))
		.log().all();
		
		
		
	
	
	
	}

}
