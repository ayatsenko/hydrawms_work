package com.idltd.hydramob.utils;

import com.idltd.hydramob.entity.Workplace;

import java.util.List;

public class JSONWorkplaceDatatable {
    public Iterable<Workplace> getData() {
        return data;
    }

    public void setData(Iterable<Workplace> data) {
        this.data = data;
    }

    Iterable<Workplace> data;

    public JSONWorkplaceDatatable(Iterable<Workplace> data) {
        this.data = data;
    }

    public void add(Workplace location){
        ((List) data).add(location);
    }
}
