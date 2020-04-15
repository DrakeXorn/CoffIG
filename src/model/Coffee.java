package model;

import model.exceptions.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Coffee {
    // tableau static de features dans lequel choisir quand on ajoute un café avec formulaire ?
    private Integer coffeeID, intensity;
    private String label, originCountry, recommendedConsummingMoment;
    private Double weightNeededForPreparation, price, packaging;
    private GregorianCalendar discoveryDate;
    private Boolean isInGrains, isEnvironmentFriendly;

    // pas sûre...
    private StockLocation stockLocation;

    private String [] features;
    private int nbFeatures;
    private final static int NB_FEATURES = 5;

    private Drink [] drinks;
    private int nbDrinks;
    private final static int NB_DRINKS = 100;


    public Coffee(Integer coffeeID, String label, String originCountry, Integer intensity,
                  Double weightNeededForPreparation, GregorianCalendar discoveryDate, Boolean isInGrains,
                  Boolean isEnvironmentFriendly, Double price, Double packaging,
                  String recommendedConsummingMoment, StockLocation stockLocation)
            throws IntensityException, WeightException, PriceException, PackagingException {
        this.coffeeID = coffeeID;
        this.label = label;
        this.originCountry = originCountry;
        setIntensity(intensity);
        setWeightNeededForPreparation(weightNeededForPreparation);
        this.discoveryDate = discoveryDate;
        this.isInGrains = isInGrains;
        this.isEnvironmentFriendly = isEnvironmentFriendly;
        setPrice(price);
        setPackaging(packaging);
        this.recommendedConsummingMoment = recommendedConsummingMoment;
        this.stockLocation = stockLocation;

        features = new String [NB_FEATURES];
        //String ou Feature ? On en fait vraiment une classe comme il n'y a que le label ?

        drinks = new Drink[NB_DRINKS];
    }

    public void setIntensity(Integer intensity) throws IntensityException {
        if(intensity > 0 && intensity < 6)
            this.intensity = intensity;
        else
            throw new IntensityException(intensity);
    }

    public void setWeightNeededForPreparation(Double weightNeededForPreparation) throws WeightException {
        if(weightNeededForPreparation > 0)
            this.weightNeededForPreparation = weightNeededForPreparation;
        else
            throw new WeightException(weightNeededForPreparation);
    }

    public void setPrice(Double price) throws PriceException{
        if(price > 0)
            this.price = price;
        else
            throw new PriceException(price);
    }

    public void setPackaging(Double packaging) throws PackagingException {
        if(packaging > 0)
            this.packaging = packaging;
        else
            throw new PackagingException(packaging);
    }

    // à appeller dans le constructeur de drink sur le café
    public void addDrink(Drink drink){
        if(nbDrinks < NB_DRINKS){
            drinks[nbDrinks] = drink;
            nbDrinks++;
        }
    }

    public void addFeature(String feature){ // ou Feature ?
        if(nbFeatures < NB_FEATURES){
            features[nbFeatures] = feature;
            nbFeatures++;
        }
    }

    public String toString(){
        StringBuilder description = new StringBuilder();
        description.append("Laissez-vous tenter par le café " + label);
        description.append(isInGrains ? " en grain" : " moulu");
        description.append(" originaire de " + originCountry);
        if(discoveryDate != null)
            description.append(" et découvert en " + discoveryDate.get(Calendar.YEAR));
        description.append(".\nCe café " + (isEnvironmentFriendly ? "biologique" : "non biologique"));
        for(int iFeature = 0; iFeature < nbFeatures; iFeature++){
            description.append(iFeature == 0 ? " est " : iFeature == nbFeatures - 1 ? " et " : ", ");
            description.append(features[iFeature]);
        }
        description.append(".\nSon intensité de " + intensity + (intensity == 1 ? " grain" : " grains") + " sur 5 ");
        if(recommendedConsummingMoment != null)
            description.append("le rend idéal à savourer durant " + recommendedConsummingMoment + ".");
        return description.toString();
    }
}
