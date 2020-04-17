package model.exceptions;

public class GenderException extends Exception{
    private Character wrongGender;

    public GenderException(Character wrongGender) {
        this.wrongGender = wrongGender;
    }

    public String getMessage(){
        return "La valeur " + wrongGender + " proposée pour le genre est invalide !";
    }
}
