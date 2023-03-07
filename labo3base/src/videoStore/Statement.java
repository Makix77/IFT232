package videoStore;

public abstract class Statement {
    public String print(Customer cust){
        String statement = "";
        statement += header(cust);
        for(Rental each : cust.getRentals()){
            statement += rentalLine(each);
        }
        statement += footer(cust);
        return statement;
    }

    public abstract String header(Customer cust);
    public abstract String footer(Customer cust);
    public abstract String rentalLine(Rental rent);
}
