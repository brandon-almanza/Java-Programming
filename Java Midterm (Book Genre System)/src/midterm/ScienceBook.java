package midterm;

public class ScienceBook extends Book {
	@Override public void setPrice(double price) {
		double discountPrice = price * 0.85;
		this.price = discountPrice;
	}
	
	@Override public String getGenre() {
		return "Science";
	}
}
