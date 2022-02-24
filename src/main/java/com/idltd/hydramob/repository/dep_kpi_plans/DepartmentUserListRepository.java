package com.idltd.hydramob.repository.dep_kpi_plans;

import com.idltd.hydramob.entity.dep_kpi_plans.DepartmentUserList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentUserListRepository extends CrudRepository<DepartmentUserList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_DEP_USERS_LIST(?1,?2,?3))")
    List<DepartmentUserList> queryGetAllDepUsers(Long user_id, Long role_id, Long dep_id);
}
