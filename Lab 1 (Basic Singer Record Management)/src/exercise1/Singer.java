package exercise1;

public class Singer {
	
	//variables
	private int id;
	private String name;
	private String address;
	private int birthdate;
	private int albumsPublished;
	
	//Default Constructor
	public Singer() {
		this.id = 0;
		this.name = "N/A";
		this.address = "Unknown";
		this.birthdate = 0;
		this.albumsPublished = 0;
	}
	
	//Constructors
	public Singer(int id) {
		this.id = id;
	}
	
	public Singer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Singer(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public Singer(int id, String name, String address, int birthdate) {
		this.id = id;
		this.name = name;	
		this.address = address;
		this.birthdate = birthdate;
	}
	
	public Singer(int id, String name, String address, int birthdate, int albumspublished) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthdate = birthdate;
		this.albumsPublished = albumspublished;
	}
	
	//Methods
	//Setters update the data
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setBirthDate(int birthdate) {
		this.birthdate = birthdate;
	}
	
	public void setAlbumsPublished(int albumspublished) {
		this.albumsPublished = albumspublished; 
	}
	
	//Getters read/display the data
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getBirthDate() {
		return birthdate;
	}
	
	public int getAlbumsPublished() {
		return albumsPublished;
	}
	
	public void setAll(int id, String name, String address, int birthdate, int albumspublished) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthdate = birthdate;
		this.albumsPublished = albumspublished;
	}
	
}
