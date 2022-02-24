package com.idltd.hydramob.repository.wms_sale_orders.files;

import com.idltd.hydramob.entity.wms_sale_orders.files.WMSSaleOrdersHittradeFileTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WMSSaleOrdersHittradeFileRepository extends CrudRepository<WMSSaleOrdersHittradeFileTemp, Long> {

}