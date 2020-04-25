package model.exceptions;

public class EmailException extends Exception{
    private String wrongEmail;

    public EmailException(String wrongEmail) {
        this.wrongEmail = wrongEmail;
    }

    public String getMessage(){
        return "L'email est un champ obligatoire et a comme format xxxxxxx@xxxxxx.xxx";
    }
}
