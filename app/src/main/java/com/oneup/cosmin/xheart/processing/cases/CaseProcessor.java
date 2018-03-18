package com.oneup.cosmin.xheart.processing.cases;

import com.oneup.cosmin.xheart.processing.risk.RiskAssessmentResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CaseProcessor {
    private static final CaseProcessor ourInstance = new CaseProcessor();
    public static CaseProcessor getInstance() {
        return ourInstance;
    }
    private CaseProcessor() {
    }

    public ArrayList<Case> selectCase(RiskAssessmentResult ras){
        ConcurrentLinkedQueue<Case> m  =
                CaseMemory.getInstance().getMemory();
        ArrayList<Case> selectedCases = new ArrayList<>();
        float as = ras.getArrhythmiaScore();
        float bpmScore = ras.getBpmScore();
        for (Case x: m){
            Range ar = x.getArrhythmiaRange();
            Range bpmRange = x.getAverageBpmRange();
            if(ar.isWithinRange(as) &&
                    bpmRange.isWithinRange(bpmScore))
                selectedCases.add(x);
        }
        Collections.sort(selectedCases);
        return selectedCases;
    }
}
