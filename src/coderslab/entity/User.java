package coderslab.entity;

public class User {
	private Integer id;
	private String username;
	private String surname;
	private String email;
	private String password;

	public User() {

	}

	public User(String username, String surname, String email, String password) {

		this.username = username;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	/**
	 * This constructor is used to create new object from database
	 * @param id 
	 * @param username
	 * @param surname
	 * @param email
	 * @param password
	 */
	public User(int id, String username, String surname, String email, String password) {
		this.id = id;
		this.username = username;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void toStringUser() {
		System.out.println(this.id + this.username + this.surname + this.email);
	}
}
