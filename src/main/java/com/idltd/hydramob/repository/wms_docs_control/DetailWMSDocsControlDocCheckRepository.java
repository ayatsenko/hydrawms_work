package com.idltd.hydramob.repository.wms_docs_control;

import com.idltd.hydramob.entity.wms_docs_control.DetailWMSDocsControlDocCheck;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailWMSDocsControlDocCheckRepository extends CrudRepository<DetailWMSDocsControlDocCheck, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vPOST_CONTROL_DOC_CHECK(?1,?2,?3))")
    List<DetailWMSDocsControlDocCheck> queryWMSDocsControlDocCheckByUserID(
            Long user_id, Long role_id, String client_post_id
    );
}