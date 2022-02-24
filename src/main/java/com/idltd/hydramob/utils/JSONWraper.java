package com.idltd.hydramob.utils;

public class JSONWraper {
    public String data;
    public boolean result;

    public JSONWraper() {
        this.result = false;
    }

    public JSONWraper(boolean result) {
        this.result = result;
    }

    public JSONWraper(String data, boolean result) {
        this.data = data;
        this.result = result;
    }
}
