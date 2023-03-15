package videoStore;

public class EnglishStatement extends Statement{

	public EnglishStatement(Customer c) {
		super(c);		
	}

	@Override
	protected String header() {
		
		return "Rental Record for " + cust.getName() + "\n";
	}

	@Override
	protected String rentalLine(Rental rent) {
		
		return "\t" + rent.getDate()+ "\t"+ rent.getMovie().getTitle() + "\t" + rent.amount() + "$\n";
	}

	@Override
	protected String footer() {
		
		return "Amount owed is " + cust.totalAmount() + "$\n" +
		"You earned " + cust.totalPoints() + " frequent renter points\n";
	}

}
