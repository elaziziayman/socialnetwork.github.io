package test;

import java.util.UUID;

public class TestGenerateID {

	public static void main(String[] args) {
		String uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);

	}

}
