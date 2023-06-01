package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import metier.entities.Comment;
import metier.entities.Photo;
import metier.entities.Publication;
import metier.entities.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public User save(User U) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO USER (USER_ID, FIRST_NAME, LAST_NAME, GENDER, AGE, USERNAME, PASSWORD) VALUES(?,?,?,?,?,?,?)");
			U.setId(UUID.randomUUID().toString());
			ps.setString(1, U.getId());
			ps.setString(2, U.getFirstname());
			ps.setString(3, U.getLastname());
			ps.setString(4, U.getGender());
			ps.setInt(5, U.getAge());
			ps.setString(6, U.getUsername());
			ps.setString(7, U.getPassword());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return U;
	}

	@Override
	public List<User> usersParMC(String mc) {
		Connection connection = SingletonConnection.getConnection();
		List<User> users = new ArrayList<User>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM USER WHERE USERNAME LIKE ?");
			ps.setString(1, mc);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User U = new User();
				U.setId(rs.getString("USER_ID"));
				U.setFirstname(rs.getString("FIRST_NAME"));
				U.setLastname(rs.getString("LAST_NAME"));
				U.setAge(rs.getInt("AGE"));
				U.setGender(rs.getString("GENDER"));
				U.setUsername(rs.getString("USERNAME"));
				U.setPassword(rs.getString("PASSWORD"));
				users.add(U);

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User update(User U) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE  USER SET FIRST_NAME=?, LAST_NAME=?, GENDER=?, AGE =?, USERNAME=?, PASSWORD=? WHERE USER_ID=?");
			ps.setString(1, U.getFirstname());
			ps.setString(2, U.getLastname());
			ps.setString(3, U.getGender());
			ps.setInt(4, U.getAge());
			ps.setString(5, U.getUsername());
			ps.setString(6, U.getPassword());
			ps.setString(7, U.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return U;
	}

	@Override
	public User getUser(String username, String password) {
		Connection connection = SingletonConnection.getConnection();
		User U = null;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM USER WHERE USERNAME = '" + username + "' AND PASSWORD ='" + password + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				U = new User();
				U.setId(rs.getString("USER_ID"));
				U.setFirstname(rs.getString("FIRST_NAME"));
				U.setLastname(rs.getString("LAST_NAME"));
				U.setAge(rs.getInt("AGE"));
				U.setGender(rs.getString("GENDER"));
				U.setUsername(rs.getString("USERNAME"));
				U.setPassword(rs.getString("PASSWORD"));
				U.setNumberposts(rs.getInt("NUMBER_POSTS"));
				U.setNumberfollowers(rs.getInt("NUMBER_FOLLOWERS"));
				U.setNumberfollowing(rs.getInt("NUMBER_FOLLOWING"));


			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return U;
	}

	@Override
	public Photo addPhoto(Photo profilephoto, User U) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO profile_photo (FILENAME, MIME_TYPE, FILE_SIZE, DATA, USER_ID) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, profilephoto.getFilename());
			ps.setString(2, profilephoto.getMimeType());
			ps.setLong(3, profilephoto.getFileSize());
			ps.setBytes(4, profilephoto.getData());
			ps.setString(5, U.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profilephoto;
	}

	@Override
	public Photo getPhoto(String user_id) {
		Connection connection = SingletonConnection.getConnection();
		String sql = "SELECT FILENAME, MIME_TYPE, FILE_SIZE, DATA FROM profile_photo WHERE USER_ID = ?";
		PreparedStatement statement;
		Photo P = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				 P = new Photo(rs.getString("FILENAME"), rs.getString("MIME_TYPE"), rs.getLong("FILE_SIZE"),
						rs.getBytes("DATA"), user_id);
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return P;
	}

	@Override
	public Photo updatePhoto(Photo profilephoto, User U) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE profile_photo SET FILENAME=?, MIME_TYPE=?, FILE_SIZE=?, DATA=? WHERE USER_ID = ?");
			ps.setString(1, profilephoto.getFilename());
			ps.setString(2, profilephoto.getMimeType());
			ps.setLong(3, profilephoto.getFileSize());
			ps.setBytes(4, profilephoto.getData());
			ps.setString(5, U.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profilephoto;
	}

	@Override
	public User getUser(String user_id) {
		Connection connection = SingletonConnection.getConnection();
		User U = null;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM USER WHERE USER_ID = ?");
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				U = new User();
				U.setId(rs.getString("USER_ID"));
				U.setFirstname(rs.getString("FIRST_NAME"));
				U.setLastname(rs.getString("LAST_NAME"));
				U.setAge(rs.getInt("AGE"));
				U.setGender(rs.getString("GENDER"));
				U.setUsername(rs.getString("USERNAME"));
				U.setPassword(rs.getString("PASSWORD"));
				U.setNumberposts(rs.getInt("NUMBER_POSTS"));
				U.setNumberfollowers(rs.getInt("NUMBER_FOLLOWERS"));
				U.setNumberfollowing(rs.getInt("NUMBER_FOLLOWING"));

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return U;
	}

	@Override
	public Publication addPublication(Publication publication, User U) {
		Connection connection = SingletonConnection.getConnection();
		String uid = U.getId();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO publication (FILENAME, MIME_TYPE, FILE_SIZE, DATA, NUMBER_LIKES, NUMBER_COMMENTS, DESCRIPTION ,USER_ID, DATE_PUBLICATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE user SET NUMBER_POSTS = NUMBER_POSTS + 1 WHERE USER_ID = ?");

			ps.setString(1, publication.getFilename());
			ps.setString(2, publication.getMimeType());
			ps.setLong(3, publication.getFileSize());
			ps.setBytes(4, publication.getData());
			ps.setInt(5, publication.getNumberlikes());
			ps.setInt(6, publication.getNumbercomments());
			ps.setString(7, publication.getDescription());
			ps.setString(8, U.getId());
			ps.setLong(9, publication.getDatepublication());
			
			ps2.setString(1, uid);

			ps.executeUpdate();
			ps2.executeUpdate();
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return publication;
	}

	@Override
	public List<Publication> getPublicationsbyUser(String user_id) {
		Connection connection = SingletonConnection.getConnection();
		List<Publication> publications = new ArrayList<Publication>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM publication WHERE USER_ID = ? ORDER BY DATE_PUBLICATION DESC");
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("PUBLICATION_ID");
				String filename = rs.getString("FILENAME");
				String mimetype = rs.getString("MIME_TYPE");
				Long filesize = rs.getLong("FILE_SIZE");
				byte[] data = rs.getBytes("Data");	
				int n_likes = rs.getInt("NUMBER_LIKES");
				int n_comments = rs.getInt("NUMBER_COMMENTS");
				String description = rs.getString("DESCRIPTION");
				Long date_publication = rs.getLong("DATE_PUBLICATION");
				Publication P = new Publication(filename, mimetype, filesize, data, n_likes, n_comments, description, date_publication, user_id);
				P.setId(id);
				publications.add(P);

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publications;
	}

	

	@Override
	public Publication getPublication(int id_pub) {
		Connection connection = SingletonConnection.getConnection();
		Publication publication = null;

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM publication WHERE PUBLICATION_ID = ?");
			ps.setInt(1, id_pub);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("PUBLICATION_ID");
				String filename = rs.getString("FILENAME");
				String mimetype = rs.getString("MIME_TYPE");
				Long filesize = rs.getLong("FILE_SIZE");
				byte[] data = rs.getBytes("Data");	
				int n_likes = rs.getInt("NUMBER_LIKES");
				int n_comments = rs.getInt("NUMBER_COMMENTS");
				String description = rs.getString("DESCRIPTION");
				Long date_publication = rs.getLong("DATE_PUBLICATION");
				publication = new Publication(filename, mimetype, filesize, data, n_likes, n_comments, description, date_publication);
				publication.setId(id);

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publication;
	}

	@Override
	public void likePublication(String user_id, int pub_id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO likes (USER_ID,PUBLICATION_ID) VALUES (?, ?)");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE publication SET NUMBER_LIKES = NUMBER_LIKES + 1 WHERE PUBLICATION_ID = " + pub_id);
			ps.setString(1, user_id);
			ps.setInt(2, pub_id);
			ps.executeUpdate();
			ps2.executeUpdate();
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}

	@Override
	public void unlikePublication(String user_id, int pub_id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"DELETE FROM likes  WHERE USER_ID = ?");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE publication SET NUMBER_LIKES = NUMBER_LIKES -1 WHERE PUBLICATION_ID = " + pub_id);
			ps.setString(1, user_id);
			ps.executeUpdate();
			ps2.executeUpdate();
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean haslikedPublication(String user_id, int pub_id) {
		Connection connection = SingletonConnection.getConnection();
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM likes WHERE USER_ID = ? AND PUBLICATION_ID= ?");
			ps.setString(1, user_id);
			ps.setInt(2, pub_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}
			ps.close();
			if(count==1) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Comment commentPublication(String user_id, int pub_id , String comment) {
		Connection connection = SingletonConnection.getConnection();
		IUserDao metier = new UserDaoImpl();
		Comment c = null;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO comments (USER_ID,PUBLICATION_ID, COMMENT_TEXT) VALUES (?, ?, ?)");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE publication SET NUMBER_COMMENTS = NUMBER_COMMENTS + 1 WHERE PUBLICATION_ID = " + pub_id);
			ps.setString(1, user_id);
			ps.setInt(2, pub_id);
			ps.setString(3, comment);
			c = new Comment(user_id, comment);
			ps.executeUpdate();
			ps2.executeUpdate();
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Comment> getComments(int pub_id) {
		Connection connection = SingletonConnection.getConnection();
		Comment c = null;
		List<Comment> cmts = new ArrayList<Comment>();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM COMMENTS WHERE PUBLICATION_ID = ?");
			ps.setInt(1, pub_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Comment(rs.getString("USER_ID"),rs.getString("COMMENT_TEXT"));
				cmts.add(c);
			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return cmts;
	}

	@Override
	public void followUser(String follower_id, String following_id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO followers (FOLLOWER_ID, FOLLOWING_ID) VALUES (?, ?)");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE user SET NUMBER_FOLLOWING = NUMBER_FOLLOWING + 1 WHERE USER_ID = ? ");
			PreparedStatement ps3 = connection.prepareStatement("UPDATE user SET NUMBER_FOLLOWERS = NUMBER_FOLLOWERS + 1 WHERE USER_ID = ? ");

			ps.setString(1, follower_id);
			ps.setString(2, following_id);
			ps.executeUpdate();
			ps2.setString(1, follower_id);
			ps2.executeUpdate();
			ps3.setString(1, following_id);
			ps3.executeUpdate();
			ps.close();
			ps2.close();
			ps3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void unfollowUser(String follower_id, String following_id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"DELETE FROM followers  WHERE FOLLOWER_ID = ? AND FOLLOWING_ID = ?" );
			PreparedStatement ps2 = connection.prepareStatement("UPDATE user SET NUMBER_FOLLOWING = NUMBER_FOLLOWING - 1 WHERE USER_ID = ?");
			PreparedStatement ps3 = connection.prepareStatement("UPDATE user SET NUMBER_FOLLOWERS = NUMBER_FOLLOWERS - 1 WHERE USER_ID = ? " );
			ps.setString(1, follower_id);
			ps.setString(2, following_id);
			ps.executeUpdate();
			ps2.setString(1, follower_id);
			ps2.executeUpdate();
			ps3.setString(1, following_id);
			ps3.executeUpdate();
			ps.close();
			ps2.close();
			ps3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isfollowedby(String follower_id, String following_id) {
		Connection connection = SingletonConnection.getConnection();
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM followers WHERE FOLLOWER_ID = ? AND FOLLOWING_ID= ?");
			ps.setString(1, follower_id);
			ps.setString(2, following_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}
			ps.close();
			if(count==1) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> getfollowingId(String user_id) {
		Connection connection = SingletonConnection.getConnection();
		List<String> idfollowings = new ArrayList<String>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT FOLLOWING_ID FROM followers WHERE FOLLOWER_ID = ?");
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("FOLLOWING_ID");
				
				idfollowings.add(id);

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idfollowings;
	
	}

	@Override
	public List<User> getfollowingUsers(String user_id) {
		List<User> followings = new ArrayList<User>();
		List<String> ids = this.getfollowingId(user_id);
		for (int i = 0; i < ids.size(); i++) {
			User us = this.getUser(ids.get(i));
			followings.add(us);
			

			}
		

		return followings;
	}

	@Override
	public List<Publication> getfollowingPublications(String user_id) {
		Connection connection = SingletonConnection.getConnection();
		List<Publication> publications = new ArrayList<Publication>();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PUBLICATION INNER JOIN FOLLOWERS ON PUBLICATION.USER_ID=FOLLOWERS.FOLLOWING_ID WHERE FOLLOWER_ID =? ORDER BY DATE_PUBLICATION DESC;");
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int pub_id = rs.getInt("PUBLICATION_ID");
				Publication P = this.getPublication(pub_id);
				String id_user = rs.getString("FOLLOWING_ID");
				User U = this.getUser(id_user);
				P.setUser(U);
				publications.add(P);
				

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return publications;
	}

	@Override
	public List<String> getfollowerId(String user_id) {
		Connection connection = SingletonConnection.getConnection();
		List<String> idfollowers = new ArrayList<String>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT FOLLOWER_ID FROM followers WHERE FOLLOWING_ID = ?");
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("FOLLOWER_ID");
				
				idfollowers.add(id);

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idfollowers;
	}

	@Override
	public List<User> getfollowerUsers(String user_id) {
		List<User> followers = new ArrayList<User>();
		List<String> ids = this.getfollowerId(user_id);
		for (int i = 0; i < ids.size(); i++) {
			User us = this.getUser(ids.get(i));
			followers.add(us);
			

			}
		

		return followers;
	}

	@Override
	public List<String> getLikersId(int pub_id) {
		Connection connection = SingletonConnection.getConnection();
		List<String> likersid = new ArrayList();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT USER_ID FROM likes WHERE PUBLICATION_ID= ?");
			ps.setInt(1,pub_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String uid = rs.getString(1);
				likersid.add(uid);
				}
			ps.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return likersid;
	}

	@Override
	public List<User> getLikers(int pub_id) {
		List<String> likersid = getLikersId(pub_id);
		List<User> likers = new ArrayList();
		for (int i = 0; i < likersid.size(); i++) {
			User U = getUser(likersid.get(i));
			likers.add(U);
		}
		return likers;
	}

	@Override
	public List<Publication> getPublicationsbyPopularity(String user_id) {
		Connection connection = SingletonConnection.getConnection();
		List<Publication> publications = new ArrayList<Publication>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM publication WHERE NUMBER_LIKES >= 100 AND USER_ID NOT LIKE ? ORDER BY DATE_PUBLICATION DESC LIMIT 3");
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("PUBLICATION_ID");
				String filename = rs.getString("FILENAME");
				String mimetype = rs.getString("MIME_TYPE");
				Long filesize = rs.getLong("FILE_SIZE");
				byte[] data = rs.getBytes("Data");	
				int n_likes = rs.getInt("NUMBER_LIKES");
				int n_comments = rs.getInt("NUMBER_COMMENTS");
				String description = rs.getString("DESCRIPTION");
				Long date_publication = rs.getLong("DATE_PUBLICATION");
				String uid = rs.getString("USER_ID");
				Publication P = new Publication(filename, mimetype, filesize, data, n_likes, n_comments, description, date_publication, uid);
				P.setId(id);
				User user = this.getUser(uid);
				P.setUser(user);
				if(!user.isfollowed(user_id)) {
					publications.add(P);
				}
				
				

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publications;
	}
}
