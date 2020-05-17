package controller;

import business.FeatureManager;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public class FeatureController {
    private FeatureManager manager;

    public FeatureController() {
        manager = new FeatureManager();
    }

    public ArrayList<String> getFeatures() throws AllDataException, ConnectionException {
        return manager.getFeatures();
    }
}
