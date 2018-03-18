package com.oneup.cosmin.xheart.processing.risk;

import java.sql.Timestamp;

public class RiskAnalysis {

    private final RiskAssessmentResult value;
    private final Timestamp time;

    public RiskAssessmentResult getValue() {
        return value;
    }

    public RiskAnalysis(RiskAssessmentResult assessmentResult, Timestamp time) {
        this.value = assessmentResult;
        this.time = time;
    }

}
