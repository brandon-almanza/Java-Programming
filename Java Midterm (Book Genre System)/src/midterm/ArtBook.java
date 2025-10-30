package midterm;

public class ArtBook extends Book{
	@Override public void setPrice(double price) {
		this.price = price;
	}
	  
	@Override public String getGenre() {
		return "Art";
	}
}
