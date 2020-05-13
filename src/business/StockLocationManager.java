package business;

import dataAccess.StockLocationDBAccess;
import dataAccess.StockLocationDataAccess;
import model.StockLocation;
import model.exceptions.*;

import java.util.ArrayList;

public class StockLocationManager {
    private StockLocationDataAccess dataAccessor;

    public StockLocationManager() {
        dataAccessor = new StockLocationDBAccess();
    }

    public ArrayList<StockLocation> getAllStockLocations() throws ConnectionException, DoubleInputException, IntegerInputException, AllDataException, DateException {
        return dataAccessor.getAllStockLocations();
    }

    public void updateStockLocation(StockLocation stockLocation) throws ConnectionException, AddDataException {
        dataAccessor.updateStockLocation(stockLocation);
    }
}
