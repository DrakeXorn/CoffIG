package test;

public class LoyaltyCardPointsCalculator {

    public Integer add(Integer loyaltyCardPoints, double orderPrice){
        return loyaltyCardPoints + ((int)(orderPrice) * 10);
    }

    public Integer subtract(Integer loyaltyCardPoints, Integer advantagePoints){
        return loyaltyCardPoints - advantagePoints;
    }


}
