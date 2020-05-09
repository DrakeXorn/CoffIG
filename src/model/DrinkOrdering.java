package model;

public class DrinkOrdering {
    private Drink drink;
    private Order order;
    private Integer nbrPieces;
    private Double sellingPrice;

    public DrinkOrdering(Drink drink, Order order, Integer nbrPieces, Double sellingPrice) {
        this.drink = drink;
        this.order = order;
        order.addDrinkOrdering(this);
        this.nbrPieces = nbrPieces;
        this.sellingPrice = sellingPrice;
    }

    public Drink getDrink() {
        return drink;
    }

    public Order getOrder() {
        return order;
    }

    public Integer getNbrPieces() {
        return nbrPieces;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(drink.toString());

        res.append("\t").append(nbrPieces).append("\t").append(sellingPrice);

        return res.toString();
    }
}
