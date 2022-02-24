package com.idltd.hydramob.Sheduler.ftp_stock.repository;

import com.idltd.hydramob.Sheduler.ftp_stock.entity.FTPStockClientList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FTPStockClientListRepository extends CrudRepository<FTPStockClientList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(HCOMM.PKG_SCHEDULER_VIEW.vEXCHANGE_CONTRAGENT_LIST(?1))")
    List<FTPStockClientList> queryFTPStockClientListByID(
            Long exch_ser_type_id
    );
}