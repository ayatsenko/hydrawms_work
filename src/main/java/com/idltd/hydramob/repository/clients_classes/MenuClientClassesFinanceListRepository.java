package com.idltd.hydramob.repository.clients_classes;

import com.idltd.hydramob.entity.clients_classes.MenuClientClassesFinanceList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientClassesFinanceListRepository extends CrudRepository<MenuClientClassesFinanceList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ANALYTICS_VIEW.VCLIENT_CLASSES_FIN_MENU(?1,?2,?3))")
    List<MenuClientClassesFinanceList> queryClientClassesFinanceMenuByUserID(
            Long user_id, Long role_id, Long cnt_id
    );
}