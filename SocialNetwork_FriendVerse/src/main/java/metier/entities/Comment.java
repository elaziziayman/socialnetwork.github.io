package metier.entities;

public class Comment {
	private int id;
	private String user_id;
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Comment(String user_id, String comment) {
		this.user_id = user_id;
		this.comment = comment;
	}

	
}
