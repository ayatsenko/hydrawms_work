package com.idltd.hydramob.repository.wms_list;

import com.idltd.hydramob.entity.wms_list.WMSContragentWebServiceList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WMSContragentWEBServiceListRepository extends CrudRepository<WMSContragentWebServiceList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_WEB_CONTRAGENT_SERVICE_LIST(?1,?2,?3))")
    List<WMSContragentWebServiceList> queryCntWMSWEBServiceList(Long user_id, Long role_id, Long cnt_id);
}
