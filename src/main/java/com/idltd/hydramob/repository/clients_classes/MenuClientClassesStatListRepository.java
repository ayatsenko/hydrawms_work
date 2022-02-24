package com.idltd.hydramob.repository.clients_classes;

import com.idltd.hydramob.entity.clients_classes.MenuClientsClassesStatList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientClassesStatListRepository extends CrudRepository<MenuClientsClassesStatList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTR_CLASSES_STAT_MENU(?1,?2,?3,?4,?5))")
    List<MenuClientsClassesStatList> queryClientMenuStatisticsListByUserID(Long user_id, Long role_id, Long cnt_id, String start_date, String end_date);
}