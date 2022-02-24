package com.idltd.hydramob.utils;

import com.idltd.hydramob.entity.User;

public class JSONUsersDatatable {
    public Iterable<User> getData() {
        return data;
    }

    public void setData(Iterable<User> data) {
        this.data = data;
    }

    Iterable<User> data;

    public JSONUsersDatatable(Iterable<User> data) {
        this.data = data;
    }
}
