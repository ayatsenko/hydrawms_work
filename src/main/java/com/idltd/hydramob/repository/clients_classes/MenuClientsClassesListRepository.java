package com.idltd.hydramob.repository.clients_classes;

import com.idltd.hydramob.entity.clients_classes.MenuClientsClassesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsClassesListRepository extends CrudRepository<MenuClientsClassesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTR_CLASSES_MENU(?1,?2,?3,?4,?5,?6))")
    List<MenuClientsClassesList> queryClientClassesMenuFilterListByUserID(Long user_id, Long role_id, Long requests_filter_check, Long clients_filters_user_id, Long clients_filters_status_id, Long clients_filters_class_id);
}