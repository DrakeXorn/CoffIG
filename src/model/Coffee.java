package model;

import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Coffee {
    private Integer coffeeID;
    private Integer intensity;
    private String label;
    private String originCountry;
    private String recommendedConsumingMoment;
    private Double weightNeededForPreparation;
    private Double price;
    private Double packaging;
    private Integer discoveryYear;
    private Boolean inGrains, environmentFriendly;
    private StockLocation stockLocation;
    private ArrayList<String> features;

    public Coffee(Integer coffeeID, String label, String originCountry, Integer intensity,
                  Double weightNeededForPreparation, Boolean inGrains,
                  Boolean environmentFriendly, Double price, Double packaging,
                  StockLocation stockLocation)
            throws IntegerInputException, DoubleInputException {
        this.coffeeID = coffeeID;
        this.label = label;
        this.originCountry = originCountry;
        setIntensity(intensity);
        setWeightNeededForPreparation(weightNeededForPreparation);
        this.inGrains = inGrains;
        this.environmentFriendly = environmentFriendly;
        setPrice(price);
        setPackaging(packaging);
        this.stockLocation = stockLocation;

        features = new ArrayList<>();
    }

    public void setRecommendedConsumingMoment(String recommendedConsumingMoment) {
        this.recommendedConsumingMoment = recommendedConsumingMoment;
    }

    public void setDiscoveryYear(Integer discoveryYear) throws DateException {
        if (discoveryYear > (GregorianCalendar.getInstance().get(GregorianCalendar.YEAR)))
            throw new DateException(new GregorianCalendar(discoveryYear, GregorianCalendar.JANUARY, 1), "La date doit être antérieure à la date d'aujourd'hui !");
        this.discoveryYear = discoveryYear;
    }

    public void setIntensity(Integer intensity) throws IntegerInputException {
        if (intensity <= 0 || intensity >= 6)
            throw new IntegerInputException(intensity, "l'intensité", "L'intensité doit être comprise entre 1 et 5 !");
        this.intensity = intensity;
    }

    public void setWeightNeededForPreparation(Double weightNeededForPreparation) throws DoubleInputException {
        if (weightNeededForPreparation < 0 || weightNeededForPreparation > 50)
            throw new DoubleInputException(weightNeededForPreparation, "le poids nécessaire pour la préparation", "Le poids nécessaire doit être compris entre 0 et 50 grammes !");
        this.weightNeededForPreparation = weightNeededForPreparation;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price < 1 || price > 2000)
            throw new DoubleInputException(price, "le prix", "Le prix doit être compris entre 0 et 2000€ !");
        this.price = price;
    }

    public void setPackaging(Double packaging) throws DoubleInputException {
        if (packaging < 1 || packaging > 50)
            throw new DoubleInputException(packaging, "le packaging", "Le packaging doit compris entre 0 et 50kg !");

        this.packaging = packaging;
    }

    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    public Integer getCoffeeID() {
        return coffeeID;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public String getLabel() {
        return label;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public String getRecommendedConsumingMoment() {
        return recommendedConsumingMoment;
    }

    public Double getWeightNeededForPreparation() {
        return weightNeededForPreparation;
    }

    public Double getPrice() {
        return price;
    }

    public Double getPackaging() {
        return packaging;
    }

    public Integer getDiscoveryYear() {
        return discoveryYear;
    }

    public Boolean isInGrains() {
        return inGrains;
    }

    public Boolean isEnvironmentFriendly() {
        return environmentFriendly;
    }

    public StockLocation getStockLocation() {
        return stockLocation;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public String toString() {
        StringBuilder description = new StringBuilder();

        description.append("Laissez-vous tenter par le café ").append(label);
        description.append(" originaire de/du/d' ").append(originCountry);
        if (discoveryYear != null)
            description.append(" et découvert en ").append(discoveryYear);
        description.append(".\nCe café").append(inGrains ? " en grains" : " moulu").append(" est cultivé de manière");
        description.append(environmentFriendly ? "" : " non").append(" respectueuse de l'environnement.\n");
        for (int iFeature = 0; iFeature < features.size(); iFeature++) {
            description.append(iFeature == 0 ? "Il est " : iFeature == features.size() - 1 ? " et " : ", ");
            description.append(features.get(iFeature));
            if (iFeature == features.size() - 1)
                description.append(".\n");
        }
        description.append("Son intensité de ").append(intensity).append(intensity == 1 ? " grain" : " grains").append(" sur 5");
        description.append(" le rend idéal à savourer ").append(recommendedConsumingMoment != null ? recommendedConsumingMoment : "à toute heure de la journée");
        description.append(".");

        return description.toString();
    }
}
