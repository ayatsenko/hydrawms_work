package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientStatisticsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientStatisticsListRepository extends CrudRepository<MenuClientStatisticsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_STATISTICS_MENU(?1,?2,?3,?4,?5))")
    List<MenuClientStatisticsList> queryClientMenuStatisticsListByUserID(Long user_id, Long role_id, Long cnt_id, String start_date, String end_date);
}