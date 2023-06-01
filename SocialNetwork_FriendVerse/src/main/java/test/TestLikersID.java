package test;

import java.util.List;

import dao.IUserDao;
import dao.UserDaoImpl;

public class TestLikersID {

	public static void main(String[] args) {
		IUserDao metier = new UserDaoImpl();
		List<String> likersid = metier.getLikersId(13);
		for (int i = 0; i < likersid.size(); i++) {
			System.out.println(likersid.get(i));
		}
	}

}
