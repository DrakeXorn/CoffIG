package business;

import dataAccess.CustomerDataAccess;
import dataAccess.UserDataAccess;
import model.Locality;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public class UserManager {
    private UserDataAccess dao;

    public int getLastCustomerId() throws AllDataException, ConnectionException {
        return dao.getLastCustomerId();
    }
    public ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException {
        return dao.getAllLocalities();
    }
}
