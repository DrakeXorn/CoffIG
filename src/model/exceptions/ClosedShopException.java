package model.exceptions;

public class ClosedShopException extends Exception {
    private String message;

    public ClosedShopException() {
        message = "Le bar à cafés est fermé. Revenez demain 😁";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
