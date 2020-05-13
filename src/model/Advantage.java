package model;

import model.exceptions.*;

import java.util.GregorianCalendar;

public class Advantage {
    private static int nbrAdvantages = 0;
    private Integer advantageID;
    private String label;
    private Double discount;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private Integer pointsRequired;

    public Advantage(String label, Double discount,
                     GregorianCalendar startDate, GregorianCalendar endDate, Integer pointsRequired)
                    throws IntegerInputException, DoubleInputException, DateException {
        nbrAdvantages++;
        this.advantageID = nbrAdvantages;
        this.label = label;
        setDiscount(discount);
        this.startDate = startDate;
        setEndDate(endDate);
        setPointsRequired(pointsRequired);
    }

    public void setDiscount(Double discount) throws DoubleInputException {
        if (discount <= 0 || discount > 100)
            throw new DoubleInputException(discount, "la remise", "Elle doit être comprise entre 0 et 100%");
        this.discount = discount;
    }

    public void setPointsRequired(Integer pointsRequired) throws IntegerInputException {
        if (pointsRequired < 0)
            throw new IntegerInputException(pointsRequired, "les points requis", "Les points requis doivent être positif !");
        this.pointsRequired = pointsRequired;
    }

    public void setEndDate(GregorianCalendar endDate) throws DateException {
        if (endDate.before(startDate))
            throw new DateException(endDate, "La date de fin ne doit pas se trouver avant la date de début !");
        this.endDate = endDate;
    }
}
