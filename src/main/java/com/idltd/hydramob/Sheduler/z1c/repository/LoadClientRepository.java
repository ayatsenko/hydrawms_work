package com.idltd.hydramob.Sheduler.z1c.repository;

import com.idltd.hydramob.Sheduler.z1c.entity.Load_Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadClientRepository extends CrudRepository<Load_Client, Long> {

}
