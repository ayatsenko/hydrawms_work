package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.entity.sheduler.ShedulerCurrencyDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShedulerCurrencyDetailRepository extends CrudRepository<ShedulerCurrencyDetail, Long> {
}
