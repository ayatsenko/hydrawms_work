package com.idltd.hydramob.repository.system_params;

import com.idltd.hydramob.entity.system_params.MenuSystemParams;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MenuSystemParamsRepository extends CrudRepository<MenuSystemParams, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_ADMIN_VIEW.vCRM_SYSTEM_PARAMETERS_MENU(?1,?2))")
    List<MenuSystemParams> queryMenuSystemParamsByID(
            Long user_id, Long role_id
    );
}