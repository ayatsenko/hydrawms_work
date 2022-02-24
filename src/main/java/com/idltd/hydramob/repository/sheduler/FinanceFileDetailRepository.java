package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.entity.sheduler.ShedulerFileDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceFileDetailRepository extends CrudRepository<ShedulerFileDetail, Long> {
}
