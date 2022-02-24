package com.idltd.hydramob.controller.dashboard_tms_status;

import com.idltd.hydramob.entity.Service_Type_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.Service_Type_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.dashboard_tms_status.DetailDashboardTMSServiceDetailRepository;
import com.idltd.hydramob.repository.dashboard_tms_status.MenuDashboardTMSServiceDetailRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dashboard_tms_status")
public class DashboardTMSStatusController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;

    private MenuDashboardTMSServiceDetailRepository menuDashboardTMSServiceDetailRepository;
    private DetailDashboardTMSServiceDetailRepository detailDashboardTMSServiceDetailRepository;

    public DashboardTMSStatusController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,

            MenuDashboardTMSServiceDetailRepository menuDashboardTMSServiceDetailRepository,
            DetailDashboardTMSServiceDetailRepository detailDashboardTMSServiceDetailRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;

        this.menuDashboardTMSServiceDetailRepository = menuDashboardTMSServiceDetailRepository;
        this.detailDashboardTMSServiceDetailRepository = detailDashboardTMSServiceDetailRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Service_Type_List> service_Type_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("ser_id", 0);
        service_Type_List = service_Type_ListRepository.queryReportSerIDByUserID(user_List.get(0).id);
        mav.addObject("service_list", service_Type_List);

        mav.setViewName("dashboard_tms_status/cover");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(value="start_date", required = false) String start_date,
            @RequestParam(value="end_date", required = false) String end_date,
            @RequestParam(value="req_ser_id", required = false) Long req_ser_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(start_date.length() > 0 && req_ser_id != null && req_ser_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
            String StartResult = "";
            String EndResult = "";
            Date Date1;
            Date Date2;

            Date1 = df.parse(start_date);
            StartResult = newdf.format(Date1);

            Date2 = df.parse(end_date);
            EndResult = newdf.format(Date2);

            result.setData(menuDashboardTMSServiceDetailRepository.queryGetMenuDashboardTMSStatusDetail(user_List.get(0).id, user_Role_List.get(0).role_id, StartResult, EndResult, req_ser_id));
        }
        return result;
    }

    @PostMapping("/get_tab_table")
    public JSONDatatable get_tab_table(
            @RequestParam(value="start_date", required = false) String start_date,
            @RequestParam(value="end_date", required = false) String end_date,
            @RequestParam(value="req_ser_id", required = false) Long req_ser_id,
            @RequestParam(value="req_user_id", required = false) Long req_user_id,
            @RequestParam(value="req_clm_status_id", required = false) Long req_clm_status_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(req_ser_id != null && req_user_id != null && req_clm_status_id != null && start_date.length() > 0 && end_date.length() > 0  && req_ser_id >= 0 && req_user_id >= 0 && req_clm_status_id > -2) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
            String StartResult = "";
            String EndResult = "";
            Date Date1;
            Date Date2;

            Date1 = df.parse(start_date);
            StartResult = newdf.format(Date1);

            Date2 = df.parse(end_date);
            EndResult = newdf.format(Date2);

            result.setData(detailDashboardTMSServiceDetailRepository.queryGetDetailDashboardTMSStatusDetail(
                    user_List.get(0).id, user_Role_List.get(0).role_id, StartResult, EndResult, req_ser_id, req_user_id, req_clm_status_id
            ));
        }
        return result;
    }
}
