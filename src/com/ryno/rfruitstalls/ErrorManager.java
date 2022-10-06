package com.ryno.rfruitstalls;

public class ErrorManager {
    private double errorScore = 0.0;
    private final double errorThreshold;
    private final double errorIncrement;
    private final double errorDecrement;

    public ErrorManager(double errorThreshold, double errorIncrement, double errorDecrement) {
        this.errorThreshold = errorThreshold;
        this.errorIncrement = errorIncrement;
        this.errorDecrement = errorDecrement;
    }

    public void incrementErrorScore() {
        this.errorScore += errorIncrement;
    }

    public void decrementErrorScore() {
        this.errorScore = Math.max(this.errorScore - errorDecrement, 0);
    }

    public void resetErrorScore() {
        this.errorScore = 0.0;
    }

    public boolean checkForThreshold() {
        return errorScore >= errorThreshold;
    }

    public double getErrorScore() {
        return errorScore;
    }
}
