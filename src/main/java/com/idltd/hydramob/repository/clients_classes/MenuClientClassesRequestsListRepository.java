package com.idltd.hydramob.repository.clients_classes;

import com.idltd.hydramob.entity.client_requests.MenuClientRequestsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientClassesRequestsListRepository extends CrudRepository<MenuClientRequestsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ANALYTICS_VIEW.vCLIENT_CLASSES_REQUESTS_MENU(?1,?2,?3))")
    List<MenuClientRequestsList> queryClientClassesRequestsMenuByUserID(
            Long user_id, Long role_id, Long cnt_id
    );
}