package com.idltd.hydramob.Sheduler.repository;

import com.idltd.hydramob.Sheduler.entity.ShedulerCSVFileTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShedulerCSVFileTempRepository extends CrudRepository<ShedulerCSVFileTemp, Long> {
}
