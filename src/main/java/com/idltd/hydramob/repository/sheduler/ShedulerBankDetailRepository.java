package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.entity.sheduler.ShedulerBankDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShedulerBankDetailRepository extends CrudRepository<ShedulerBankDetail, Long> {
}
