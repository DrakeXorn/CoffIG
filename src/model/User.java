package model;

import model.exceptions.GenderException;

import java.util.GregorianCalendar;

public class User {
    private Integer userID;
    private String password;
    private String lastName, firstName, secondName, maidenName;
    private GregorianCalendar birthDate;
    private String streetName;
    private String email;
    private String phone;
    private Character gender;
    private Locality locality;

    public User(Integer userID, String password, String lastName, String firstName, String secondName,
                String maidenName, GregorianCalendar birthDate, String streetName, String email,
                String phone, Character gender, Locality locality) {
        this.userID = userID;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.maidenName = maidenName;
        this.birthDate = birthDate;
        this.streetName = streetName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.locality = locality;
    }

    public void setGender(Character gender) throws GenderException{
        if(Character.toUpperCase(gender) == 'M' || Character.toUpperCase(gender) == 'F')
            this.gender = gender;
        else
            throw new GenderException(gender);
    }

    public String getPhone() {
        return phone;
    }
}
