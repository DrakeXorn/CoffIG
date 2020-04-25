package model.exceptions;

public class FirstNameException extends Exception {
    private String wrongName;

    public FirstNameException(String wrongName) {
        this.wrongName = wrongName;
    }

    public String getMessage(){
        return "Le prénom est un champ obligatoire et se compose uniquement de lettres !";
    }
}
