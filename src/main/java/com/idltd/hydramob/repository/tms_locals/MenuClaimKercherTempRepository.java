package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.MenuClaimKercherTemp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimKercherTempRepository extends CrudRepository<MenuClaimKercherTemp, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_KERCHER_TEMP(?1,?2,?3))")
    List<MenuClaimKercherTemp> queryFLDClaimKercherTempMenuListByUserID(Long user_id, Long role_id, Long type_id);
}