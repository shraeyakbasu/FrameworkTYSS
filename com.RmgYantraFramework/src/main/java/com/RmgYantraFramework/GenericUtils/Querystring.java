package com.RmgYantraFramework.GenericUtils;

public interface Querystring 
{
	String createprojwithcompletedstatusquery="select status from project where project_name='";
	String createprojwithcreatedstatusquery="select status from project where project_name='";
	String createprojwithongoingstatusquery="select status from project where project_name='";
	String createprojwithtextformatbodyquery="";
	String updateaprojectquery="select * from project where project_id='";

}
