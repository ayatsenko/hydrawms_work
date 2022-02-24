package com.idltd.hydramob.entity.sheduler;

import javax.persistence.*;

@Entity
@Table(name = "CURRENCY_RATE")
public class ShedulerCurrencyDetail {
    @Id
    @Column(name = "CURRENCY_ROW_ID", nullable = false)
    @SequenceGenerator(name = "CURRENCY_RATE_SEQ", sequenceName = "CURRENCY_RATE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_RATE_SEQ")
    public Long currency_row_id;

    @Column(name = "CURRENCY_DATE_NAME", nullable = false)
    public String currency_date_name;

    @Column(name = "CURRENCY_BASE_NAME", nullable = false)
    public String currency_base_name;

    @Column(name = "CURRENCY_RATE_NAME", nullable = false)
    public String currency_rate_name;

    @Column(name = "CURRENCY_RATE_VALUE_NAME", nullable = false)
    public String currency_rate_value_name;
}
