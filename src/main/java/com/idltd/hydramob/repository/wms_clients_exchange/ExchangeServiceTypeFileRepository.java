package com.idltd.hydramob.repository.wms_clients_exchange;

import com.idltd.hydramob.entity.wms_clients_exchange.ExchangeServiceTypeFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeServiceTypeFileRepository extends CrudRepository<ExchangeServiceTypeFile, Long> {
}
