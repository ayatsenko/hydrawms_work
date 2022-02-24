package com.idltd.hydramob.Sheduler.z1c.repository;

import com.idltd.hydramob.Sheduler.z1c.entity.Load_Payments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadPaymentsRepository extends CrudRepository<Load_Payments, Long> {

}
