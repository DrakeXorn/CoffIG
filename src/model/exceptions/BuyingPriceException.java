package model.exceptions;

public class BuyingPriceException extends Exception {
    private Double buyingPriceErrone;

    public BuyingPriceException(Double buyingPriceErrone) {
        this.buyingPriceErrone = buyingPriceErrone;
    }
    public String getMessage() {
        return	"The value ("+buyingPriceErrone+") proposed for buying Price is invalid !";
    }
}
