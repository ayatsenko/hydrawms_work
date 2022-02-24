package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Load_Type_List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Load_Type_ListRepository extends CrudRepository<Load_Type_List, Long> {
}
