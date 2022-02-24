package com.idltd.hydramob.quguar.export.ser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SER")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ser {
    @XmlElement(name = "PROD_SERIAL", required =true)
    private List<SerList> serListList;

    public List<SerList> getSerListList() {
        return serListList;
    }

    public void setSerListList(List<SerList> serListList) {
        this.serListList = serListList;
    }

    public void addList(SerList list) {
       this.serListList.add(list);
    }

    public Ser() {
        this.serListList = new ArrayList<>();
    }
}
