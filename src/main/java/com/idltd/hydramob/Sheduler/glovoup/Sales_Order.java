package com.idltd.hydramob.Sheduler.glovoup;

public class Sales_Order {
    private String id;
    private String Date;
    private String OrderNo;
    private String ItemID;
    private String Itemtitle;
    private String SKU;
    private String Qty;
    private String ShippingMethod;
    private String SpecialInstructions;
    private String Name;
    private String Cneetelnumber;
    private String Email;
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String State;
    private String Postcode;
    private String Country;

    public Sales_Order(String id, String date, String orderNo, String itemID, String itemtitle, String SKU, String Qty, String shippingMethod, String specialInstructions, String name, String cneetelnumber, String email, String addressLine1, String addressLine2, String city, String state, String postcode, String country) {
        this.id = id;
        Date = date;
        OrderNo = orderNo;
        ItemID = itemID;
        Itemtitle = itemtitle;
        this.SKU = SKU;
        this.Qty=Qty;
        ShippingMethod = shippingMethod;
        SpecialInstructions = specialInstructions;
        Name = name;
        Cneetelnumber = cneetelnumber;
        Email = email;
        AddressLine1 = addressLine1;
        AddressLine2 = addressLine2;
        City = city;
        State = state;
        Postcode = postcode;
        Country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getItemtitle() {
        return Itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        Itemtitle = itemtitle;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getShippingMethod() {
        return ShippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        ShippingMethod = shippingMethod;
    }

    public String getSpecialInstructions() {
        return SpecialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        SpecialInstructions = specialInstructions;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCneetelnumber() {
        return Cneetelnumber;
    }

    public void setCneetelnumber(String cneetelnumber) {
        Cneetelnumber = cneetelnumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
