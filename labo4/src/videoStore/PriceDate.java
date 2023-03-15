package videoStore;

import java.time.LocalDate;

public class PriceDate implements Comparable{
    private Price price;
    private LocalDate date;

    public PriceDate(){

    }

    public Price getPrice(){
        return price;
    }

    public LocalDate getDate(){
        return date;
    }
    public PriceDate(Price newPrice, LocalDate newDate){
        price = newPrice;
        date = newDate;
    }
    @Override
    public int compareTo(Object o) {
        if(o instanceof PriceDate){
            PriceDate p = (PriceDate) o;
            return date.compareTo(p.getDate());
        }
        else if(o instanceof LocalDate){
            return date.compareTo((LocalDate) o);
        }
        throw new IllegalArgumentException();
    }
}
