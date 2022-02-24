package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.ClientsWHParametersTypesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsWHParametersTypesListRepository extends CrudRepository<ClientsWHParametersTypesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.VEXCHANGE_PARAMETERS_TYPE_LIST(?1,?2,?3))")
    List<ClientsWHParametersTypesList> queryClientWHParametersTypesListByUserID(
            Long user_id, Long role_id, Long cnt_id
    );
}