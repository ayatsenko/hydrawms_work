package com.idltd.hydramob.quguar.export.spr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SPR")
@XmlAccessorType(XmlAccessType.FIELD)
public class Spr {
    @XmlElement(name = "HEADER", required =true)
    private List<SprHeader> sprHeaderList;



    public void addHeader(SprHeader sprheader) {
       this.sprHeaderList.add(sprheader);
    }


    public Spr() {
        this.sprHeaderList = new ArrayList<>();
    }

    public List<SprHeader> getSprHeaderList() {
        return sprHeaderList;
    }

    public void setSprHeaderList(List<SprHeader> sprHeaderList) {
        this.sprHeaderList = sprHeaderList;
    }


}
