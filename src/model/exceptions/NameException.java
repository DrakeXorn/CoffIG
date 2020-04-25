package model.exceptions;

public class NameException extends Exception {
    private String wrongName;

    public NameException(String wrongName) {
        this.wrongName = wrongName;
    }

    public String getMessage(){
        return "Le nom est un champ obligatoire et se compose uniquement de lettres !";
    }
}
