package com.idltd.hydramob.controller.report_tms_check;

import com.idltd.hydramob.entity.Service_Type_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Report_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.Service_Type_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.report_tms_check.MenuReportTMSCheckDetailRepository;
import com.idltd.hydramob.repository.report_tms_check.MenuReportTMSCheckMainRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/report_tms_check")
public class ReportTMSCheckController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private MenuReportTMSCheckMainRepository menuReportTMSCheckMainRepository;

    private MenuReportTMSCheckDetailRepository menuReportTMSCheckDetailRepository;
    public ReportTMSCheckController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            MenuReportTMSCheckMainRepository menuReportTMSCheckMainRepository,

            MenuReportTMSCheckDetailRepository menuReportTMSCheckDetailRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.menuReportTMSCheckMainRepository = menuReportTMSCheckMainRepository;

        this.menuReportTMSCheckDetailRepository = menuReportTMSCheckDetailRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<Service_Type_List> service_Type_List;
        List<User_List> user_List;
        List<User_Report_List> user_Report_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.setViewName("report_tms_check/cover");
        return mav;
    }

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/get_main_table")
    public JSONDatatable get_main_table(
            @RequestParam(value="report_tms_check_start_date") String report_tms_check_start_date,
            @RequestParam(value="report_tms_check_end_date") String report_tms_check_end_date
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(report_tms_check_start_date != null && report_tms_check_end_date != null && !report_tms_check_start_date.equals("") && !report_tms_check_end_date.equals("")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_REPORTS_VIEW2.FILL_REPORT_137")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, report_tms_check_start_date)
                    .setParameter(4, report_tms_check_end_date);
                    ;
            DelQuery.execute();

            result.setData(menuReportTMSCheckMainRepository.queryGetMainRep137(
                    user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        return result;
    }

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="req_ser_id") Long req_ser_id,
            @RequestParam(value="req_month") Long req_month
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(req_ser_id != null && req_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportTMSCheckDetailRepository.queryGetDetailRep137(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_ser_id, req_month
            ));
        }
        return result;
    }


}
