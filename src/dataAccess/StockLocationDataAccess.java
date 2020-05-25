package dataAccess;

import model.StockLocation;
import model.exceptions.*;

import java.util.ArrayList;

public interface StockLocationDataAccess {
    ArrayList<StockLocation> getAllStockLocations() throws ConnectionException, AllDataException, DoubleInputException, IntegerInputException, DateException;
    void updateStockLocation(StockLocation stockLocation) throws ConnectionException, ModifyException;
}
