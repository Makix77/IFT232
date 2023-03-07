package videoStore;

public class EnglishStatement extends Statement{
    @Override
    public String header(Customer cust) {
        return "Rental Record for " + cust.getName() + "\n";
    }

    @Override
    public String footer(Customer cust) {
        String footer = "";
        footer += "Amount owed is " + cust.getTotalAmount() + "\n";
        footer += "You earned " + cust.getTotalFrequentRenterPoints()
                + " frequent renter points\n";
        return footer;
    }

    @Override
    public String rentalLine(Rental rent) {
        return "\t" + rent.getMovie().getTitle() + "\t"
                + rent.getMovieAmount() + "\n";
    }
}
