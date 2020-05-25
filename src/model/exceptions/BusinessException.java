package model.exceptions;

public class BusinessException extends Exception {
    private String wrongClass, methodCalled, wrongValue;

    public BusinessException(String wrongClass, String methodCalled, String wrongValue) {
        this.wrongClass = wrongClass;
        this.methodCalled = methodCalled;
        this.wrongValue = wrongValue;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de l'appel de " + methodCalled + " dans la classe " + wrongClass + " :\n" + wrongValue + " est nul(le).";
    }
}
