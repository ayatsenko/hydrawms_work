package com.idltd.hydramob.repository.country;

import com.idltd.hydramob.entity.country.MenuCountryMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCountryMainRepository extends CrudRepository<MenuCountryMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.vCOUNTRY_MENU(?1,?2))")
    List<MenuCountryMain> queryCountryMainByUserID(Long user_id, Long role_id);
}