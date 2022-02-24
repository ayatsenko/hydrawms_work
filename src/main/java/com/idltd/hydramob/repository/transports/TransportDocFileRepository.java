package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.TransportDocFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportDocFileRepository extends CrudRepository<TransportDocFile, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM CONTRAGENT_DOC_FILE WHERE cnt_doc_file_id = ?1")
    TransportDocFile queryDOCFileByID(long cnt_doc_file_id);
}
