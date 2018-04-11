package com.oneup.cosmin.xheart.net;

import java.sql.Timestamp;
import java.util.Date;

class Stopwatch {
    class NotStartedException extends Exception{
        public NotStartedException() {
            super();
        }

        public NotStartedException(String message) {
            super(message);
        }

        public NotStartedException(String message, Throwable cause) {
            super(message, cause);
        }

        public NotStartedException(Throwable cause) {
            super(cause);
        }
    }
    class AlreadyStartedException extends Exception{
        public AlreadyStartedException() {
            super();
        }

        public AlreadyStartedException(String message) {
            super(message);
        }

        public AlreadyStartedException(String message, Throwable cause) {
            super(message, cause);
        }

        public AlreadyStartedException(Throwable cause) {
            super(cause);
        }
    }

    private Date date  = new Date();
    private Timestamp startTime;
    private boolean started;

    public void start() throws AlreadyStartedException {
        if(started) throw new AlreadyStartedException();
        startTime = new Timestamp(date.getTime());
        started = true;
    }

    public long getElapsedTime() throws NotStartedException {
        if(!started) throw new NotStartedException();
        return new Date().getTime() - startTime.getTime();
    }

    public double getElapsedTimeInSeconds() throws NotStartedException{
        return (double) getElapsedTime()/1000;
    }

    public void stop() throws NotStartedException {
        if(!started) throw new NotStartedException();
        startTime = null;
        started = false;
    }
}
