package model.exceptions;

public class PasswordException extends Exception {
    private String wrongPassword;

    public PasswordException(String wrongPassword) {
        this.wrongPassword = wrongPassword;
    }

    public String getMessage(){
        return "Le mot de passe est un champ obligatoire !";
    }
}
