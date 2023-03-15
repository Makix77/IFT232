package videoStore.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import videoStore.*;

import java.time.LocalDate;

public class RentalTest {
	
	private final double EPSILON = 0.01;
	private Movie childMovie;
	private Movie regularMovie;

	private Movie newMovie;
	private Movie unpopularMovie;
	
	@Before
	public void setup() {
		childMovie = new Movie("Caillou a le cancer", Movie.CHILDRENS);
		newMovie = new Movie("31 Jump Street: Ninja Academy", Movie.NEW_RELEASE);
		regularMovie = new Movie("Les oiseaux se cachent pour mourir", Movie.REGULAR);
		unpopularMovie = new Movie("Pas populaire", Movie.UNPOPULAR);
	}
	
	
	@Test
	public void testAmount(){
		Rental child1day = new Rental(childMovie,1);
		Rental child3days = new Rental(childMovie,3);
		Rental child5days = new Rental(childMovie,5);
		Rental new1day = new Rental(newMovie,1);
		Rental new5days = new Rental(newMovie,5);
		Rental reg1day = new Rental(regularMovie,1);
		Rental reg2days = new Rental(regularMovie,2);
		Rental reg5days = new Rental(regularMovie,5);
		Rental unpo1day = new Rental(unpopularMovie, 1);
		Rental unpo187days = new Rental(unpopularMovie, 187);
		
		
		assertEquals(child1day.amount(),1.5,EPSILON);
		assertEquals(child1day.amount(),child3days.amount(),EPSILON);
		assertEquals(child5days.amount(),4.5,EPSILON);
		assertEquals(reg1day.amount(),2.0,0.0001);
		assertEquals(reg1day.amount(),reg2days.amount(),EPSILON);		
		assertEquals(reg5days.amount(),6.5,EPSILON);
		assertEquals(new1day.amount(),3.0,EPSILON);
		assertEquals(new5days.amount(),15.0,EPSILON);		
		assertEquals(unpo1day.amount(), 2.0, EPSILON);
		assertEquals(unpo187days.amount(), 2.0, EPSILON);

	}
	
	@Test
	public void testPoints(){
		Rental childrens = new Rental(childMovie,1);		
		Rental newrelease = new Rental(newMovie,1);	
		Rental regular = new Rental(regularMovie,5);
		Rental unpopular = new Rental(unpopularMovie, 187);
		
		assertEquals(newrelease.points(),2);	
		assertEquals(childrens.points(),1);	
		assertEquals(regular.points(),1);
		assertEquals(unpopular.points(), 3);
	}

	@Test
	public void testPriceCodeAmount(){
		newMovie.setPriceCode(new NewReleasePrice(), LocalDate.now().minusYears(2));
		newMovie.setPriceCode(new RegularPrice(), LocalDate.now().minusMonths(1));
		regularMovie.setPriceCode(new NewReleasePrice(), LocalDate.now().minusMonths(3));
		regularMovie.setPriceCode(new ChildrenPrice(), LocalDate.now().minusMonths(1));
		regularMovie.setPriceCode(new RegularPrice(), LocalDate.now());

		Rental newrelease = new Rental(newMovie, LocalDate.now().minusMonths(20), 2);
		Rental newrelease2 = new Rental(newMovie, LocalDate.now().minusDays(3), 4);
		Rental regular = new Rental(regularMovie, LocalDate.now().minusMonths(2), 5);
		Rental regular2 = new Rental(regularMovie, LocalDate.now().minusDays(20), 3);
		Rental regular3 = new Rental(regularMovie, LocalDate.now(), 6);

		assertEquals(newrelease.amount(),6.0, EPSILON);
		assertEquals(newrelease2.amount(), 5.0, EPSILON);
		assertEquals(regular.amount(),15.0, EPSILON);
		assertEquals(regular2.amount(), 1.5, EPSILON);
		assertEquals(regular3.amount(), 8.0, EPSILON);
	}

	@Test
	public void testPriceCodePoints(){
		newMovie.setPriceCode(new NewReleasePrice(), LocalDate.now().minusYears(2));
		newMovie.setPriceCode(new RegularPrice(), LocalDate.now().minusMonths(1));
		regularMovie.setPriceCode(new NewReleasePrice(), LocalDate.now().minusMonths(3));
		regularMovie.setPriceCode(new ChildrenPrice(), LocalDate.now().minusMonths(1));
		regularMovie.setPriceCode(new RegularPrice(), LocalDate.now());

		Rental newrelease = new Rental(newMovie, LocalDate.now().minusMonths(20), 2);
		Rental newrelease2 = new Rental(newMovie, LocalDate.now().minusDays(3), 4);
		Rental regular = new Rental(regularMovie, LocalDate.now().minusMonths(2), 5);
		Rental regular2 = new Rental(regularMovie, LocalDate.now().minusDays(20), 3);
		Rental regular3 = new Rental(regularMovie, LocalDate.now(), 6);

		assertEquals(newrelease.points(), 2);
		assertEquals(newrelease2.points(), 1);
		assertEquals(regular.points(), 2);
		assertEquals(regular2.points(), 1);
		assertEquals(regular3.points(), 1);
	}
	
	@After
	public void teardown() {
		childMovie = null;
		regularMovie=null;
		newMovie=null;
	}
}
