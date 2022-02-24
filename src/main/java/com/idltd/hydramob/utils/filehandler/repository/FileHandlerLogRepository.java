package com.idltd.hydramob.utils.filehandler.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.idltd.hydramob.utils.filehandler.entity.FilehandlerLog;

import java.util.List;

@Repository
public interface FileHandlerLogRepository extends CrudRepository<FilehandlerLog, Long> {
    @Query(nativeQuery = true, value = "select * from FILEHANDLER_LOG")
    List<FilehandlerLog> getAll();
}
