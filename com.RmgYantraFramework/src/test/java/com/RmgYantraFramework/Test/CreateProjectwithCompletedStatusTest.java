package com.RmgYantraFramework.Test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.RmgYantraFramework.GenericUtils.Baseclass;
import com.RmgYantraFramework.GenericUtils.IEEndpoints;
import com.RmgYantraFramework.GenericUtils.JavaUtils;
import com.RmgYantraFramework.GenericUtils.Querystring;
import com.RmgYantraFramework.PojoClass.CreateProjectWithCompletedStatusPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class CreateProjectwithCompletedStatusTest extends Baseclass
{

	@Test
	public void CreateProjectwithCompletedStatus() throws SQLException
	{
		baseURI="http://localhost:5555";
		String expectedprojectname="asirvad aata "+JavaUtils.getrandomdata();
		String actualstatus="";
		String expectedstatus="completed";
		String expectedmessage="Successfully Added";
		
		CreateProjectWithCompletedStatusPojo createprojpojo=new CreateProjectWithCompletedStatusPojo("shraeyak basu", expectedprojectname, expectedstatus, 10);
		
		Response resp=given()
		.contentType(ContentType.JSON)
		.body(createprojpojo)
		.when()
		.post(IEEndpoints.createprojectwithcompletedstatusenpoints);
		
		System.out.println(resp.getTimeIn(TimeUnit.MILLISECONDS));
		
		resp.then()
		.assertThat().statusCode(201).time(Matchers.lessThanOrEqualTo(3000L))
		.log().all();
		
		String actualprojectname=resp.jsonPath().get("projectName");
		String actualmsg=resp.jsonPath().getString("msg");
		
		
		ResultSet result=dbutil.Executequery(Querystring.createprojwithcompletedstatusquery+actualprojectname+"'"); 
		while(result.next())
		{
			actualstatus=(result.getString(1));
		}
		Assert.assertEquals(actualprojectname, expectedprojectname);
		Assert.assertEquals(expectedstatus, actualstatus);
		Assert.assertEquals(expectedmessage, actualmsg);
		
	}
}
