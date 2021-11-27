package com.RmgYantraFramework.Test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.RmgYantraFramework.GenericUtils.Baseclass;
import com.RmgYantraFramework.GenericUtils.IEEndpoints;
import com.RmgYantraFramework.GenericUtils.JavaUtils;
import com.RmgYantraFramework.GenericUtils.Querystring;
import com.RmgYantraFramework.PojoClass.CreateProjectWithOnGoingStatusPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class CreateProjectwithOnGoingStatusTest extends Baseclass
{
	
          @Test
          public void createprojectwithongoingstatus() throws SQLException
          {
        	  baseURI="http://localhost:5555";
      		String expectedprojectname=" balaram mallick sweets "+JavaUtils.getrandomdata();
      		String expectedstatus="on going";
      		String actualstatus="";
      		String expectedmessage="Successfully Added";
      		
      		CreateProjectWithOnGoingStatusPojo createprojpojo=new CreateProjectWithOnGoingStatusPojo("shraeyak basu", expectedprojectname, expectedstatus, 10);
      		
      		Response resp=given()
      		.contentType(ContentType.JSON)
      		.body(createprojpojo)
      		.when()
      		.post(IEEndpoints.createprojectwithongoingstatusenpoints);
      		
      		System.out.println(resp.getTimeIn(TimeUnit.MILLISECONDS));
      		
      		resp.then()
      		.assertThat().statusCode(201).time(Matchers.lessThanOrEqualTo(3000L))
      		.log().all();
      		
      		String actaulprojectname=resp.jsonPath().get("projectName");
      		String actualmsg=resp.jsonPath().getString("msg");
      		
      		
      		String projectname=dbutil.ExecutequeryandGetResult("select * from project", 4, expectedprojectname);
      		ResultSet result=dbutil.Executequery(Querystring.createprojwithongoingstatusquery+projectname+"'"); 
      		if(result.next())
      		{
      		actualstatus=(result.getString(1));
      		}
      		Assert.assertEquals(actaulprojectname, expectedprojectname);
      		Assert.assertEquals(expectedstatus, actualstatus);
      		Assert.assertEquals(expectedmessage, actualmsg);
      		

          }

}
