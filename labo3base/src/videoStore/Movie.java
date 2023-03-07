package videoStore;

/**
 * M. Fowler, et al., Refactoring, Improving the design of existing code,
 * Addison-Wiley, 2000. Exemple Chapitre 1
 * 
 * The class Movie is just a simple data class
 */

public class Movie {
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;

	private String title;
	private int priceCode;

	public Movie(String t, int pc) {
		title = t;
		priceCode = pc;
	}

	public String getTitle() {
		return title;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int newCode) {
		priceCode = newCode;
	}
}
