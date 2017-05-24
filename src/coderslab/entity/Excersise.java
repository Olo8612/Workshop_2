package coderslab.entity;

public class Excersise {
	private int id;
	private String title;
	private String description;
	
	public Excersise(String title, String description){
		this.title = title;
		this.description = description;
	}
	
	/**
	 * Use this constructor for create excersise from database
	 */
	public Excersise(int id, String title, String description){
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	/**
	 * Use only to put to database
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
