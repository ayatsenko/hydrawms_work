package com.idltd.hydramob.repository.template;

import com.idltd.hydramob.entity.template.MenuContentProp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuContentPropRepository extends CrudRepository<MenuContentProp, Long> {
    @Query(nativeQuery = true, value = "SELECT MC_INT_NAME, MC_HIDE, MC_LIST_NUM FROM TABLE(PKG_USERS_VIEW.vMENU_CONT_USER_ROLE_LINK(?1,7,?2))")
    List<MenuContentProp> queryMenuContantPropByID(Long user_id, Long menu_id);
}