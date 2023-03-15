/*
 * Created on 2005-03-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package videoStore;

import java.time.LocalDate;

public class Rental {
	private int daysRented;
	private Movie movie;
	private LocalDate date;

	public Rental(Movie movie, LocalDate initialDate, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
		date = initialDate;
	}

	public Rental(Movie movie, int daysRented) {
		this(movie, LocalDate.now(), daysRented);
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}
	public LocalDate getDate() {
		return date;
	}

	public double amount(){
		return movie.amount(getDaysRented(), getDate());
	}
	public int points(){
		return movie.points(getDate());
	}
}
