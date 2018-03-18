package com.oneup.cosmin.xheart.processing.cases;

import com.oneup.cosmin.xheart.exceptions.ShittyCodeException;

public final class Range {
    private float min;
    private float max;
    private boolean closedLeft;
    private boolean closedRight;

    public Range(float min, float max, boolean closedLeft, boolean closedRight) throws ShittyCodeException{
        if(min >= max) throw new ShittyCodeException("Minimum must be strictly less than maximum.");
        this.min = min;
        this.max = max;
        this.closedLeft = closedLeft;
        this.closedRight = closedRight;
    }

    public boolean isWithinRange(float value){
        boolean more;
        boolean less;
        if(closedLeft) more = min <= value;
        else more = min < value;

        if(closedRight) less = value <= max;
        else less = value < max;

        return less && more; //sa fim corecti gramatical
    }

    public float delta(){ return max-min; }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
}

