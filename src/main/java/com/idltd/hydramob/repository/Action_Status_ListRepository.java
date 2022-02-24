package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Action_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Action_Status_ListRepository extends CrudRepository<Action_Status_List, Long> {

    @Query(nativeQuery = true, value = "select * from ACTION_STATUS where act_status_id in (0,1)")
    List<Action_Status_List> queryEditStatus();
}
