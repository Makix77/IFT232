package videoStore;

/**
 * M. Fowler, et al., Refactoring, Improving the design of existing code,
 * Addison-Wiley, 2000. Exemple Chapitre 1
 * 
 * The class Rental is just a simple data class
 */

public class Rental {
	private int daysRented;
	private Movie movie;

	public Rental(Movie m, int d) {
		movie = m;
		daysRented = d;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public double getMovieAmount(){
		double thisAmount = 0;

		// determines amount for each line
		switch (movie.getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (daysRented > 2)
					thisAmount += (daysRented - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += daysRented * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (daysRented > 3)
					thisAmount += (daysRented - 3) * 1.5;
				break;
		}
		return thisAmount;
	}

	public int getFrequentRenterPoints(){
		// add frequent renter point and bonus for a new release rental
		if (movie.getPriceCode() == Movie.NEW_RELEASE)
			return 2;
		// add frequent renter point but no bonus
		return 1;
	}
}
