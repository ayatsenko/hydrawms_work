package com.idltd.hydramob.repository.client_requests;

import com.idltd.hydramob.entity.client_requests.MenuClientRequestsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientRequestsListRepository extends CrudRepository<MenuClientRequestsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REQUEST_VIEW.vUSERS_CLIENT_SIMP_REQ_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<MenuClientRequestsList> queryClientRequestsMenuByUserID(
            Long user_id, Long role_id, Long cnt_id, Long client_requests_filter_id, String client_requests_filter_start_date, String client_requests_filter_end_date
    );
}