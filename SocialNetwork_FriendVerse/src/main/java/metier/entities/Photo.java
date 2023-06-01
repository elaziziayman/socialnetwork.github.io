package metier.entities;

public class Photo {
    private int id;
    private String filename;
    private String mimeType;
    private long fileSize;
    private byte[] data;
    private String user_id;

    public Photo(String filename, String mimeType, long fileSize, byte[] data, String user_id) {
        this.filename = filename;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.data = data;
        this.setUser_id(user_id);
    }

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
}
