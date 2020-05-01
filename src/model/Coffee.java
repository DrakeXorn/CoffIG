package model;

import model.exceptions.*;

import java.util.ArrayList;

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
    private Integer discoveryYear;
    private Boolean inGrains, environmentFriendly;
    private StockLocation stockLocation;

    private ArrayList<String> features;

    public Coffee(Integer coffeeID, String label, Integer intensity,
                  Double weightNeededForPreparation, Boolean inGrains,
                  Boolean environmentFriendly, Double price, Double packaging,
                  StockLocation stockLocation)
            throws IntegerInputException, DoubleInputException {
        this(label, null, intensity, weightNeededForPreparation, null, inGrains, environmentFriendly, price,
                packaging, null, stockLocation);
        if (coffeeID > nbrCoffees) {
            this.coffeeID = coffeeID;
            nbrCoffees = coffeeID + 1;
        }
    }

    public Coffee(String label, String originCountry, Integer intensity,
                  Double weightNeededForPreparation, Integer discoveryYear, Boolean inGrains,
                  Boolean environmentFriendly, Double price, Double packaging,
                  String recommendedConsumingMoment, StockLocation stockLocation)
            throws IntegerInputException, DoubleInputException {
        coffeeID = nbrCoffees;
        nbrCoffees++;
        this.label = label;
        this.originCountry = originCountry;
        setIntensity(intensity);
        setWeightNeededForPreparation(weightNeededForPreparation);
        this.discoveryYear = discoveryYear;
        this.inGrains = inGrains;
        this.environmentFriendly = environmentFriendly;
        setPrice(price);
        setPackaging(packaging);
        this.recommendedConsumingMoment = recommendedConsumingMoment;
        this.stockLocation = stockLocation;

        features = new ArrayList<>();
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public void setRecommendedConsumingMoment(String recommendedConsumingMoment) {
        this.recommendedConsumingMoment = recommendedConsumingMoment;
    }

    public void setDiscoveryYear(Integer discoveryYear) {
        this.discoveryYear = discoveryYear;
    }

    public void setIntensity(Integer intensity) throws IntegerInputException {
        if (intensity <= 0 || intensity >= 6)
            throw new IntegerInputException(intensity, "l'intensité", "L'intensité doit être comprise entre 1 et 5 !");
        this.intensity = intensity;
    }

    public void setWeightNeededForPreparation(Double weightNeededForPreparation) throws DoubleInputException {
        if (weightNeededForPreparation <= 0)
            throw new DoubleInputException(weightNeededForPreparation, "le poids nécessaire pour la préparation");
        this.weightNeededForPreparation = weightNeededForPreparation;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price <= 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        this.price = price;
    }

    public void setPackaging(Double packaging) throws DoubleInputException {
        if (packaging <= 0)
            throw new DoubleInputException(packaging, "le packaging", "Le packaging doit être positif et différent de 0 !");
        this.packaging = packaging;
    }

    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    public void addFeature(String feature) {
        features.add(feature);
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
