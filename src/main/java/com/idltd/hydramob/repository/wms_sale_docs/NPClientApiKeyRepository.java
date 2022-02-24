package com.idltd.hydramob.repository.wms_sale_docs;

import com.idltd.hydramob.entity.wms_sale_docs.NPClientApiKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NPClientApiKeyRepository extends CrudRepository<NPClientApiKey, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_CLIENT_NP_API_KEY(?1,?2))")
    List<NPClientApiKey> queryGetNPClientApiKeyByUserID(
            Long user_id, Long role_id
    );
}