package videoStore;

public class UnpopularPrice extends Price{

    @Override
    public double amount(int daysRented) {
        return 2.0;
    }

    @Override
    public int points(){
        return 3;
    }
}
