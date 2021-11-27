package com.RmgYantraFramework.Test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.RmgYantraFramework.GenericUtils.Baseclass;
import com.RmgYantraFramework.GenericUtils.IEEndpoints;
import com.RmgYantraFramework.GenericUtils.JavaUtils;
import com.RmgYantraFramework.PojoClass.CreateProjectwithTextFormatBodyPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class CreateProjectwithTextFormatBody extends Baseclass
{

	@Test
	public void createprojectwithtextformatbody() throws SQLException
	{
		baseURI="http://localhost:5555";
		String actualprojectname="curlsburg "+JavaUtils.getrandomdata();
		String expectedmsg="content type not supported";
		
		CreateProjectwithTextFormatBodyPojo createproj=new CreateProjectwithTextFormatBodyPojo("gargi", actualprojectname, "on going", 20);
		
		
		
		Response resp=given()
		.contentType(ContentType.TEXT)
			
		
		.body(createproj)
		.when()
		.post(IEEndpoints.createprojectwithtextformatbodyendpoints);
		
		
		String actualmsg=resp.jsonPath().get("msg");
		System.out.println(resp.statusCode());
		System.out.println(resp.timeIn(TimeUnit.MILLISECONDS));
		
		dbutil.ExecutequeryandGetResult("select * from project", 4, actualprojectname);
		
		resp.then()
		.assertThat().statusCode(500).time(Matchers.lessThanOrEqualTo(300L))
		.log().all();
		
		Assert.assertEquals(actualmsg, expectedmsg);
		
		
		
	}
}
