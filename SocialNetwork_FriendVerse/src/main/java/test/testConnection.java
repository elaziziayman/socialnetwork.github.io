package test;

import java.sql.Connection;

import dao.SingletonConnection;

public class testConnection {

	public static void main(String[] args) {
		Connection connection = SingletonConnection.getConnection();
		System.out.println("connected successfully");

	}

}
