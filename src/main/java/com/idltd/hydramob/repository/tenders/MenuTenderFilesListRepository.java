package com.idltd.hydramob.repository.tenders;

import com.idltd.hydramob.entity.tenders.MenuTenderFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTenderFilesListRepository extends CrudRepository<MenuTenderFilesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TENDERS_VIEW.vREQUEST_STORE_MENU(?1,?2,?3))")
    List<MenuTenderFilesList> queryTenderFilesMenuByUserID(Long user_id, Long role_id, Long req_id);
}