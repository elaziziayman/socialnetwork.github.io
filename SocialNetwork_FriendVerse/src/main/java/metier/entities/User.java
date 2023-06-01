package metier.entities;

import java.io.Serializable;

import dao.IUserDao;
import dao.UserDaoImpl;

public class User implements Serializable {

	public User(String firstname, String lastname, String gender, int age, String username,
			String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
		this.username = username;
		this.password = password;
	}
	public User() {
	}
	private String id;
	private String firstname, lastname;
	private String gender;
	private int age;
	private int numberposts, numberfollowers, numberfollowing;
	private String username,password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNumberfollowing() {
		return numberfollowing;
	}
	public void setNumberfollowing(int numberfollowing) {
		this.numberfollowing = numberfollowing;
	}
	public int getNumberposts() {
		return numberposts;
	}
	public void setNumberposts(int numberposts) {
		this.numberposts = numberposts;
	}
	public int getNumberfollowers() {
		return numberfollowers;
	}
	public void setNumberfollowers(int numberfollowers) {
		this.numberfollowers = numberfollowers;
	}
	public String followbutton(String user_id) {
		IUserDao metier = new UserDaoImpl();
		if(metier.isfollowedby(user_id, this.id)) {
			return "Unfollow";
		}
		return "Follow";
	}
	public boolean isfollowed(String user_id) {
		IUserDao metier = new UserDaoImpl();
		if(metier.isfollowedby(user_id, this.id)) {
			return true;
		}
		return false;
	}
	

}
