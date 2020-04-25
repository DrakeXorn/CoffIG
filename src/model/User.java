package model;

import model.exceptions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    private final static int YEAR_OLD_MIN = 16;
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
                String phone, Character gender) throws StringInputException, DateException, CharacterInputException {

        setPassword(password);
        setLastName(lastName);
        setFirstName(firstName);
        this.secondName = secondName;
        this.maidenName = maidenName;
        setBirthDate(birthDate);
        setStreetName(streetName);
        setEmail(email);
        setPhone(phone);
        setGender(gender);
        this.locality = locality;
        setUserID();
    }

    public void setUserID() {
        this.userID = this.lastName.substring(0, 4) + this.firstName.substring(0, 2)
                + this.phone.substring(phone.length() - 2) + nbrRegistered;
        nbrRegistered++;
    }

    public void setPassword(String password) throws StringInputException {
        if(password.isEmpty())
            throw new StringInputException(password, null, "Le mot de passe est un champ obligatoire !");
        this.password = password;
    }

    public void setLastName(String lastName) throws StringInputException {
        if(lastName.isEmpty())
            throw new StringInputException(lastName, null, "Le nom est un champ obligatoire !");
        if(!lastName.matches("^[a-zA-ZÀ-ÿ]+-?[a-zA-ZÀ-ÿ]*$"))
            throw new StringInputException(lastName, null, "Le nom se compose uniquement de lettres !");

        int size = lastName.length();
        if(size < 4){
            for(int i = 0; i < 4 - size; i++)
                lastName += "x";
        }
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) throws StringInputException {
        if(firstName.isEmpty())
            throw new StringInputException(firstName, null, "Le prénom est un champ obligatoire !");
        if(!firstName.matches("^[a-zA-ZÀ-ÿ]+-?[a-zA-ZÀ-ÿ]*$"))
           throw new StringInputException(firstName, null, "Le prénom se compose uniquement de lettres !");

        int size = firstName.length();
        if(size < 2){
            for(int i = 0; i < 2 - size; i++)
                firstName+= "x";
        }
        this.firstName = firstName;
    }

    public void setBirthDate(GregorianCalendar birthDate) throws DateException {
        GregorianCalendar today = (GregorianCalendar)Calendar.getInstance();
        if(birthDate.after(today))
            throw new DateException(birthDate, today);
        if(birthDate.get(Calendar.YEAR) + YEAR_OLD_MIN > today.get(Calendar.YEAR))
            throw new DateException(birthDate, new GregorianCalendar(today.get(Calendar.YEAR) - YEAR_OLD_MIN,
                    today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)));
        this.birthDate = birthDate;
    }

    public void setStreetName(String streetName) throws StringInputException {
        if(streetName.isEmpty())
            throw new StringInputException(streetName, null, "L'adresse est un champ obligatoire !\nFormat : nom de rue, numéro de maison\n" +
                    "La valeur encodée '" + streetName + "' est incorrecte !");
        this.streetName = streetName;
    }

    public void setEmail(String email) throws StringInputException {
        if(email.isEmpty())
            throw new StringInputException(email, null, "L'email est un champ obligatoire !");
        if(!email.matches("^[a-zA-ZÀ-ÿ0-9]+.?-?[a-zA-ZÀ-ÿ0-9]+@[a-zA-ZÀ-ÿ]+.[a-zA-ZÀ-ÿ]+$"))
            throw new StringInputException(email, null, "L'email a comme format xxxxxxx@xxxxxx.xxx !");
        this.email = email;
    }

    public void setPhone(String phone) throws StringInputException {
        if(phone.isEmpty())
            throw new StringInputException(phone , null, "Le numéro de téléphone est obligatoire !");
        if(phone.length() != 10)
            throw new StringInputException(phone, null, "Le numéro de téléphone doit comprendre exactement 10 chiffres !");
        if(!phone.matches("^\\d*$"))
            throw new StringInputException(phone, null, "Le numéro de téléphone ne peut pas contenir de </> et de <.> !");
        this.phone = phone;
    }

    public void setGender(Character gender) throws CharacterInputException {
        if(Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F')
            throw new CharacterInputException(gender, "Le genre", "Le genre doit être M ou F !");
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public String toString (){
        return firstName + " " + lastName + " (" + userID + ")" +
                (gender == 'F' ? " née le " : " né le ") + birthDate.get(Calendar.DAY_OF_MONTH)
                + "/" + (birthDate.get(Calendar.MONTH ) + 1) + "/" + birthDate.get(Calendar.YEAR) +
                " et habitant " + streetName + " " + locality + " a l'email " + email +
                " et le numéro " + phone;
    }
}
