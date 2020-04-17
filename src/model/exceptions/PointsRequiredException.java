package model.exceptions;

public class PointsRequiredException extends Exception{
    private Integer worongPoints;

    public PointsRequiredException(Integer worongPoints) {
        this.worongPoints = worongPoints;
    }

    public String getMessage(){
        return "La valeur " + worongPoints + " proposée pour le nombre de point requis est invalide ! ";
    }
}
