package model;

import model.exceptions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockLocation {
    private Integer alley;
    private Integer shelf;
    private Integer number;
    private Double buyingPrice;
    private Integer quantity;
    private Date expirationDate;


    // Constructor
    public StockLocation(Integer alley, Integer shelf, Integer number, Double buyingPrice, Integer quantity, Date expirationDate)
            throws AlleyException, ShelfException, NumberException, BuyingPriceException, QuantityException
    {
        setAlley(alley);
        setShelf(shelf);
        setNumber(number);
        setBuyingPrice(buyingPrice);
        setQuantity(quantity);
        this.expirationDate = expirationDate;
    }


    // Getters / Setters
    public Integer getAlley() { return alley; }
    public Integer getShelf() { return shelf; }
    public Integer getNumber() { return number; }
    public Double getBuyingPrice() { return buyingPrice; }
    public Integer getQuantity() { return quantity; }
    public Date getExpirationDate() { return expirationDate;}

    public void setAlley(Integer alley) throws AlleyException {
        if (alley < 1) throw new AlleyException(alley);
        else this.alley = alley;
    }
    public void setShelf(Integer shelf) throws ShelfException {
        if (shelf < 1) throw new ShelfException(shelf);
        else this.shelf = shelf;
    }
    public void setNumber(Integer number) throws NumberException {
        if (number < 1) throw new NumberException(number);
        else this.number = number;
    }
    public void setBuyingPrice(Double buyingPrice) throws BuyingPriceException {
        if (buyingPrice < 0) throw new BuyingPriceException(buyingPrice);
        else this.buyingPrice = buyingPrice;
    }
    public void setQuantity(Integer quantity) throws QuantityException {
        if (quantity < 0) throw new QuantityException(quantity);
        else this.quantity = quantity;
    }


    // Methods
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // Pas sur de +º+á
        return alley + ", " + shelf + ", " + number + ", " + buyingPrice + ", " + quantity + ", " + dateFormat.format(expirationDate);
    }
}
