package model;

import model.exceptions.*;

import java.util.Calendar;
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
                    throws DiscountException, PointsRequiredException, DateException {
        nbrAdvantages++;
        this.advantageID = nbrAdvantages;
        this.label = label;
        setDiscount(discount);
        this.startDate = startDate;
        setEndDate(endDate);
        setPointsRequired(pointsRequired);
    }

    public void setDiscount(Double discount) throws DiscountException {
        if (discount <= 0 || discount > 100)
            throw new DiscountException(discount);
        this.discount = discount;
    }

    public void setPointsRequired(Integer pointsRequired) throws PointsRequiredException {
        if (pointsRequired < 0)
            throw new PointsRequiredException(pointsRequired);
        this.pointsRequired = pointsRequired;
    }

    public void setEndDate(GregorianCalendar endDate) throws DateException {
        if (endDate.before(startDate))
            throw new DateException(endDate, startDate);
        this.endDate = endDate;
    }
}
