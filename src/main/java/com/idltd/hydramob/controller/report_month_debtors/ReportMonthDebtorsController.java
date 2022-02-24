package com.idltd.hydramob.controller.report_month_debtors;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Report_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.report_month_debtors.MenuReportMonthDebtorsMainRepository;
import com.idltd.hydramob.repository.report_month_debtors.MenuReportMonthDebtorsMonthRepository;
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
import java.util.List;

@RestController
@RequestMapping("/report_month_debtors")
public class ReportMonthDebtorsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuReportMonthDebtorsMainRepository menuReportMonthDebtorsMainRepository;
    private MenuReportMonthDebtorsMonthRepository menuReportMonthDebtorsMonthRepository;

    public ReportMonthDebtorsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuReportMonthDebtorsMainRepository menuReportMonthDebtorsMainRepository,
            MenuReportMonthDebtorsMonthRepository menuReportMonthDebtorsMonthRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuReportMonthDebtorsMainRepository = menuReportMonthDebtorsMainRepository;
        this.menuReportMonthDebtorsMonthRepository = menuReportMonthDebtorsMonthRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<User_Report_List> user_Report_List;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        mav.setViewName("report_month_debtors/cover");
        return mav;
    }
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/get_main_table")
    public JSONDatatable get_main_table(
            @RequestParam(value="req_year") Long req_year
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_year != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportMonthDebtorsMainRepository.queryGetMonthDebtorsMain(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_year
            ));
        }
        return result;
    }

    @PostMapping("/get_month_table")
    public JSONDatatable get_month_table(
            @RequestParam(value="req_ser_id") Long req_ser_id,
            @RequestParam(value="req_year") Long req_year,
            @RequestParam(value="req_month") Long req_month
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_year != null && req_ser_id != null && req_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportMonthDebtorsMonthRepository.queryGetMonthDebtorsMonth(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_ser_id, req_year, req_month
            ));
        }
        return result;
    }
}
