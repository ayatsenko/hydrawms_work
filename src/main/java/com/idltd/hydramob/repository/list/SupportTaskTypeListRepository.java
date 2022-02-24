package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.SupportTaskTypeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTaskTypeListRepository extends CrudRepository<SupportTaskTypeList, Long> {
    @Query(nativeQuery = true, value = "select SPTT_ID, SPTT_NAME, SPTT_SNAME, SPTT_DESCRIPTION from SUPPORT_TASK_TYPE WHERE SPTT_ID = ?1")
    List<SupportTaskTypeList> querySupportTaskTypeListByID(Long sptp_id);

    @Query(nativeQuery = true, value = "select SPTT_ID, SPTT_NAME, SPTT_SNAME, SPTT_DESCRIPTION from SUPPORT_TASK_TYPE")
    List<SupportTaskTypeList> querySupportTaskTypeListAll();
}
