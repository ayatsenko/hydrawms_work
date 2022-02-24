package com.idltd.hydramob.repository.wms_sale_docs;

import com.idltd.hydramob.entity.wms_sale_docs.DetailAllNotPrint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailAllNotPrintRepository extends CrudRepository<DetailAllNotPrint, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_DOC_ALL_NOT_PRINT_POSTS(?1,?2,?3))")
    List<DetailAllNotPrint> queryDetailAllNotPrintByUserID(
            Long user_id, Long role_id, Long doc_id
    );
}