package com.idltd.hydramob.Sheduler.ftp_stock.repository;

import com.idltd.hydramob.Sheduler.ftp_stock.entity.FTPStockWMSClientsQuantity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FTPStockWMSClientsQuantityRepository extends CrudRepository<FTPStockWMSClientsQuantity, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.VWH_CLIENTS_QUANTITY_DATE(?1))")
    List<FTPStockWMSClientsQuantity> queryFTPStockWMSClientsQuantityByID(
            Long cnt_id
    );
}