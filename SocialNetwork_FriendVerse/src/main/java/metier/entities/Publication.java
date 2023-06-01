package metier.entities;

import java.util.List;

import dao.IUserDao;
import dao.UserDaoImpl;
import supp.Help;

public class Publication {

	private int id;
    private String filename;
    private String mimeType;
    private long fileSize;
    private byte[] data;
    private List<Comment> comments;
    

	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	private int numberlikes, numbercomments;
    private String description;
    private Long datepublication;
    private User user;
    
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Publication(String filename, String mimeType, long fileSize, byte[] data, int numberlikes,
			int numbercomments, String description, Long datepublication, String user_id) {
		super();
		this.filename = filename;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
		this.data = data;
		this.numberlikes = numberlikes;
		this.numbercomments = numbercomments;
		this.description = description;
		this.datepublication = datepublication;
		this.user_id = user_id;
	}
    public Publication(String filename, String mimeType, long fileSize, byte[] data, int numberlikes,
			int numbercomments, String description, Long datepublication) {
		super();
		this.filename = filename;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
		this.data = data;
		this.numberlikes = numberlikes;
		this.numbercomments = numbercomments;
		this.description = description;
		this.datepublication = datepublication;
		
	}

	public int getNumberlikes() {
		return numberlikes;
	}

	public void setNumberlikes(int numberlikes) {
		this.numberlikes = numberlikes;
	}

	public int getNumbercomments() {
		return numbercomments;
	}

	public void setNumbercomments(int numbercomments) {
		this.numbercomments = numbercomments;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDatepublication() {
		return datepublication;
	}

	public void setDatepublication(Long datepublication) {
		this.datepublication = datepublication;
	}

	private String user_id;


    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }



	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String afficherdate() {
		Help hp = new Help();
		long currentdate= System.currentTimeMillis() / 1000;
		long date_pub = this.datepublication;
		String res = hp.timeInfo(currentdate, date_pub);

		return res;
	}
	public String likebutton(String user_id) {
		IUserDao metier = new UserDaoImpl();
		if(metier.haslikedPublication(user_id, this.id)) {
			return "Unlike";
		}
		return "Like";
	}
}