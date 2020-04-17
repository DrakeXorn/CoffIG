package model;

public class Right {
    private Advantage advantage;
    private LoyaltyCard card;

    public Right(Advantage advantage, LoyaltyCard card) {
        this.advantage = advantage;
        this.card = card;
    }

    public Advantage getAdvantage() {
        return advantage;
    }
}
