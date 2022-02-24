package com.idltd.hydramob.repository.wms_list;

import com.idltd.hydramob.entity.wms_list.WMSUsersPrintersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WMSUsersPrintersListRepository extends CrudRepository<WMSUsersPrintersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vUSERS_PRINTERS_LINK_DEFAULT(?1,?2))")
    List<WMSUsersPrintersList> queryUserPrinterDefaultList(
            Long user_id, Long role_id
    );

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vUSERS_PRINTERS_LINK_PDF_DEFAULT(?1,?2))")
    List<WMSUsersPrintersList> queryUserPrinterPDFDefaultList(
            Long user_id, Long role_id
    );
}
