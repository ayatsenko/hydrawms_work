package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionPlanTypeRepository extends CrudRepository<ActionPlanTypeList, Long> {
    @Query(nativeQuery = true, value = "select * from ACTION_PLAN_TYPE")
    List<ActionPlanTypeList> queryActionPlanTypeByID();
}
