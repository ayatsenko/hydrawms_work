package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.MenuClaimReportParamsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimReportParamsListRepository extends CrudRepository<MenuClaimReportParamsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_REPORT_PARAM_MENU(?1,?2,?3,?4))")
    List<MenuClaimReportParamsList> queryClaimReportParamsMenuListByUserID(Long user_id, Long role_id, Long clm_id, Long rep_id);
}