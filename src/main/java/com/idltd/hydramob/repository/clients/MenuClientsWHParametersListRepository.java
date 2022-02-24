package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientsWHParametersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsWHParametersListRepository extends CrudRepository<MenuClientsWHParametersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.VEXCHANGE_PARAMETERS_TYPE_LINK_MENU(?1,?2,?3))")
    List<MenuClientsWHParametersList> queryClientMenuWHParametersListByUserID(
            Long user_id, Long role_id, Long cnt_id
    );
}