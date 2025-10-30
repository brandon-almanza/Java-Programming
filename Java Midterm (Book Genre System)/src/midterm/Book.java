package midterm;


public abstract class Book {
	//Variables
	protected String title;
	protected String isbn;
	protected String publisher;
	protected double price;
	protected int year;
	
	//Constructors
	public Book() {
		this.title = "N/A";
		this.isbn = "N/A";
		this.publisher = "Not Found";
		this.price = 0.0;
		this.year = 0;
	}
	
	public Book(String title, String isbn, String publisher, double price, int year){
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
		this.price = price;
		this.year = year;
	}
	
	//Getters
	public String getTitle() {
		return title;
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public String getPublisher(){
		return publisher;
	}
	
	public int getYear() {
		return year;
	}
	
	//Setters
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	//Abstract Methods
	public abstract void setPrice(double price);
	public abstract String getGenre();
	
	
	//ToString Method
	public String toString() {
		return "Title: " + title + "  ISBN: " + isbn + "  Publisher: " + publisher + "  Price: " + price + "  Year: " + year + "  Genre: " + getGenre();
	}
	
}
