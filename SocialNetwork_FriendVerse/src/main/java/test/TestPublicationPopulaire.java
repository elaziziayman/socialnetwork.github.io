package test;

import java.util.List;

import dao.IUserDao;
import dao.UserDaoImpl;
import metier.entities.Publication;

public class TestPublicationPopulaire {

	public static void main(String[] args) {
		IUserDao metier = new UserDaoImpl();
		List<Publication> publications = metier.getPublicationsbyPopularity("951bcb7d-465c-4921-aeb4-f3137f0bbeb4");
		
			
	
		for (int i = 0; i < publications.size(); i++) {
			System.out.println(publications.get(i).getUser_id());
		}
		
	}

}
