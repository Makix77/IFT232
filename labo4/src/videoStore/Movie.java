/*
 * Created on 2005-03-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package videoStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * M. Fowler, et al., Refactoring, Improving the design of existing code,
 * Addison-Wiley, 2000. Exemple Chapitre 1
 * 
 * The class Movie is just a simple data class
 */

public class Movie {
	public static final Price REGULAR = new RegularPrice();
	public static final Price NEW_RELEASE = new NewReleasePrice();
	public static final Price CHILDRENS = new ChildrenPrice();
	public static final Price UNPOPULAR = new UnpopularPrice();

	private java.lang.String title_;
	private ArrayList<PriceDate> prices;

	public Movie(String title, Price price) {
		title_ = title;
		prices = new ArrayList<>();
		prices.add(new PriceDate(price, LocalDate.now()));
	}

	public java.lang.String getTitle() {
		return title_;
	}

	public Price getPriceCode(LocalDate date) {
		for(int i = 0; i < prices.size(); i++){
			if(prices.get(i).compareTo(date) > 0)
				return prices.get(i-1).getPrice();
		}
		return prices.get(prices.size() - 1).getPrice();
	}

	public void setPriceCode(Price price, LocalDate date) {
		PriceDate newPriceDate = new PriceDate(price, date);
		prices.add(newPriceDate);
		Collections.sort(prices);
	}

	public double amount(int daysRented, LocalDate date){
		Price price = getPriceCode(date);
		return price.amount(daysRented);
	}

	public int points(LocalDate date){
		Price price = getPriceCode(date);
		return price.points();
	}
}
