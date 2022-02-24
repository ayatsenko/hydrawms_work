package com.idltd.hydramob.repository.wms_sale_docs;

import com.idltd.hydramob.entity.wms_sale_docs.MenuWMSSaleDocsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSSaleDocsMainRepository extends CrudRepository<MenuWMSSaleDocsMain, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_DOC_MENU(?1,?2))")
    List<MenuWMSSaleDocsMain> queryWMSSaleDocsMainByID(
            Long user_id,
            Long role_id
    );
}