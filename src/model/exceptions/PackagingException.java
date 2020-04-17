package model.exceptions;

public class PackagingException extends Exception {
    private Double wrongPackaging;

    public PackagingException(Double wrongPackaging) {
        this.wrongPackaging = wrongPackaging;
    }

    public String getMessage(){
        return "La valeur " + wrongPackaging + " proposée pour le packaging est invalide !";
    }
}
