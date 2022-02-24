package com.idltd.hydramob.utils.filehandler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.idltd.hydramob.utils.filehandler.entity.FilehandlerDetailLog;

@Repository
public interface FileHandlerDetailLogRepository extends CrudRepository<FilehandlerDetailLog, Long> {

}
