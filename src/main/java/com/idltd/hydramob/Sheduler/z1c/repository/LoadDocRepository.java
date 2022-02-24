package com.idltd.hydramob.Sheduler.z1c.repository;

import com.idltd.hydramob.Sheduler.z1c.entity.Load_Docs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadDocRepository extends CrudRepository<Load_Docs, Long> {

}
