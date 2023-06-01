package test;

import java.util.List;

import dao.IUserDao;
import dao.UserDaoImpl;
import metier.entities.User;

public class TestFollowing {
	public static void main(String[] args) {
		IUserDao metier = new UserDaoImpl();
		List<User> users = metier.getfollowingUsers("2303a185-ba0f-4a50-b74a-13356824aa79");
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
		}
	}

}
