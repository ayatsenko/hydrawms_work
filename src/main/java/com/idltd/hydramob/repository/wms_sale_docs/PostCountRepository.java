package com.idltd.hydramob.repository.wms_sale_docs;

import com.idltd.hydramob.entity.wms_sale_docs.PostCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCountRepository extends CrudRepository<PostCount, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_DOC_COUNT(?1,?2,?3))")
    List<PostCount> queryPostCountByUserID(
            Long user_id, Long role_id, Long doc_id
    );
}