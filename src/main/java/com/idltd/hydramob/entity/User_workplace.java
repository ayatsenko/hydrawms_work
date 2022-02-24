package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name="USER_WORKPLACE")
public class User_workplace {
    @Id
    @SequenceGenerator( name = "USER_WORKPLACE_SEQ", sequenceName = "USER_WORKPLACE_SEQ", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USER_WORKPLACE_SEQ")
    public Long ulw_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;
    @Column(name = "wp_id", nullable = false)
    private Long wp_id;

    public User_workplace(Long user_id, Long wp_id) {
        this.user_id = user_id;
        this.wp_id = wp_id;
    }

    public User_workplace() {
    }
}
