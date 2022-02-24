package com.idltd.hydramob.repository.report_dk;

import com.idltd.hydramob.entity.report_dk.MenuReportDKDocComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportDKDocCommentRepository extends CrudRepository<MenuReportDKDocComment, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_134_DOC_COMMENT(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuReportDKDocComment> queryGetRepDKDocComment(
            Long user_id, Long role_id, Long dkm_id, Long dkmd_id, Long dkmdd_id, Long cnt_id, Long ser_id
    );
}