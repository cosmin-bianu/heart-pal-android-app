package com.oneup.cosmin.xheart.processing;


public class Diagnostic {
    public final String time;
    public final String date;
    public final String type;
    public final String description;
    public final int severity;

    public static class Type{
        public final int severity;
        public final String description;

        public Type(String str){
            int sev = -1;
            String desc = null;
            switch (str){
                case "A":
                    sev = 3;
                    desc = "Bătaie atrială prematură";
                    break;
                case "V":
                    sev = 7;
                    desc = "Contracție ventriculară prematură";
                    break;
            }
            this.severity = sev;
            this.description = desc;
        }
    }

    public Diagnostic(String time, String date, String type, String description, int severity) {
        this.time = time;
        this.date = date;
        this.type = type;
        this.description = description;
        this.severity = severity;
    }
}
