package com.idltd.hydramob.repository.tenders_outtime;

import com.idltd.hydramob.entity.tenders_outtime.MenuTendersOuttimeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTendersOuttimeListRepository extends CrudRepository<MenuTendersOuttimeList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.VTENDER_MENU_OUTTIME(?1,?2))")
    List<MenuTendersOuttimeList> queryTendersMenuOuttimeListByUserID(Long user_id, Long role_id);
}