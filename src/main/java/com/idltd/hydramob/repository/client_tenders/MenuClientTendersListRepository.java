package com.idltd.hydramob.repository.client_tenders;

import com.idltd.hydramob.entity.client_tenders.MenuClientTendersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientTendersListRepository extends CrudRepository<MenuClientTendersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.VCLIENT_TENDER_USER_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<MenuClientTendersList> queryClientTendersMenuListByUserID(Long user_id, Long role_id, Long cnt_id, Long client_tenders_filter_id, String client_tenders_filter_start_date, String client_tenders_filter_end_date);
}