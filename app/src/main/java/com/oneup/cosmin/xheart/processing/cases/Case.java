package com.oneup.cosmin.xheart.processing.cases;

import java.util.Comparator;

public abstract class Case implements Comparable<Case>, Comparator<Case>{
    private final Range arrhythmiaRange;
    private final Range averageBpmRange;
    private final String message;

    public Case(Range arrhythmiaRange, Range averageBpmRange, String message) {
        this.arrhythmiaRange = arrhythmiaRange;
        this.averageBpmRange = averageBpmRange;
        this.message = message;
    }

    public Range getArrhythmiaRange() {
        return arrhythmiaRange;
    }

    public Range getAverageBpmRange() {
        return averageBpmRange;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int compare(Case c1, Case c2) {
        float aDelta1 = c1.arrhythmiaRange.delta();
        float bpmDelta1 = c1.averageBpmRange.delta();
        float aDelta2 = c2.arrhythmiaRange.delta();
        float bpmDelta2 = c2.averageBpmRange.delta();
        float c1FinalDelta = aDelta1 + bpmDelta1;
        float c2FinalDelta = aDelta2 + bpmDelta2;
        float finalDelta = c1FinalDelta - c2FinalDelta;
        if(finalDelta > 0) return 1;
        else if (finalDelta < 0) return -1;
        else return 0;
    }
}
