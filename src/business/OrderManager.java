package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;

public class OrderManager {
    private OrderDataAccess orderDataAccess;

    public OrderManager() {
        orderDataAccess = new OrderDBAccess();
    }
}
