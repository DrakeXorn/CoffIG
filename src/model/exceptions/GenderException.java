package model.exceptions;

public class GenderException extends Exception{
    private Character genderErrone;

    public GenderException(Character genderErrone) {
        this.genderErrone = genderErrone;
    }

    public String getMessage(){
        return "La valeur " + genderErrone + " proposée pour le genre est invalide !";
    }
}
