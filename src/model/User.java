package model;

import controller.CustomerController;
import model.exceptions.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    private final static int AGE_MIN = 16;
    private Integer userID;
    private String password;
    private String lastName, firstName, secondName, maidenName;
    private GregorianCalendar birthDate;
    private String streetName;
    private String email;
    private String phone;
    private Character gender;
    private Locality locality;
    private CustomerController controller;

    // pour la création avant l'insertion
    public User(String password, String lastName, String firstName, String secondName, String maidenName,
                GregorianCalendar birthDate, String streetName, Locality locality, String email, String phone, Character gender)
            throws StringInputException, DateException, CharacterInputException, AllDataException, ConnectionException {
        setUserID();
        setPassword(password);
        setLastName(lastName);
        setFirstName(firstName);
        setSecondName(secondName);
        setMaidenName(maidenName);
        setBirthDate(birthDate);
        setStreetName(streetName);
        setEmail(email);
        setPhone(phone);
        setGender(gender);
        this.locality = locality;
    }

    // pour la récupération et la modification d'un user
    public User(Integer userID, String password, String lastName, String firstName, String secondName, String maidenName,
                GregorianCalendar birthDate, String streetName, Locality locality, String email, String phone, Character gender)
            throws StringInputException, DateException, CharacterInputException, AllDataException, ConnectionException {
        this(password, lastName, firstName, secondName, maidenName,birthDate, streetName, locality, email, phone, gender);
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Character getGender() {
        return gender;
    }

    public Locality getLocality() {
        return locality;
    }

    public String getIdentity() {
        return firstName + " " + lastName;
    }

    public void setUserID() throws AllDataException, ConnectionException {
        if(userID == null) {
            controller = new CustomerController();
            this.userID = controller.getLastCustomerId() + 1;
        }
    }

    public void setPassword(String password) throws StringInputException {

        if (password.isEmpty())
            throw new StringInputException(password, null, "Le mot de passe est un champ obligatoire !");
        this.password = password;
    }

    public void setLastName(String lastName) throws StringInputException {
        if (lastName.isEmpty())
            throw new StringInputException(lastName, null, "Le nom est un champ obligatoire !");
        if (!lastName.matches("^[a-zA-ZÀ-ÿ]+-?[a-zA-ZÀ-ÿ]*$"))
            throw new StringInputException(lastName, null, "Le nom se compose uniquement de lettres !");
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) throws StringInputException {
        if (firstName.isEmpty())
            throw new StringInputException(firstName, null, "Le prénom est un champ obligatoire !");
        if(!firstName.matches("^[a-zA-ZÀ-ÿ]+-?[a-zA-ZÀ-ÿ]*$"))
           throw new StringInputException(firstName, null, "Le prénom se compose uniquement de lettres !");
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) throws StringInputException {
        if(secondName == null || secondName.isEmpty())
            this.secondName = null;
        else {
            if(!secondName.matches("^[a-zA-ZÀ-ÿ]+-?[a-zA-ZÀ-ÿ]*$"))
                throw new StringInputException(secondName, null, "Le second prénom se compose uniquement de lettres !");
            this.secondName = secondName;
        }
    }

    public void setMaidenName(String maidenName) throws StringInputException {
        if(maidenName == null || maidenName.isEmpty())
            this.maidenName = null;
        else {
            if(!maidenName.matches("^[a-zA-ZÀ-ÿ]+-?[a-zA-ZÀ-ÿ]*$"))
                throw new StringInputException(maidenName, null, "Le nom de jeune fille se compose uniquement de lettres !");
            this.maidenName = maidenName;
        }    }

    public void setBirthDate(GregorianCalendar birthDate) throws DateException {
        GregorianCalendar today = (GregorianCalendar)Calendar.getInstance();
        if(birthDate.after(today))
            throw new DateException(birthDate, "La date de naissance ne doit pas exécer aujourd'hui !");
        if(Period.between(LocalDate.ofInstant(birthDate.toInstant(), birthDate.getTimeZone().toZoneId()), LocalDate.now()).getYears() < AGE_MIN)
            throw new DateException(birthDate, "L'utilisateur doit avoir minimum 16 ans pour s'inscrire !");
        this.birthDate = birthDate;
    }

    public void setStreetName(String streetName) throws StringInputException {
        if(streetName.isEmpty())
            throw new StringInputException(streetName, null, "L'adresse est un champ obligatoire !");
        this.streetName = streetName;
    }

    public void setEmail(String email) throws StringInputException {
        if (email.isEmpty())
            throw new StringInputException(email, null, "L'email est un champ obligatoire !");
        if (!email.matches("^[a-zA-ZÀ-ÿ0-9]+.?-?[a-zA-ZÀ-ÿ0-9]+@[a-zA-ZÀ-ÿ]+.[a-zA-ZÀ-ÿ]+$"))
            throw new StringInputException(email, null, "L'email a comme format xxxxxxx@xxxxxx.xxx !");
        this.email = email;
    }

    public void setPhone(String phone) throws StringInputException {
        if(phone.isEmpty())
            throw new StringInputException(phone , null, "Le numéro de téléphone est un champ obligatoire !");
        if(phone.length() != 10)
            throw new StringInputException(phone, null, "Le numéro de téléphone se compose d'exactement 10 chiffres !");
        if(!phone.matches("^\\d*$"))
            throw new StringInputException(phone, null, "Le numéro de téléphone ne peut pas contenir de '/' et de '.' !");
        this.phone = phone;
    }

    public void setGender(Character gender) throws CharacterInputException {
        if(Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F')
            throw new CharacterInputException(gender, "le genre", "Le genre doit être M ou F !");
        this.gender = gender;
    }

    public String description () {
        return toString() +
                (gender == 'F' ? " née le " : " né le ") + birthDate.get(Calendar.DAY_OF_MONTH)
                + "/" + (birthDate.get(Calendar.MONTH ) + 1) + "/" + birthDate.get(Calendar.YEAR) +
                " et habitant " + streetName + " " + locality + " a l'email " + email +
                " et le numéro " + phone;
    }

    public String toString(){
        return getIdentity() + " (" + userID + ")";
    }
}
