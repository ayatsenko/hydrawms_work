package com.idltd.hydramob.Sheduler.z1c.repository;

import com.idltd.hydramob.Sheduler.z1c.entity.Load_Requests;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRequestsRepository extends CrudRepository<Load_Requests, Long> {

}
