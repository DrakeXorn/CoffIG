package dataAccess;

import model.Locality;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public interface UserDataAccess {
    ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException;
    int getLastCustomerId() throws AllDataException, ConnectionException;
}
