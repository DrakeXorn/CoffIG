package dataAccess;

import model.exceptions.AddDataException;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public interface FeatureDataAccess {
    ArrayList<String> getFeatures() throws AllDataException, ConnectionException;
    void addFeature(String feature) throws AddDataException, ConnectionException;
}
