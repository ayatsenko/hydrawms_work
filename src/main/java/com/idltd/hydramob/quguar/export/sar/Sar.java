package com.idltd.hydramob.quguar.export.sar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SAR")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sar {
    @XmlElement(name = "PRODUCT", required =true)
    private List<Product> product;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public void addProduct(Product vproduct) {
       this.product.add(vproduct);
    }

    public Sar() {
        this.product = new ArrayList<>();
    }
}
