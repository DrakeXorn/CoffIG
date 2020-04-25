package model.exceptions;

public class PhoneException extends Exception {
    private String wrongPhone;

    public PhoneException(String wrongPhone) {
        this.wrongPhone = wrongPhone;
    }

    public String getMessage(){
        return "Le numéro de téléphone est invalide !\nLe numéro de téléphone est obligatoire et doit comprendre exactement 10 chiffres sans / et sans .";
    }
}
