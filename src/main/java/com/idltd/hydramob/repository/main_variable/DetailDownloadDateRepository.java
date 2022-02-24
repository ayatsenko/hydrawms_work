package com.idltd.hydramob.repository.main_variable;

import com.idltd.hydramob.entity.report_dk.DetailDownloadDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailDownloadDateRepository extends CrudRepository<DetailDownloadDate, Long> {
    @Query(nativeQuery = true, value = "SELECT to_char(MAX(SFA_DATE),'dd.mm.yyyy hh:mi:ss') ADD_DATE FROM ZCRM.SHEDULER_FILE_ARCH WHERE SE_ID in (SELECT SE_ID FROM ZCRM.SHEDULER_EVENT WHERE SET_ID = 209 AND SE_STATE = 'done')")
    List<DetailDownloadDate> queryDetailDownloadDateBy();
}