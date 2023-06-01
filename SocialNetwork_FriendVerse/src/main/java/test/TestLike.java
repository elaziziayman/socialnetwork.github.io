package test;


import dao.UserDaoImpl;

public class TestLike {
	public static void main(String[] args) {
		UserDaoImpl metier = new UserDaoImpl();
		boolean stat = metier.haslikedPublication("2303a185-ba0f-4a50-b74a-13356824aa79", 4);
		System.out.println(stat);

	}

}
