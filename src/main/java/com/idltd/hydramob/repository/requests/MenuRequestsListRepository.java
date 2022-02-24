package com.idltd.hydramob.repository.requests;

import com.idltd.hydramob.entity.requests.MenuRequestsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestsListRepository extends CrudRepository<MenuRequestsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REQUEST_VIEW.vUSERS_SIMP_REQ_MENU_TIME(?1,?2,?3,?4,?5))")
    List<MenuRequestsList> queryRequestsMenuByUserID(
            Long user_id, Long role_id, Long requests_filter_id, String requests_filter_start_date, String requests_filter_end_date
    );
}