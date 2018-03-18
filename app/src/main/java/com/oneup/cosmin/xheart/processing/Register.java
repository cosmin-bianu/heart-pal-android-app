package com.oneup.cosmin.xheart.processing;

import com.oneup.cosmin.xheart.processing.cases.Case;
import com.oneup.cosmin.xheart.processing.ecg.ECG;
import com.oneup.cosmin.xheart.processing.pulse.Pulse;
import com.oneup.cosmin.xheart.processing.risk.RiskAnalysis;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Register {
    private final Timestamp time;
    private final ECG ecg;
    private final Pulse pulse;
    private final RiskAnalysis riskAnalysis;
    private final ArrayList<Case> possibleCases;

    public Timestamp getTime() {
        return time;
    }
    public ECG getEcg() {
        return ecg;
    }
    public Pulse getPulse() {
        return pulse;
    }
    public RiskAnalysis getRiskAnalysis() {
        return riskAnalysis;
    }
    public ArrayList<Case> getPossibleCases() { return possibleCases; }

    public Register(
            Timestamp time,
            ECG ecg,
            Pulse pulse,
            RiskAnalysis riskAnalysis,
            ArrayList<Case> possibleCases) {
        this.time = time;
        this.ecg = ecg;
        this.pulse = pulse;
        this.riskAnalysis = riskAnalysis;
        this.possibleCases = possibleCases;
    }

}
