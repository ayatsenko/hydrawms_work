package com.idltd.hydramob.repository.finance_abc;

import com.idltd.hydramob.entity.finance_abc.MenuFinanceABCDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFinanceABCDetailRepository extends CrudRepository<MenuFinanceABCDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ANALYTICS_VIEW.vABC_SERVICE(?1,?2,?3,?4))")
    List<MenuFinanceABCDetail> queryGetFinanceABCDetail(Long user_id, Long role_id, String start_date, String end_date);
}