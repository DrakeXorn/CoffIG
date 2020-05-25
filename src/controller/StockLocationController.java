package controller;

import business.StockLocationManager;
import model.StockLocation;
import model.exceptions.*;

import java.util.ArrayList;

public class StockLocationController {
    private StockLocationManager manager;

    public StockLocationController() {
        manager = new StockLocationManager();
    }

    public ArrayList<StockLocation> getAllStockLocations() throws ConnectionException, DoubleInputException, IntegerInputException, AllDataException, DateException {
        return manager.getAllStockLocations();
    }

    public void updateStockLocation(StockLocation stockLocation) throws ConnectionException, ModifyException, BusinessException {
        manager.updateStockLocation(stockLocation);
    }
}
