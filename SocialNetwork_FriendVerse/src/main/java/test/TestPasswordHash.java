package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

public class TestPasswordHash {

	public static void main(String[] args) {
		
		Scanner clavier = new Scanner(System.in);
		while(true) {
			System.out.println("Enter a password");
			String password=clavier.next();
			String hash = doHashing(password);
			System.out.println(hash);
			
		}

	}
	public static  String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

}
