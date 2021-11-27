package com.RmgYantraFramework.Test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.RmgYantraFramework.GenericUtils.Baseclass;
import com.RmgYantraFramework.GenericUtils.IEEndpoints;
import com.RmgYantraFramework.GenericUtils.Querystring;
import com.RmgYantraFramework.PojoClass.UpdateAProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateAProject extends Baseclass
{
	
      @Test
      public void updateaproject() throws SQLException
      {
    	  baseURI="http://localhost:5555";
    	  
    	  UpdateAProjectPojo updateproj=new UpdateAProjectPojo("TY_PROJ_004","sishikee","19/05/2021", "history", "completed", 8);
    	
    	  String projid=updateproj.getProjectid();
    	 String actualprojname=updateproj.getProjectName();
    	 String actualstatus=updateproj.getStatus();
    	 String actualcreatedby=updateproj.getCreatedBy();
    	 int actualteamsize=updateproj.getTeamSize();
    	 
    	 String createdby="";
    	 String createdon="";
    	 String projectname="";
    	 String status="";
    	 int teamsize=0;
    	 
    	 String actualcreatedon=updateproj.getCreatedon();
    	 
    	 Response resp=given()
    	  .contentType(ContentType.JSON)
    	  .body(updateproj)
    	  .when()
    	  .put(IEEndpoints.updateprojectendpoints+projid);
    	 
    	 ResultSet result=dbutil.Executequery(Querystring.updateaprojectquery+projid+"'");
    	 while(result.next())
    	 {
    		 createdby=result.getString("created_by");
    		createdon= result.getString("created_on");
    		projectname=result.getString("project_name");
    		status=result.getString("status");
    		teamsize=result.getInt("team_size");
    	 }
    	 
    	 resp.then()
    	 .assertThat().statusCode(200).time(Matchers.lessThanOrEqualTo(2000L))
    	 .log().all();
    	 
    	 Assert.assertEquals(actualcreatedby, createdby);
    	 Assert.assertEquals(actualcreatedon, createdon);
    	 Assert.assertEquals(actualprojname, projectname);
    	 Assert.assertEquals(actualstatus, status);
    	 Assert.assertEquals(actualteamsize, teamsize);
    			  
      }
}
