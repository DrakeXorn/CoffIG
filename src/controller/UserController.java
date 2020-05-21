package controller;

import business.UserManager;
import model.Locality;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public class UserController {
    private UserManager manager;

    public ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException {
        return manager.getAllLocalities();
    }

    public int getLastCustomerId() throws AllDataException, ConnectionException {
        return manager.getLastCustomerId();
    }
}
