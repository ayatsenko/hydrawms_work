package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Action_Result_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Action_Result_ListRepository extends CrudRepository<Action_Result_List, Long> {
    @Query(nativeQuery = true, value = "select * from ACTION_RESULT where act_result_id in (-1,1) ORDER BY ACT_RESULT_ID DESC")
    List<Action_Result_List> queryActionResultEditStatus();
}
