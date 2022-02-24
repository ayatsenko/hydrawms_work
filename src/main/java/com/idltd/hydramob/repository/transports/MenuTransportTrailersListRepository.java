package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportTrailersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportTrailersListRepository extends CrudRepository<MenuTransportTrailersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_TRAILERS_MENU(?1,?2,?3))")
    List<MenuTransportTrailersList> querytransportTrailersMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}