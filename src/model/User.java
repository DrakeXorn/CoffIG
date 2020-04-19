package model;

import model.exceptions.GenderException;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    private static int nbrRegistered = 0;
    private String userID;
    private String password;
    private String lastName, firstName, secondName, maidenName;
    private GregorianCalendar birthDate;
    private String streetName;
    private String email;
    private String phone;
    private Character gender;
    private Locality locality;

    public User(String password, String lastName, String firstName, String secondName,
                String maidenName, GregorianCalendar birthDate, String streetName, Locality locality, String email,
                String phone, Character gender) throws GenderException {
        userID = lastName.substring(0, 3) + firstName.substring(0, 1) + phone.substring(phone.length() - 2)
                + nbrRegistered;
        nbrRegistered++;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.maidenName = maidenName;
        this.birthDate = birthDate;
        this.streetName = streetName;
        this.email = email;
        this.phone = phone;
        setGender(gender);
        this.locality = locality;
    }

    public void setGender(Character gender) throws GenderException{
        if(Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F')
            throw new GenderException(gender);
        this.gender = gender;
    }

    public String getUserID() {
        return userID;
    }

    public String getPhone() {
        return phone;
    }

    public String toString (){
        return firstName + " " + lastName + " (" + userID + ")" +
                (gender == 'F' ? " née le " : "né le ") + birthDate.get(Calendar.DAY_OF_MONTH)
                + "/" + birthDate.get(Calendar.MONTH + 1) + "/" + birthDate.get(Calendar.YEAR) +
                " et habitant " + streetName + " " + locality + " a l'email " + email +
                " et le numéro " + phone;
    }
}
