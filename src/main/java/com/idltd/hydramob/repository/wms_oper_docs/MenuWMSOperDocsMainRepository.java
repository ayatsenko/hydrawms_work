package com.idltd.hydramob.repository.wms_oper_docs;

import com.idltd.hydramob.entity.wms_oper_docs.MenuWMSOperDocsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSOperDocsMainRepository extends CrudRepository<MenuWMSOperDocsMain, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_DOC_ADMIN_MENU(?1,?2))")
    List<MenuWMSOperDocsMain> queryWMSOperDocsMainByID(
            Long user_id,
            Long role_id
    );
}