package com.idltd.hydramob.repository.wms_docs_control;

import com.idltd.hydramob.entity.wms_docs_control.MenuWMSDocsControlDoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSDocsControlDocRepository extends CrudRepository<MenuWMSDocsControlDoc, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_DOC_CONTROL_MENU(?1,?2))")
    List<MenuWMSDocsControlDoc> queryWMSDocsControlDocByID(
            Long user_id,
            Long role_id
    );
}