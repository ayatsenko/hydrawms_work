package com.idltd.hydramob.repository.clients_classes;

import com.idltd.hydramob.entity.clients_classes.ClientsClassesMeetsMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientClassesMeetsRepository extends CrudRepository<ClientsClassesMeetsMenu, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_ANALYTICS_VIEW.vCLIENT_CLASSES_MEETS_MENU(?1,?2,?3))")
    List<ClientsClassesMeetsMenu> queryMenuClientActionsByID(
            Long user_id, Long role_id, Long cnt_id
    );
}