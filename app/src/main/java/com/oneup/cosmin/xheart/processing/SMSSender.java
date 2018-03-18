package com.oneup.cosmin.xheart.processing;

import android.telephony.SmsManager;

public class SMSSender {
    private static final SMSSender ourInstance = new SMSSender();
    public static SMSSender getInstance() {
        return ourInstance;
    }
    private SMSSender() {

    }

    private static final SmsManager mgr = SmsManager.getDefault();
    private String destinationAddress = "+40773749563";

    public void sendText(String text){
        mgr.sendTextMessage(
                destinationAddress,
                null,
                text,
                null,
                null
        );
        //TODO check for successful send operation
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
}
