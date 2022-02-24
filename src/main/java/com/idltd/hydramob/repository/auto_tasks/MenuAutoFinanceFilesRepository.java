package com.idltd.hydramob.repository.auto_tasks;

import com.idltd.hydramob.entity.auto_tasks.MenuAutoFinanceFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAutoFinanceFilesRepository extends CrudRepository<MenuAutoFinanceFile, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_SHEDULER_VIEW.vSHEDULER_FINANCE_DETAIL(?1,?2,?3))")
    List<MenuAutoFinanceFile> queryMenuAutoFinanceFile(Long user_id, Long role_id, Long sfa_id);
}