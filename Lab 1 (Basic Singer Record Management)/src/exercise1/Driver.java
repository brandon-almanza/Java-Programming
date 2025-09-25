package exercise1;

public class Driver {
 public static void main(String[] args) {
	 
	 //Brandon Argenal Almanza, 301467830
	 
	 //Creating new singer object
	 Singer singer1 = new Singer();
	 
	 //Displaying default values
	 System.out.println("ID: " + singer1.getID());
	 System.out.println("Name: " + singer1.getName());
	 System.out.println("Address: " + singer1.getAddress());
	 System.out.println("Birth Date: " + singer1.getBirthDate());
	 System.out.println("Albums Published: " + singer1.getAlbumsPublished());
	 
	 //Creating second singer object
	 //This time with fully added data/values 
	 Singer singer2 = new Singer(1, "Brandon", "Centennial College", 210906, 2);
	 
	 //Displaying singer2 values
	 System.out.println("\nID: " + singer2.getID());
	 System.out.println("Name: " + singer2.getName());
	 System.out.println("Address: " + singer2.getAddress());
	 System.out.println("Birth Date: " + singer2.getBirthDate());
	 System.out.println("Albums Published: " + singer2.getAlbumsPublished());
	 
	 //Creating third singer object
	 Singer singer3 = new Singer();
	 
	 //updating the objects data through setter methods
	 singer3.setID(2);
	 singer3.setName("Rehanna");
	 singer3.setBirthDate(310506);
	 
	 //Displaying singer3 values
	 System.out.println("\nID: " + singer3.getID());
	 System.out.println("Name: " + singer3.getName());
	 System.out.println("Address: " + singer3.getAddress());
	 System.out.println("Birth Date: " + singer3.getBirthDate());
	 System.out.println("Albums Published: " + singer3.getAlbumsPublished());
	 
	 //Method that sets values for every variable
	 singer3.setAll(0, "Biggest Bird", "Elmo street", 0, 0);
	 
	 
	 }
	 
 }

