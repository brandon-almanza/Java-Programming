package midterm;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		System.out.println("<----- Mid-Term Test COMP 228 008 Fall-2025 by: Brandon Argenal Almanza --->");
		
		//Scanner object
		Scanner scan = new Scanner(System.in);
		
		//Variable
		String userInput;
		
		//Get user input for genre of book
		System.out.println("What is the genre of your book? (Science or Art)");
		userInput = scan.nextLine();
		
		//if user input is equal to science
		if(userInput.equalsIgnoreCase("science")) {
			ScienceBook scBook1 = new ScienceBook();
			
			System.out.println("Enter the title of your book");
			String scTitle = scan.nextLine();
			scBook1.setTitle(scTitle);
			
			System.out.println("Enter the ISBN of your book");
			String scISBN = scan.nextLine();
			scBook1.setISBN(scISBN);
			
			System.out.println("Enter the Publisher of your book");
			String scPublisher = scan.nextLine();
			scBook1.setPublisher(scPublisher);
			
			System.out.println("Enter the price");
			double scPrice = scan.nextDouble();
			scBook1.setPrice(scPrice);
			
			System.out.println("Enter the year");
			int scYear = scan.nextInt();
			scBook1.setYear(scYear);
			
			//Display the objects info
			System.out.println(scBook1.toString());
			
		}
		
		//else if user input is equal to Art
		else if(userInput.equalsIgnoreCase("art")) {
			ArtBook artBook = new ArtBook();
			
			System.out.println("Enter the title of your book");
			String aTitle = scan.nextLine();
			artBook.setTitle(aTitle);
			
			System.out.println("Enter the ISBN of your book");
			String aISBN = scan.nextLine();
			artBook.setISBN(aISBN);
			
			System.out.println("Enter the Publisher of your book");
			String aPublisher = scan.nextLine();
			artBook.setPublisher(aPublisher);
			
			System.out.println("Enter the price");
			double aPrice = scan.nextDouble();
			artBook.setPrice(aPrice);
			
			System.out.println("Enter the year");
			int aYear = scan.nextInt();
			artBook.setYear(aYear);
			
			//Display the objects info
			System.out.println(artBook.toString());
			
		}
		//Close scanner object
		scan.close();
	}
}
