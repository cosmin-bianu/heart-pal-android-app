package com.oneup.cosmin.xheart.processing.risk;

public class RiskAssessmentResult {
    private final float arrhythmiaScore;
    private final float bpmScore;

    public RiskAssessmentResult(float arrhythmiaScore, float bpmScore) {
        this.arrhythmiaScore = arrhythmiaScore;
        this.bpmScore = bpmScore;
    }

    public float getArrhythmiaScore() {
        return arrhythmiaScore;
    }

    public float getBpmScore() {
        return bpmScore;
    }
}
