package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокФинПоказатели", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestsResultList {

    @XmlElement(name="Заявка", namespace="ExchangeCRM")
    private List<RequestsResult> requestsResult;

    public List<RequestsResult> getRequestsResult() {
        return requestsResult;
    }

    public void setRequestsResult(List<RequestsResult> requestsResult) {
        this.requestsResult = requestsResult;
    }
}
