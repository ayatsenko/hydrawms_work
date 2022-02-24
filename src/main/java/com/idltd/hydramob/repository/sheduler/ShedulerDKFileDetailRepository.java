package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.entity.sheduler.ShedulerDKFileDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShedulerDKFileDetailRepository extends CrudRepository<ShedulerDKFileDetail, Long> {
}
