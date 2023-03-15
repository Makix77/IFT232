package videoStore;

public class FrenchStatement extends Statement{

	public FrenchStatement(Customer c) {
		super(c);		
	}

	@Override
	protected String header() {
		
		return "Facture de location pour " + cust.getName() + "\n";
	}

	@Override
	protected String rentalLine(Rental rent) {
		
		return "\t" + rent.getDate()+"\t"+rent.getMovie().getTitle() + "\t" + rent.amount() + "$\n";
	}

	@Override
	protected String footer() {
		
		return "Montant dû: " + cust.totalAmount() + "$\n" +
		"Vous avez obtenu " + cust.totalPoints() + " points de fidélité\n";
	}
}
