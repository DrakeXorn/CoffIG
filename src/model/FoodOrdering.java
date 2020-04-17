package model;

public class FoodOrdering {
    private Food food;
    private Order order;
    private Integer nbrPieces;
    private Double sellingPrice;

    public FoodOrdering(Food food, Order order, Integer nbrPieces, Double sellingPrice) {
        this.food = food;
        this.order = order;
        order.addFoodOrdering(this);
        this.nbrPieces = nbrPieces;
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(food.toString());

        res.append("\t").append(nbrPieces).append("\t").append(sellingPrice);

        return res.toString();
    }
}
