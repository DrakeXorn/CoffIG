package business;

import dataAccess.FeatureDBAccess;
import dataAccess.FeatureDataAccess;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public class FeatureManager {
    private FeatureDataAccess dataAccessor;

    public FeatureManager() {
        dataAccessor = new FeatureDBAccess();
    }

    public ArrayList<String> getFeatures() throws AllDataException, ConnectionException {
        return dataAccessor.getFeatures();
    }
}
