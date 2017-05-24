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
	/**
	 * Overload constructor to create object with values from database
	 * @param id
	 * @param createDate
	 * @param updateDate
	 * @param excersiseId
	 * @param userId
	 */
	public Solution(int id, Date createdDate, Date updatedDate, String description, int excersiseId, int userId){
		this.id = id;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.description = description;
		this.excersiseId = excersiseId;
		this.userId = userId;
	}
	public void setId(int id) {
		this.id = id;
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
