package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.BanksList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BanksListRepository extends CrudRepository<BanksList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vBANK_LIST(?1,?2)) ORDER BY BANK_NAME")
    List<BanksList> queryBanksListAll(Long user_id, Long role_id);
}
