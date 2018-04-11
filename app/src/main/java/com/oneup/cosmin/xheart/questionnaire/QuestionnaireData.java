package com.oneup.cosmin.xheart.questionnaire;

import com.oneup.cosmin.xheart.processing.cases.Range;
import com.oneup.cosmin.xheart.processing.pulse.PulseProcessor;
import com.oneup.cosmin.xheart.processing.risk.RiskProcessor;

public class QuestionnaireData {
    private static final QuestionnaireData ourInstance = new QuestionnaireData();
    public static QuestionnaireData getInstance() {
        return ourInstance;
    }
    private QuestionnaireData() {
    }

    public void setCardiacFrequency(int freq){
        PulseProcessor.getInstance().setPulseThreshold(freq);
    }
    public void setQRSComplexLength(long length){
        RiskProcessor.setQrsComplexLength(length);
    }
    public void setBPMRange(Range bpmRange){
        RiskProcessor.setBpmSafeRange(bpmRange);
    }
}