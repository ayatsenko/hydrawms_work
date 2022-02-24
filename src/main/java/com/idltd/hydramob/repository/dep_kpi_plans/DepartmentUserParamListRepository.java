package com.idltd.hydramob.repository.dep_kpi_plans;

import com.idltd.hydramob.entity.dep_kpi_plans.DepartmentUserParamList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentUserParamListRepository extends CrudRepository<DepartmentUserParamList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_DEP_USERS_PARAM_LIST(?1,?2,?3))")
    List<DepartmentUserParamList> queryGetAllDepUserParams(Long user_id, Long role_id, Long dep_id);
}
