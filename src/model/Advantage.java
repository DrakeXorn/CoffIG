package model;

import model.exceptions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Advantage {
    private Integer advantageID;
    private String label;
    private Double discount;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private Integer pointsRequired;

    public Advantage(Integer advantageID, String label, Double discount,
                     GregorianCalendar startDate, GregorianCalendar endDate, Integer pointsRequired)
                    throws DiscountException, PointsRequiredException, DateException{
        this.advantageID = advantageID;
        this.label = label;
        setDiscount(discount);
        this.startDate = startDate;
        setEndDate(endDate);
        setPointsRequired(pointsRequired);
    }

    public void setDiscount(Double discount) throws DiscountException{
        if(discount > 0 && discount <= 100)
            this.discount = discount;
        else
            throw new DiscountException(discount);
    }

    public void setPointsRequired(Integer pointsRequired) throws PointsRequiredException {
        if(pointsRequired > 0)
            this.pointsRequired = pointsRequired;
        else
            throw new PointsRequiredException(pointsRequired);
    }

    public void setEndDate(GregorianCalendar endDate) throws DateException{
        if(endDate.get(Calendar.YEAR) < startDate.get(Calendar.YEAR))
            throw new DateException(endDate);
        else
            if(endDate.get(Calendar.MONTH) < startDate.get(Calendar.MONTH))
                throw new DateException(endDate);
            else
                if(endDate.get(Calendar.DAY_OF_MONTH) < startDate.get(Calendar.DAY_OF_MONTH))
                    throw new DateException(endDate);
                else
                    this.endDate = endDate;
    }

    // pas de addRight car aucun intérêt de les retenir selon moi
}
