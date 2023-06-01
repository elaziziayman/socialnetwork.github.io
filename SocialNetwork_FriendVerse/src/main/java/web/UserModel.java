package web;
import java.util.ArrayList;
import java.util.List;

import metier.entities.User;
public class UserModel {
	private String mc;
	private List<User> users = new ArrayList<User>();
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
