package videoStore;

public abstract class Price {

    public Price(){ }

    public abstract double amount(int daysRented);

    public int points() {
        return 1;
    }
}
