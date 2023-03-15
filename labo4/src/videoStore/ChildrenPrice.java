package videoStore;

public class ChildrenPrice extends Price {
    @Override
    public double amount(int daysRented){
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}
