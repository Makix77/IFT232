package videoStore;

import java.util.Vector;


/**
 * M. Fowler, et al., Refactoring, Improving the design of existing code,
 * Addison-Wiley, 2000. Exemple Chapitre 1
 * 
 * The Customer class represents the customer of the store.
 * 
 */

public class Customer {
	private String name;
	private Vector<Rental> rentals;

	public Customer(String name) {
		this.name = name;
		rentals = new Vector<Rental>();
	}

	public void addRentals(Rental arg) {
		rentals.addElement(arg);
	}

	public Vector<Rental> getRentals(){
		return rentals;
	}

	public String getName() {
		return name;
	}

	public String englishStatement() {
		EnglishStatement statement = new EnglishStatement();
		return statement.print(this);
	}

	public String frenchStatement(){
		FrenchStatement statement = new FrenchStatement();
		return statement.print(this);
	}

	public double getTotalAmount(){
		double totalAmount = 0.0;
		for (Rental each : rentals){
			totalAmount += each.getMovieAmount();
		}
		return totalAmount;
	}

	public int getTotalFrequentRenterPoints(){
		int frequentRenterPoints = 0;
		for (Rental each : rentals){
			frequentRenterPoints += each.getFrequentRenterPoints();
		}
		return frequentRenterPoints;
	}
}
