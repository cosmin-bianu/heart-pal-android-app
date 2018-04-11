package com.oneup.cosmin.xheart.processing.risk;

public class RiskAssessmentResult {
    private final float arrhythmiaScore;
    private final float bpmScore;
    private final float complexScore;

    public RiskAssessmentResult(float arrhythmiaScore, float bpmScore, float complexScore) {
        this.arrhythmiaScore = arrhythmiaScore;
        this.bpmScore = bpmScore;
        this.complexScore = complexScore;
    }

    public float getArrhythmiaScore() {
        return arrhythmiaScore;
    }

    public float getBpmScore() {
        return bpmScore;
    }

    public float getComplexScore() {
        return complexScore;
    }
}
