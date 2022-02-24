package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.ClientDocFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDocFileRepository extends CrudRepository<ClientDocFile, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM  CONTRAGENT_DOC_FILE WHERE  CNT_DOC_FILE_ID = ?")
    List<ClientDocFile> queryDocByID(Long cnt_doc_file_id);
}
