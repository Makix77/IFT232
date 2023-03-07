package videoStore;

public class FrenchStatement extends Statement {
    @Override
    public String header(Customer cust) {
        return "Note de location pour " + cust.getName() + "\n";
    }

    @Override
    public String footer(Customer cust) {
        String footer = "";
        footer += "Total du : " + cust.getTotalAmount() + "\n";
        footer += "Vous avez obtenu " + cust.getTotalFrequentRenterPoints()
                + " points de location\n";
        return footer;
    }

    @Override
    public String rentalLine(Rental rent) {
        return "\t" + rent.getMovie().getTitle() + "\t"
                + rent.getMovieAmount() + "\n";
    }
}
