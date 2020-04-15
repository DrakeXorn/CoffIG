package model.exceptions;

public class PackagingException extends Exception {
    private Double packagingErrone;

    public PackagingException(Double packagingErrone) {
        this.packagingErrone = packagingErrone;
    }

    public String getMessage(){
        return "La valeur " + packagingErrone + " proposée pour le packaging est invalide !";
    }
}
