package videoStore;

public abstract class Statement {

	protected Customer cust;

	public Statement(Customer c) {
		cust = c;
	}

	public String print() {

		String result = header();

		for (Rental each : cust.getRentals()) {

			result += rentalLine(each);
		}

		result += footer();

		return result;
	}

	protected abstract String header();

	protected abstract String rentalLine(Rental rent);

	protected abstract String footer();

}
