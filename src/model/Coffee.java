package model;

import model.exceptions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Coffee {
    private static int nbrCoffees = 1;
    private Integer coffeeID;
    private Integer intensity;
    private String label;
    private String originCountry;
    private String recommendedConsumingMoment;
    private Double weightNeededForPreparation;
    private Double price;
    private Double packaging;
    private GregorianCalendar discoveryDate;
    private Boolean isInGrains, isEnvironmentFriendly;
    private StockLocation stockLocation;

    private ArrayList<String> features;

    public Coffee(String label, String originCountry, Integer intensity,
                  Double weightNeededForPreparation, GregorianCalendar discoveryDate, Boolean isInGrains,
                  Boolean isEnvironmentFriendly, Double price, Double packaging,
                  String recommendedConsumingMoment, StockLocation stockLocation)
            throws IntensityException, WeightException, PriceException, PackagingException {
        coffeeID = nbrCoffees;
        nbrCoffees++;
        this.label = label;
        this.originCountry = originCountry;
        setIntensity(intensity);
        setWeightNeededForPreparation(weightNeededForPreparation);
        this.discoveryDate = discoveryDate;
        this.isInGrains = isInGrains;
        this.isEnvironmentFriendly = isEnvironmentFriendly;
        setPrice(price);
        setPackaging(packaging);
        this.recommendedConsumingMoment = recommendedConsumingMoment;
        this.stockLocation = stockLocation;

        features = new ArrayList<>();
    }

    public void setIntensity(Integer intensity) throws IntensityException {
        if (intensity <= 0 || intensity >= 6)
            throw new IntensityException(intensity);
        this.intensity = intensity;
    }

    public void setWeightNeededForPreparation(Double weightNeededForPreparation) throws WeightException {
        if (weightNeededForPreparation <= 0)
            throw new WeightException(weightNeededForPreparation);
        this.weightNeededForPreparation = weightNeededForPreparation;
    }

    public void setPrice(Double price) throws PriceException {
        if (price <= 0)
            throw new PriceException(price);
        this.price = price;
    }

    public void setPackaging(Double packaging) throws PackagingException {
        if (packaging <= 0)
            throw new PackagingException(packaging);
        this.packaging = packaging;
    }

    public void addFeature(String feature) {
        features.add(feature);
    }

    public String toString(){
        StringBuilder description = new StringBuilder();

        description.append("Laissez-vous tenter par le café ").append(label);
        description.append(isInGrains ? " en grain" : " moulu");
        description.append(" originaire de ").append(originCountry);
        if (discoveryDate != null)
            description.append(" et découvert en ").append(discoveryDate.get(Calendar.YEAR));
        description.append(".\nCe café ").append(isEnvironmentFriendly ? "biologique" : "non biologique");
        for (int iFeature = 0; iFeature < features.size(); iFeature++){
            description.append(iFeature == 0 ? " est " : iFeature == features.size() - 1 ? " et " : ", ");
            description.append(features.get(iFeature));
        }
        description.append(".\nSon intensité de ").append(intensity).append(intensity == 1 ? " grain" : " grains").append(" sur 5 ");
        if (recommendedConsumingMoment != null)
            description.append("le rend idéal à savourer durant ").append(recommendedConsumingMoment).append(".");
        return description.toString();
    }
}
