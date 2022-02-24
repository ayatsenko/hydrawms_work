package com.idltd.hydramob.repository.requests;

import com.idltd.hydramob.entity.requests.MenuRequestFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestFilesListRepository extends CrudRepository<MenuRequestFilesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vREQUEST_STORE_MENU(?1,?2,?3))")
    List<MenuRequestFilesList> queryRequestFilesMenuByUserID(Long user_id, Long role_id, Long req_id);
}