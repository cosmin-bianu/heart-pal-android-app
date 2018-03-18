package com.oneup.cosmin.xheart.processing.ecg;

import com.oneup.cosmin.xheart.processing.risk.RiskAnalysis;

import java.sql.Timestamp;

public class ECG {
    private final int value;
    private final Timestamp time;
    private final RiskAnalysis riskAnalysisAtRegistration;

    public int getValue() {
        return value;
    }

    public ECG(int value, Timestamp time, RiskAnalysis riskAnalysisAtRegistration) {
        this.value = value;
        this.time = time;
        this.riskAnalysisAtRegistration = riskAnalysisAtRegistration;
    }
}
