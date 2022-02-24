package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.ClientsWHServicesTypesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsWHServicesTypesListRepository extends CrudRepository<ClientsWHServicesTypesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.VEXCHANGE_SERVICE_TYPE_LIST(?1,?2,?3))")
    List<ClientsWHServicesTypesList> queryClientWHServiceTypesListByUserID(
            Long user_id, Long role_id, Long cnt_id
    );
}