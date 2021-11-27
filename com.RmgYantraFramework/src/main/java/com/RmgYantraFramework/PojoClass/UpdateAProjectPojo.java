package com.RmgYantraFramework.PojoClass;

public class UpdateAProjectPojo
{
	private String projectid;
	private String createdBy;
	private String createdon;
	private String projectName;
	private String status;
	private int teamSize;
	
	public UpdateAProjectPojo(String projectid,String createdBy,String createdon, String projectName, String status, int teamSize) 
	{
		this.projectid=projectid;
		this.createdBy = createdBy;
		this.createdon=createdon;
		this.projectName = projectName;
		this.status = status;
		this.teamSize = teamSize;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	
	
	
	
}
