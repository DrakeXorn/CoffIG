package model.exceptions;

public class CharacterInputException extends Exception {
    private Character wrongValue;
    private String nameValue;
    private String precision;

    public CharacterInputException(Character wrongValue, String nameValue, String precision) {
        this.wrongValue = wrongValue;
        this.nameValue = nameValue;
        this.precision = precision;
    }

    public CharacterInputException(Character wrongValue, String nameValue) {
        this(wrongValue, nameValue, "");
    }

    public String getMessage() {
        return	"La valeur ("+ wrongValue +") proposée pour "+ nameValue +" est invalide !\n" + precision;
    }
}
