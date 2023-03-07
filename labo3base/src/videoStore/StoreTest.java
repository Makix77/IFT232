package videoStore;

public class StoreTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Movie movies[] = new Movie[5];
		
		
		movies[0] = new Movie("Caillou a le cancer",Movie.CHILDRENS);
		movies[1] = new Movie("Fast and furious 38: Diesel Cars vs Google Cars",Movie.NEW_RELEASE);
		movies[2] = new Movie("31 Jump Street: Ninja Academy", Movie.NEW_RELEASE);
		movies[3] = new Movie("Les oiseaux se cachent pour mourir",Movie.REGULAR);
		movies[4] = new Movie("Les gars chauds se cachent pour vomir",Movie.REGULAR);
		
		
		Customer grandmaman = new Customer("Grand-môman");
		
		grandmaman.addRentals(new Rental(movies[0],4));
		grandmaman.addRentals(new Rental(movies[1],1));
		grandmaman.addRentals(new Rental(movies[2],2));
		grandmaman.addRentals(new Rental(movies[3],7));
		grandmaman.addRentals(new Rental(movies[4],1));
		
		System.out.println(grandmaman.englishStatement());
	
		
		
		

	}

}
