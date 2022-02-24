package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.DetailPartWorldClaimTransList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailPartWorldClaimTransListRepository extends CrudRepository<DetailPartWorldClaimTransList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TRANS_DETAIL(?1,?2,?3))")
    List<DetailPartWorldClaimTransList> queryClaimsDetailTransListByUserID(Long user_id, Long role_id, Long clmtrl_id);
}