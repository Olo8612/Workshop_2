package coderslab.entity;

import java.util.Date;

public class Solution {

	private int id;
	private Date createdDate;
	private Date updatedDate;
	private String description;
	private int excersiseId;
	private int userId;
	
	public Solution(){
		
	}
	public Solution(String description, int excersiseId, int userId){
		this.createdDate = new Date();
		this.description = description;
		this.excersiseId = excersiseId;
		this.userId = userId;
		
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = new Date();
	}
	public int getId() {
		return id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public String getDescription() {
		return description;
	}
	public int getExcersiseId() {
		return excersiseId;
	}
	public int getUserId() {
		return userId;
	}
	
}
