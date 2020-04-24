package model.exceptions;

public class StreetException extends Exception {
    private String wrongStreet;

    public StreetException(String wrongStreet) {
        this.wrongStreet = wrongStreet;
    }

    public String getMessage(){
        return "L'adresse est un champ obligatoire !\nFormat : nom de rue, numéro de maison\n" +
                "La valeur encodée '" + wrongStreet + "' est incorrecte !";
    }
}
