package videoStore;

public class NewReleasePrice extends Price {
    @Override
    public double amount(int daysRented){
        return 3 * daysRented;
    }

    @Override
    public int points(){
        return 2;
    }
}
