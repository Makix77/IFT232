/*
 * Created on 2005-03-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package videoStore;

import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Rental> rentals;

	public Customer(String name) {
		this.name = name;
		rentals = new ArrayList<Rental>();
	}

	public void addRentals(Rental arg) {
		rentals.add(arg);
	}

	public String getName() {
		return name;
	}

	public String englishStatement() {

		return new EnglishStatement(this).print();
	}
	
	public String frenchStatement() {

		return new FrenchStatement(this).print();
	}

	public double totalAmount() {
		double totalAmount = 0;

		for (Rental each : rentals) {

			totalAmount += each.amount();
		}
		
		return totalAmount;
	}

	public int totalPoints() {

		int frequentRenterPoints = 0;

		for (Rental each : rentals) {

			frequentRenterPoints += each.points();
		}
		
		return frequentRenterPoints;
	}

	public ArrayList<Rental> getRentals() {
		
		return rentals;
	}

}
