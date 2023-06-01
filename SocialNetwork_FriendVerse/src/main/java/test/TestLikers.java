package test;

import java.util.List;


import dao.IUserDao;
import dao.UserDaoImpl;
import metier.entities.User;

public class TestLikers {

	public static void main(String[] args) {
		IUserDao metier = new UserDaoImpl();
		List<User> likers = metier.getLikers(13);
		for (int i = 0; i < likers.size(); i++) {
			System.out.println(likers.get(i).getUsername());
		}
	}

	}


