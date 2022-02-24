package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Action_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Action_Type_ListRepository extends CrudRepository<Action_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from ACTION_TYPE")
    List<Action_Type_List> queryGetAll();
}
