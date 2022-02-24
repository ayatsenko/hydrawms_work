package com.idltd.hydramob.repository.finance;

import com.idltd.hydramob.entity.finance.MenuFinanceList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFinanceListRepository extends CrudRepository<MenuFinanceList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.VFINANCE_USER_MENU_TIME(?1,?2,?3,?4,?5))")
    List<MenuFinanceList> queryFinanceMenuByUserID(
            Long user_id, Long role_id, Long finance_filter_id, String finance_filter_start_date, String finance_filter_end_date
    );
}