package model.exceptions;

public class PointsRequiredException extends Exception{
    private Integer pointsErrone;

    public PointsRequiredException(Integer pointsErrone) {
        this.pointsErrone = pointsErrone;
    }

    public String getMessage(){
        return "La valeur " + pointsErrone + " proposée pour le nombre de point requis est invalide ! ";
    }
}
