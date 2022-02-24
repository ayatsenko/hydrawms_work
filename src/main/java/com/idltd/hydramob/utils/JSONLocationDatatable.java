package com.idltd.hydramob.utils;

import com.idltd.hydramob.entity.Location;

import java.util.List;

public class JSONLocationDatatable {
    public Iterable<Location> getData() {
        return data;
    }

    public void setData(Iterable<Location> data) {
        this.data = data;
    }

    Iterable<Location> data;

    public JSONLocationDatatable(Iterable<Location> data) {
        this.data = data;
    }

    public void add(Location location){
        ((List) data).add(location);
    }
}
