package com.oneup.cosmin.xheart.processing;

import com.oneup.cosmin.xheart.processing.ecg.ECG;
import com.oneup.cosmin.xheart.processing.pulse.Pulse;
import com.oneup.cosmin.xheart.processing.risk.RiskAnalysis;

import java.sql.Timestamp;

public class Register {
    private final Timestamp time;
    private final ECG ecg;
    private final Pulse pulse;
    private final RiskAnalysis riskAnalysis;

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

    public Register(
            Timestamp time,
            ECG ecg,
            Pulse pulse,
            RiskAnalysis riskAnalysis) {
        this.time = time;
        this.ecg = ecg;
        this.pulse = pulse;
        this.riskAnalysis = riskAnalysis;
    }

}
