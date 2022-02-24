package com.idltd.hydramob.repository.departments;

import com.idltd.hydramob.entity.departments.MenuDepartmentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepartmentsListRepository extends CrudRepository<MenuDepartmentsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.VDEPARTMENTS_MENU(?1,?2))")
    List<MenuDepartmentsList> queryDepartmentsMenuByUserID(Long user_id, Long role_id);
}