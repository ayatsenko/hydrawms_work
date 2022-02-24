package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.tms_locals.MenuFLDClaimTasksList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimTasksListRepository extends CrudRepository<MenuFLDClaimTasksList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TASKS_MENU(?1,?2,?3))")
    List<MenuFLDClaimTasksList> queryClaimTasksMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}