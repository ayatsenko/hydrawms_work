package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name="USERS_LOCATIONS")
public class User_location {

    @Id
    @SequenceGenerator( name = "USERS_LOCATIONS_SEQ", sequenceName = "USERS_LOCATIONS_SEQ", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USERS_LOCATIONS_SEQ")
    public Long ul_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;
    @Column(name = "loc_id", nullable = false)
    private Long loc_id;

    public User_location(Long user_id, Long loc_id) {
        this.user_id = user_id;
        this.loc_id = loc_id;
    }

    public User_location() {
    }
}
