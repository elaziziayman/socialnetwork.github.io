package test;

import java.util.List;

import dao.UserDaoImpl;
import metier.entities.User;

public class TestSearch {

	public static void main(String[] args) {
		UserDaoImpl metier = new UserDaoImpl();
		List<User> users = metier.usersParMC("%" + "zineb" + "%");
		for (User user : users) {
			System.out.println(user.getUsername());
		}
	}

}
