package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Request_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Request_Type_ListRepository extends CrudRepository<Request_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from REQUEST_TYPE WHERE req_type_id = ?1")
    List<Request_Type_List> queryTypeByID(Long req_type_id);
}
