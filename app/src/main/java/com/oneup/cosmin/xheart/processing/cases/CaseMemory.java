package com.oneup.cosmin.xheart.processing.cases;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CaseMemory {
    private static final CaseMemory ourInstance = new CaseMemory();
    public static CaseMemory getInstance() {
        return ourInstance;
    }
    private CaseMemory() {

    }

    private final ConcurrentLinkedQueue<Case> memory
            = new ConcurrentLinkedQueue<>();

    public ConcurrentLinkedQueue<Case> getMemory(){ return memory; }
}
