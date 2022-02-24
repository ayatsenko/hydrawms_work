package com.idltd.hydramob.utils.telegram;

public class MenuItem {

    private String name;
    private String action;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MenuItem(String name) {

        this.name = name;
    }

    public MenuItem(String name, String action) {

        this.name = name;
        this.action = action;
    }
}
