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

    public void updateStockLocation(StockLocation stockLocation) throws ConnectionException, ModifyException, BusinessException {
        if (stockLocation == null)
            throw new BusinessException("StockLocationManager", "La mise à jour de l'emplacement de stock", "L'emplacement de stock");

        dataAccessor.updateStockLocation(stockLocation);
    }
}
