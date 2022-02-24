package com.idltd.hydramob.quguar.export.ssl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SSL")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ssl {
    @XmlElement(name = "HEADER", required =true)
    private List<SslHeader> sslHeaderList;


    public void addHeader(SslHeader sprheader) {
       this.sslHeaderList.add(sprheader);
    }

    public Ssl() {
        this.sslHeaderList = new ArrayList<>();
    }

    public List<SslHeader> getSprHeaderList() {
        return sslHeaderList;
    }

    public void setSprHeaderList(List<SslHeader> sslHeaderList) {
        this.sslHeaderList = sslHeaderList;
    }

}
