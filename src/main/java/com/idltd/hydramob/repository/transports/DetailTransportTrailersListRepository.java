package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportTrailersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportTrailersListRepository extends CrudRepository<DetailTransportTrailersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_TRAILERS_DETAIL(?1,?2,?3))")
    List<DetailTransportTrailersList> querytransportTrailersDetailListByUserID(Long user_id, Long role_id, Long cntt_id);
}