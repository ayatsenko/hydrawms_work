package com.idltd.hydramob.controller.dasboard_employee_balance;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Report_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Report_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.dashboard_employee_balance.MenuDashboardEmployeeBalanceClientsRepository;
import com.idltd.hydramob.repository.dashboard_employee_balance.MenuDashboardEmployeeBalanceDetailRepository;
import com.idltd.hydramob.repository.dashboard_employee_balance.MenuDashboardEmployeeBalanceFinanceRepository;
import com.idltd.hydramob.repository.dashboard_employee_balance.MenuDashboardEmployeeBalanceServiceRepository;
import com.idltd.hydramob.repository.main_variable.DetailFinanceDownloadDateRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dashboard_employee_balance")
public class DashBoardEmployeeBalaceController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private User_Report_ListRepository user_Report_ListRepository;
    private MenuDashboardEmployeeBalanceDetailRepository menuDashboardEmployeeBalanceDetailRepository;
    private MenuDashboardEmployeeBalanceServiceRepository menuDashboardEmployeeBalanceServiceRepository;
    private MenuDashboardEmployeeBalanceClientsRepository menuDashboardEmployeeBalanceClientsRepository;
    private MenuDashboardEmployeeBalanceFinanceRepository menuDashboardEmployeeBalanceFinanceRepository;
    private DetailFinanceDownloadDateRepository detailFinanceDownloadDateRepository;

    public DashBoardEmployeeBalaceController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            User_Report_ListRepository user_Report_ListRepository,
            MenuDashboardEmployeeBalanceDetailRepository menuDashboardEmployeeBalanceDetailRepository,
            MenuDashboardEmployeeBalanceServiceRepository menuDashboardEmployeeBalanceServiceRepository,
            MenuDashboardEmployeeBalanceClientsRepository menuDashboardEmployeeBalanceClientsRepository,
            MenuDashboardEmployeeBalanceFinanceRepository menuDashboardEmployeeBalanceFinanceRepository,
            DetailFinanceDownloadDateRepository detailFinanceDownloadDateRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.user_Report_ListRepository = user_Report_ListRepository;
        this.menuDashboardEmployeeBalanceDetailRepository = menuDashboardEmployeeBalanceDetailRepository;
        this.menuDashboardEmployeeBalanceServiceRepository = menuDashboardEmployeeBalanceServiceRepository;
        this.menuDashboardEmployeeBalanceClientsRepository = menuDashboardEmployeeBalanceClientsRepository;
        this.menuDashboardEmployeeBalanceFinanceRepository = menuDashboardEmployeeBalanceFinanceRepository;
        this.detailFinanceDownloadDateRepository = detailFinanceDownloadDateRepository;
    }

    @RequestMapping
    public ModelAndView index(
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "employee_balance_show_id", required = false, defaultValue = "0") Long employee_balance_show_id,
            @RequestParam(name = "employee_balance_start_date", required = false) String employee_balance_start_date,
            @RequestParam(name = "employee_balance_end_date", required = false) String employee_balance_end_date,
            @RequestParam(name = "employee_tab_id", required = false) Long employee_tab_id
    ){
        ModelAndView mav = new ModelAndView();
        List<User_Report_List> user_Report_List;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_id == null && user_Role_List.get(0).role_id == 8) {
            mav.addObject("user_id", new Long(1));
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }
        else if(user_id == null && user_Role_List.get(0).role_id != 8){
            mav.addObject("user_id", user_List.get(0).id);
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }
        else{
            mav.addObject("user_id", user_id);
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }

        mav.addObject("employee_balance_show_id", employee_balance_show_id);
        mav.addObject("employee_balance_start_date", employee_balance_start_date);
        mav.addObject("employee_balance_end_date", employee_balance_end_date);

        mav.setViewName("dashboard_employee_balance/cover");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_user_id") Long req_user_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(start_date.length() > 0) {
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

            StoredProcedureQuery FillReportTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_REPORTS_VIEW2.pr_vREPORT_124_FILL_TEMP")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, StartResult)
                    .setParameter(2, EndResult)
                    .setParameter(3, user_List.get(0).id)
                    .setParameter(4, user_Role_List.get(0).role_id)
                    .setParameter(5, req_user_id)
                    ;
            FillReportTempQuery.execute();

            result.setData(menuDashboardEmployeeBalanceDetailRepository.queryGetAllRep124Temp(
                    StartResult, EndResult, user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id
            ));
        }
        return result;
    }

    @PostMapping("/get_service_table")
    public JSONDatatable get_service_table(
            @RequestParam(name ="req_user_id", required = false) Long req_user_id,
            @RequestParam(name ="month", required = false) Long month_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(month_id >= 0 && req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDashboardEmployeeBalanceServiceRepository.queryGetAllRep124Service(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id, month_id
            ));
        }
        return result;
    }

    @PostMapping("/get_clients_table")
    public JSONDatatable get_clients_table(
            @RequestParam(value="req_user_id", required = false) Long req_user_id,
            @RequestParam(value="req_ser_id", required = false) Long req_ser_id,
            @RequestParam(value="month", required = false) Long month
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(month >= 0 && req_user_id != null && req_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDashboardEmployeeBalanceClientsRepository.queryGetAllRep124Clients(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id, req_ser_id, month));
        }
        return result;
    }

    @PostMapping("/get_finance_table")
    public JSONDatatable get_finance_table(
            @RequestParam(value="req_user_id", required = false) Long req_user_id,
            @RequestParam(value="req_ser_id", required = false) Long req_ser_id,
            @RequestParam(value="req_cnt_id", required = false) Long req_cnt_id,
            @RequestParam(value="req_month", required = false) Long req_month
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(req_month != null && req_user_id != null && req_ser_id != null && req_cnt_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDashboardEmployeeBalanceFinanceRepository.queryGetAllRep124Finance(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id, req_ser_id, req_cnt_id, req_month));
        }
        return result;
    }

    @GetMapping("/get_finance_download_date")
    public JSONDatatable get_finance_download_date(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null) {
            result.setData(detailFinanceDownloadDateRepository.queryDetailDownloadDateBy(
            ));
        }
        return result;
    }
}
