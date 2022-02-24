package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.DepartmentList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentListRepository extends CrudRepository<DepartmentList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_DEP_LIST(?1,?2))")
    List<DepartmentList> queryGetAllDep(Long user_id, Long role_id);
}
