package model;

import model.exceptions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class StockLocation {
    private Integer alley;
    private Integer shelf;
    private Integer number;
    private Double buyingPrice;
    private Integer quantity;
    private GregorianCalendar expirationDate;

    public StockLocation(Integer alley, Integer shelf, Integer number, Double buyingPrice, Integer quantity, GregorianCalendar expirationDate)
            throws AlleyException, ShelfException, NumberException, PriceException, QuantityException {
        setAlley(alley);
        setShelf(shelf);
        setNumber(number);
        setBuyingPrice(buyingPrice);
        setQuantity(quantity);
        this.expirationDate = expirationDate;
    }

    public void setAlley(Integer alley) throws AlleyException {
        if (alley < 1)
            throw new AlleyException(alley);
        this.alley = alley;
    }

    public void setShelf(Integer shelf) throws ShelfException {
        if (shelf < 1)
            throw new ShelfException(shelf);
        this.shelf = shelf;
    }

    public void setNumber(Integer number) throws NumberException {
        if (number < 1)
            throw new NumberException(number);
        this.number = number;
    }

    public void setBuyingPrice(Double buyingPrice) throws PriceException {
        if (buyingPrice < 0)
            throw new PriceException(buyingPrice);
        this.buyingPrice = buyingPrice;
    }

    public void setQuantity(Integer quantity) throws QuantityException {
        if (quantity < 0)
            throw new QuantityException(quantity);
        this.quantity = quantity;
    }

    public String toString() {
        // TODO : à modifier
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return alley + ", " + shelf + ", " + number + ", " + buyingPrice + ", " + quantity + ", " + dateFormat.format(expirationDate);
    }
}
