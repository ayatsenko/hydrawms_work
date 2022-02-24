package com.idltd.hydramob.controller.report_employee_clients;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Report_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Report_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.report_emplyee_clients.*;
import com.idltd.hydramob.utils.JSONDatatable;
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
import java.util.List;

@RestController
@RequestMapping("/report_employee_clients")
public class ReportEmployeeClientsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private User_Report_ListRepository user_Report_ListRepository;
    private MenuReportEmployeeClientsDetailRepository menuReportEmployeeClientsDetailRepository;
    private MenuReportEmployeeClientsNotFillRepository menuReportEmployeeClientsNotFillRepository;
    private MenuReportEmployeeClientsNotFillPassRepository menuReportEmployeeClientsNotFillPassRepository;
    private MenuReportEmployeeClientsLostRepository menuReportEmployeeClientsLostRepository;
    private MenuReportEmployeeClientsOutTimeRepository menuReportEmployeeClientsOutTimeRepository;
    private MenuReportEmployeeClientsLostServiceRepository menuReportEmployeeClientsLostServiceRepository;

    public ReportEmployeeClientsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            User_Report_ListRepository user_Report_ListRepository,
            MenuReportEmployeeClientsDetailRepository menuReportEmployeeClientsDetailRepository,
            MenuReportEmployeeClientsNotFillRepository menuReportEmployeeClientsNotFillRepository,
            MenuReportEmployeeClientsNotFillPassRepository menuReportEmployeeClientsNotFillPassRepository,
            MenuReportEmployeeClientsLostRepository menuReportEmployeeClientsLostRepository,
            MenuReportEmployeeClientsOutTimeRepository menuReportEmployeeClientsOutTimeRepository,
            MenuReportEmployeeClientsLostServiceRepository menuReportEmployeeClientsLostServiceRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.user_Report_ListRepository = user_Report_ListRepository;
        this.menuReportEmployeeClientsDetailRepository = menuReportEmployeeClientsDetailRepository;
        this.menuReportEmployeeClientsNotFillRepository = menuReportEmployeeClientsNotFillRepository;
        this.menuReportEmployeeClientsNotFillPassRepository = menuReportEmployeeClientsNotFillPassRepository;
        this.menuReportEmployeeClientsLostRepository = menuReportEmployeeClientsLostRepository;
        this.menuReportEmployeeClientsOutTimeRepository = menuReportEmployeeClientsOutTimeRepository;
        this.menuReportEmployeeClientsLostServiceRepository = menuReportEmployeeClientsLostServiceRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<User_Report_List> user_Report_List;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_Role_List.get(0).role_id == 8) {
            mav.addObject("user_id", new Long(1));
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }
        else{
            mav.addObject("user_id", user_List.get(0).id);
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }
        mav.setViewName("report_employee_clients/cover");
        return mav;
    }
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="req_user_id") Long req_user_id,
            @RequestParam(value="start_date") String start_date
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportEmployeeClientsDetailRepository.queryGetAllReport131(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id, start_date));
        }
        return result;
    }

    @PostMapping("/list_del_link")
    public void task_uncheck(
            @RequestParam(name = "cntul_id") long cntul_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DelUserClintQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContragentUserLink")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, cntul_id);
        DelUserClintQuery.execute();
        return;
    }

    @PostMapping("/get_notfill_table")
    public JSONDatatable get_notfill_table(
            @RequestParam(value="req_user_id") Long req_user_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportEmployeeClientsNotFillRepository.queryGetAllReport131NotFill(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id));
        }
        return result;
    }

    @PostMapping("/get_notfill_pass_table")
    public JSONDatatable get_notfill_pass_table(
            @RequestParam(value="req_user_id") Long req_user_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportEmployeeClientsNotFillPassRepository.queryGetAllReport131NotFillPass(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id));
        }
        return result;
    }

    @PostMapping("/get_lost_table")
    public JSONDatatable get_lost_table(
            @RequestParam(value="req_user_id") Long req_user_id,
            @RequestParam(value="start_date") String start_date
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportEmployeeClientsLostRepository.queryGetAllReport131Lost(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id, start_date));
        }
        return result;
    }

    @PostMapping("/get_outtime_table")
    public JSONDatatable get_outtime_table(
            @RequestParam(value="req_user_id") Long req_user_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportEmployeeClientsOutTimeRepository.queryGetAllReport131OutTime(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id));
        }
        return result;
    }

    @PostMapping("/get_service_table")
    public JSONDatatable get_service_table(
            @RequestParam(value="req_user_id") Long req_user_id,
            @RequestParam(value="start_date") String start_date
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportEmployeeClientsLostServiceRepository.queryGetAllReport131LostService(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id, start_date));
        }
        return result;
    }
}
