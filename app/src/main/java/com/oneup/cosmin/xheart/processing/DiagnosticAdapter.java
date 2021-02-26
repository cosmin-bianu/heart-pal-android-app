package com.oneup.cosmin.xheart.processing;

public class DiagnosticAdapter {
    private static DiagnosticAdapter singleton = new DiagnosticAdapter();
    public static DiagnosticAdapter getInstance() {
        return singleton;
    }
    private DiagnosticAdapter(){
    }

    //TEMPLATE
    //A,TIME,DATE
    public Diagnostic getDiagnostic(String str){
        String time;
        String date;
        String type;
        String desc;
        int severity;


        String[]  entities = str.split(",");
        type = entities[0];
        time = entities[1];
        date = entities[2];
        Diagnostic.Type tmp = new Diagnostic.Type(type);
        desc = tmp.description;
        severity = tmp.severity;

        return new Diagnostic(time, date, type, desc, severity);
    }
}
