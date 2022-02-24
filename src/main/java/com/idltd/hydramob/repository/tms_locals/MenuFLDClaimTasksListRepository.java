package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.full_world.MenuClaimTasksList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLDClaimTasksListRepository extends CrudRepository<MenuClaimTasksList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TASKS_MENU(?1,?2,?3))")
    List<MenuClaimTasksList> queryClaimTasksMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}