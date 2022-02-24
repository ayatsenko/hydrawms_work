package com.idltd.hydramob.repository.clients_classes;

import com.idltd.hydramob.entity.clients_classes.MenuClientsClassesFullList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsClassesFullListRepository extends CrudRepository<MenuClientsClassesFullList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ANALYTICS_VIEW.vCONTR_CLASSES_TEMP_MENU(?1,?2))")
    List<MenuClientsClassesFullList> queryClientClassesMenuFilterListByUserID(Long user_id, Long role_id);
}