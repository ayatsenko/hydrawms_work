package com.idltd.hydramob.repository.tenders;

import com.idltd.hydramob.entity.tenders.MenuTendersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTendersListRepository extends CrudRepository<MenuTendersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TENDERS_VIEW.VTENDER_USER_MENU_TIME(?1,?2,?3,?4,?5))")
    List<MenuTendersList> queryTendersMenuListByUserID(
            Long user_id, Long role_id, Long tender_filter_id, String tender_filter_start_date, String tender_filter_end_date
    );
}