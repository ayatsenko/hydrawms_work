package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientsWHList {
    @Id
    @Column(name = "WH_CLIENT_ID")
    public Long wh_client_id;

    @Column(name = "WH_CLIENT_NAME")
    public String wh_client_name;
}
