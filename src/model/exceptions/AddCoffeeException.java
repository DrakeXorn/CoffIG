package model.exceptions;

public class AddCoffeeException extends Exception {
    private String message;

    public AddCoffeeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de l'inscription du café dans la base de données :\n" + message;
    }
}
