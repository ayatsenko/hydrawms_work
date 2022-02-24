package com.idltd.hydramob.Sheduler.ftp_stock.repository;

import com.idltd.hydramob.Sheduler.ftp_stock.entity.FTPStockClientParamList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FTPStockClientParamListRepository extends CrudRepository<FTPStockClientParamList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(HCOMM.PKG_SCHEDULER_VIEW.vEXCHANGE_PARAMETERS_TYPE_LINK(?1,?2))")
    List<FTPStockClientParamList> queryFTPStockClientParamListByID(
            Long exch_ser_type_id, Long cnt_id
    );
}