package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrinkOrdering {
    private Date date;
    private Boolean isToTakeAway;

    private Order order;


    // Constructor
    public DrinkOrdering(Date date, Boolean isToTakeAway, Order order) {
        this.date = date;
        this.isToTakeAway = isToTakeAway;
        this.order = order;
    }


    // Getters / Setters
    public Order getOrder() { return order;}
    public Date getDate() { return date; }
    public Boolean getToTakeAway() { return isToTakeAway; }


    // Methode
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); // Pas sur de +º+á
        return dateFormat.format(date) + ", " + isToTakeAway;
    }
}
