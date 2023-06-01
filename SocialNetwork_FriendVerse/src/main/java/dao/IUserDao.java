package dao;

import java.util.List;

import metier.entities.Comment;
import metier.entities.Photo;
import metier.entities.Publication;
import metier.entities.User;

public interface IUserDao {
	public User save(User U);
	public List<User> usersParMC(String mc);
	public User update(User U);
	public User getUser(String username, String password);
	public User getUser(String user_id);
	public Photo addPhoto(Photo profilephoto, User U);
	public Photo getPhoto(String user_id);
	public Photo updatePhoto(Photo profilephoto,User U);
	public Publication addPublication(Publication publication, User U);
	public Publication getPublication(int id_pub);
	public List<Publication> getPublicationsbyUser(String user_id);
	public List<Publication> getPublicationsbyPopularity(String user_id);
	public void likePublication(String user_id, int pub_id);
	public void unlikePublication(String user_id, int pub_id);
	public boolean haslikedPublication(String user_id, int pub_id);
	public Comment commentPublication(String user_id, int pub_id, String comment);
	public List<Comment> getComments(int pub_id);
	public void followUser(String follower_id, String following_id );
	public void unfollowUser(String follower_id, String following_id );
	public boolean isfollowedby(String follower_id, String following_id);
	public List<String> getfollowingId(String user_id);
	public List<String> getfollowerId(String user_id);
	public List<User> getfollowingUsers(String user_id);
	public List<User> getfollowerUsers(String user_id);
	public List<Publication> getfollowingPublications(String user_id);
	public List<String> getLikersId(int pub_id);
	public List<User> getLikers(int pub_id);

	









}
