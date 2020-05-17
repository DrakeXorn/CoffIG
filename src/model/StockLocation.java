package model;

import model.exceptions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class StockLocation {
    private Integer alley;
    private Integer shelf;
    private Integer number;
    private Double buyingPrice;
    private Integer quantity;
    private GregorianCalendar expirationDate;

    public StockLocation(Integer alley, Integer shelf, Integer number, Double buyingPrice, Integer quantity, GregorianCalendar expirationDate)
            throws IntegerInputException, DoubleInputException, DateException {
        setAlley(alley);
        setShelf(shelf);
        setNumber(number);
        setBuyingPrice(buyingPrice);
        setQuantity(quantity);
        setExpirationDate(expirationDate);
    }

    public Integer getAlley() {
        return alley;
    }

    public Integer getShelf() {
        return shelf;
    }

    public Integer getNumber() {
        return number;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public GregorianCalendar getExpirationDate() {
        return expirationDate;
    }
  
    public void setAlley(Integer alley) throws IntegerInputException {
        if (alley < 1 || alley > 6)
            throw new IntegerInputException(alley, "l'allée", "L'allée doit être comprise entre 1 et 6 !");
        this.alley = alley;
    }

    public void setShelf(Integer shelf) throws IntegerInputException {
        if (shelf < 1 || shelf > 6)
            throw new IntegerInputException(shelf, "l'étagère", "L'étagère doit être comprise entre 1 et 6 !");
        this.shelf = shelf;
    }

    public void setNumber(Integer number) throws IntegerInputException {
        if (number < 1 || number > 6)
            throw new IntegerInputException(number, "le nombre", "Le numéro doit être compris entre 1 et 6 !");
        this.number = number;
    }

    public void setBuyingPrice(Double buyingPrice) throws DoubleInputException {
        if (buyingPrice < 0)
            throw new DoubleInputException(buyingPrice, "le prix d'achat", "Le prix d'achat doit être positif !");
        this.buyingPrice = buyingPrice;
    }

    public void setQuantity(Integer quantity) throws IntegerInputException {
        if (quantity < 0)
            throw new IntegerInputException(quantity, "la quantité", "La quantité doit être positive !");
        this.quantity = quantity;
    }

    public void setExpirationDate(GregorianCalendar expirationDate) throws DateException {
        if (expirationDate.before(GregorianCalendar.getInstance()))
            throw new DateException(expirationDate, "La date de péremption doit être ultérieure à la date d'aujourd'hui !");
        this.expirationDate = expirationDate;
    }

    public void removeNToQuantity(int n) {
        quantity -= n;
    }

    public void addNToQuantity(int n) {
        quantity += n;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StockLocation && ((StockLocation) obj).getAlley().equals(alley) && ((StockLocation) obj).getShelf().equals(shelf) && ((StockLocation) obj).getNumber().equals(number);
    }

    public String toString() {
        // TODO : à modifier
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return alley + ", " + shelf + ", " + number + ", " + buyingPrice + ", " + quantity + ", " + dateFormat.format(expirationDate);
    }
}
