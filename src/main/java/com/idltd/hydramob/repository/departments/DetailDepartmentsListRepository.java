package com.idltd.hydramob.repository.departments;

import com.idltd.hydramob.entity.departments.DetailDepartmentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailDepartmentsListRepository extends CrudRepository<DetailDepartmentsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.VDEPARTMENTS_DETAIL(?1,?2,?3))")
    List<DetailDepartmentsList> queryDepartmentsMenuByUserID(Long user_id, Long role_id, Long det_id);
}