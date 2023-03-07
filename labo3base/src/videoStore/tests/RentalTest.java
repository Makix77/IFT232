package videoStore.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import videoStore.Movie;
import videoStore.Rental;

public class RentalTest {

    private Rental currentRental;

    private Movie movies[] = new Movie[3];

    private double delta = 0.001;

    @Before
    public void setup(){
        movies[0] = new Movie("Film 1", Movie.CHILDRENS);
        movies[1] = new Movie("Film 2", Movie.NEW_RELEASE);
        movies[2] = new Movie("Film 3", Movie.REGULAR);

        currentRental = new Rental(movies[0], 2);
    }

    @Test
    public void testGetMovieAmount(){
        assertEquals(currentRental.getMovieAmount(), 1.5, delta);

        currentRental = new Rental(movies[0], 4);
        assertEquals(currentRental.getMovieAmount(), 3.0, delta);

        currentRental = new Rental(movies[1], 2);
        assertEquals(currentRental.getMovieAmount(), 6.0, delta);

        currentRental = new Rental(movies[2], 2);
        assertEquals(currentRental.getMovieAmount(), 2.0, delta);

        currentRental = new Rental(movies[2], 4);
        assertEquals(currentRental.getMovieAmount(), 5.0, delta);
    }

    @Test
    public void testGetFrequentRenterPoints(){
        assertEquals(currentRental.getFrequentRenterPoints(), 1);

        currentRental = new Rental(movies[1], 2);
        assertEquals(currentRental.getFrequentRenterPoints(), 2);

        currentRental = new Rental(movies[2], 2);
        assertEquals(currentRental.getFrequentRenterPoints(), 1);
    }

    @After
    public void teardown() {
        currentRental = null;
        movies = null;
    }
}
