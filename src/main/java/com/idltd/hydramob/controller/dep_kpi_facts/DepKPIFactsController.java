package com.idltd.hydramob.controller.dep_kpi_facts;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.dep_kpi_facts.MenuDepKPIFactsMainListRepository;
import com.idltd.hydramob.repository.dep_kpi_facts.MenuDepKPIFactsUsersListRepository;
import com.idltd.hydramob.repository.dep_kpi_facts.*;
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
@RequestMapping("/dep_kpi_facts")
public class DepKPIFactsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuDepKPIFactsMainListRepository menuDepKPIFactsMainListRepository;
    private MenuDepKPIFactsUsersListRepository menuDepKPIFactsUsersListRepository;

    private MenuDepKPIFactsCalcListRepository menuDepKPIFactsCalcListRepository;
    private MenuDepKPIFactsCRMListRepository menuDepKPIFactsCRMListRepository;
    private MenuDepKPIFactMeetsRepository menuDepKPIFactMeetsRepository;
    private MenuDepKPIFactCallsRepository menuDepKPIFactCallsRepository;
    private MenuDepKPIFactClientsFinRepository menuDepKPIFactClientsFinRepository;
    private MenuDepKPIFactFinanceRepository menuDepKPIFactFinanceRepository;
    private MenuDepKPIFactRequestsRepository menuDepKPIFactRequestsRepository;
    private MenuDepKPIFactTendersRepository menuDepKPIFactTendersRepository;
    private MenuDepKPIFactCRMClientsRepository menuDepKPIFactCRMClientsRepository;
    private MenuDepKPIFactCRMToplistRepository menuDepKPIFactCRMToplistRepository;
    private MenuDepKPIFactCRMGeneralRepository menuDepKPIFactCRMGeneralRepository;
    private MenuDepKPIFactCRMTodoRepository menuDepKPIFactCRMTodoRepository;
    private MenuDepKPIFactCRMRequestsRepository menuDepKPIFactCRMRequestsRepository;
    private MenuDepKPIFactCRMTendersRepository menuDepKPIFactCRMTendersRepository;
    private MenuDepKPIFactCSOPSMeetsListRepository menuDepKPIFactCSOPSMeetsListRepository;
    private MenuDepKPIFactCSMeetsListRepository menuDepKPIFactCSMeetsListRepository;
    private MenuDepKPIFactsClientsOutTimeRepository menuDepKPIFactsClientsOutTimeRepository;
    private MenuDepKPIFactsClientsNotFillRepository menuDepKPIFactsClientsNotFillRepository;
    private MenuDepKPIFactsClientsNotFillPassRepository menuDepKPIFactsClientsNotFillPassRepository;
    private MenuDepKPIFactsClientsLostRepository menuDepKPIFactsClientsLostRepository;
    private MenuDepKPIFactChiefClientsFinRepository menuDepKPIFactChiefClientsFinRepository;
    private MenuDepKPIFactChiefBonusClientsFinRepository menuDepKPIFactChiefBonusClientsFinRepository;
    private MenuDepKPIFactDebtorsRepository menuDepKPIFactDebtorsRepository;
    private MenuDepKPIFactChiefDebtorsRepository menuDepKPIFactChiefDebtorsRepository;
    private MenuDepKPIFactClientsProfitRepository menuDepKPIFactClientsProfitRepository;
    private MenuDepKPIFactProfitRepository menuDepKPIFactProfitRepository;
    private MenuDepKPIFactClientsDebtorsRepository menuDepKPIFactClientsDebtorsRepository;
    private MenuDepKPIFactClientsUnpaidRepository menuDepKPIFactClientsUnpaidRepository;
    private MenuDepKPIFactUnpaidRepository menuDepKPIFactUnpaidRepository;

    private MenuDepKPIFactChiefClientsProfitRepository menuDepKPIFactChiefClientsProfitRepository;
    private MenuDepKPIFactChiefProfitRepository menuDepKPIFactChiefProfitRepository;
    private MenuDepKPIFactChiefClientsUnpaidRepository menuDepKPIFactChiefClientsUnpaidRepository;
    private MenuDepKPIFactChiefUnpaidRepository menuDepKPIFactChiefUnpaidRepository;
    private MenuDepKPIFactChiefClientsDebtorsRepository menuDepKPIFactChiefClientsDebtorsRepository;
    private MenuDepKPIFactChiefDebtorsPaidRepository menuDepKPIFactChiefDebtorsPaidRepository;

    private MenuDepKPIFactOPSHeadNewClientFinanceRepository menuDepKPIFactOPSHeadNewClientFinanceRepository;
    private MenuDepKPIFactOPSHeadNewFinanceRepository menuDepKPIFactOPSHeadNewFinanceRepository;

    private MenuDepKPIFactOPSHeadOldClientFinanceRepository menuDepKPIFactOPSHeadOldClientFinanceRepository;
    private MenuDepKPIFactOPSHeadOldFinanceRepository menuDepKPIFactOPSHeadOldFinanceRepository;

    public DepKPIFactsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuDepKPIFactsMainListRepository menuDepKPIFactsMainListRepository,
            MenuDepKPIFactsUsersListRepository menuDepKPIFactsUsersListRepository,

            MenuDepKPIFactsCalcListRepository menuDepKPIFactsCalcListRepository,
            MenuDepKPIFactsCRMListRepository menuDepKPIFactsCRMListRepository,
            MenuDepKPIFactMeetsRepository menuDepKPIFactMeetsRepository,
            MenuDepKPIFactCallsRepository menuDepKPIFactCallsRepository,
            MenuDepKPIFactFinanceRepository menuDepKPIFactFinanceRepository,
            MenuDepKPIFactClientsFinRepository menuDepKPIFactClientsFinRepository,

            MenuDepKPIFactRequestsRepository menuDepKPIFactRequestsRepository,
            MenuDepKPIFactTendersRepository menuDepKPIFactTendersRepository,
            MenuDepKPIFactCRMClientsRepository menuDepKPIFactCRMClientsRepository,
            MenuDepKPIFactCRMToplistRepository menuDepKPIFactCRMToplistRepository,
            MenuDepKPIFactCRMGeneralRepository menuDepKPIFactCRMGeneralRepository,
            MenuDepKPIFactCRMTodoRepository menuDepKPIFactCRMTodoRepository,
            MenuDepKPIFactCRMRequestsRepository menuDepKPIFactCRMRequestsRepository,
            MenuDepKPIFactCRMTendersRepository menuDepKPIFactCRMTendersRepository,
            MenuDepKPIFactCSOPSMeetsListRepository menuDepKPIFactCSOPSMeetsListRepository,
            MenuDepKPIFactCSMeetsListRepository menuDepKPIFactCSMeetsListRepository,
            MenuDepKPIFactsClientsOutTimeRepository menuDepKPIFactsClientsOutTimeRepository,
            MenuDepKPIFactsClientsNotFillRepository menuDepKPIFactsClientsNotFillRepository,
            MenuDepKPIFactsClientsNotFillPassRepository menuDepKPIFactsClientsNotFillPassRepository,
            MenuDepKPIFactsClientsLostRepository menuDepKPIFactsClientsLostRepository,
            MenuDepKPIFactChiefClientsFinRepository menuDepKPIFactChiefClientsFinRepository,
            MenuDepKPIFactChiefBonusClientsFinRepository menuDepKPIFactChiefBonusClientsFinRepository,
            MenuDepKPIFactDebtorsRepository menuDepKPIFactDebtorsRepository,
            MenuDepKPIFactChiefDebtorsRepository menuDepKPIFactChiefDebtorsRepository,
            MenuDepKPIFactClientsProfitRepository menuDepKPIFactClientsProfitRepository,
            MenuDepKPIFactProfitRepository menuDepKPIFactProfitRepository,
            MenuDepKPIFactClientsDebtorsRepository menuDepKPIFactClientsDebtorsRepository,
            MenuDepKPIFactClientsUnpaidRepository menuDepKPIFactClientsUnpaidRepository,
            MenuDepKPIFactUnpaidRepository menuDepKPIFactUnpaidRepository,

            MenuDepKPIFactChiefClientsProfitRepository menuDepKPIFactChiefClientsProfitRepository,
            MenuDepKPIFactChiefProfitRepository menuDepKPIFactChiefProfitRepository,
            MenuDepKPIFactChiefClientsUnpaidRepository menuDepKPIFactChiefClientsUnpaidRepository,
            MenuDepKPIFactChiefUnpaidRepository menuDepKPIFactChiefUnpaidRepository,
            MenuDepKPIFactChiefClientsDebtorsRepository menuDepKPIFactChiefClientsDebtorsRepository,
            MenuDepKPIFactChiefDebtorsPaidRepository menuDepKPIFactChiefDebtorsPaidRepository,

            MenuDepKPIFactOPSHeadNewClientFinanceRepository menuDepKPIFactOPSHeadNewClientFinanceRepository,
            MenuDepKPIFactOPSHeadNewFinanceRepository menuDepKPIFactOPSHeadNewFinanceRepository,

            MenuDepKPIFactOPSHeadOldClientFinanceRepository menuDepKPIFactOPSHeadOldClientFinanceRepository,
            MenuDepKPIFactOPSHeadOldFinanceRepository menuDepKPIFactOPSHeadOldFinanceRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuDepKPIFactsMainListRepository = menuDepKPIFactsMainListRepository;
        this.menuDepKPIFactsUsersListRepository = menuDepKPIFactsUsersListRepository;

        this.menuDepKPIFactsCalcListRepository = menuDepKPIFactsCalcListRepository;
        this.menuDepKPIFactsCRMListRepository = menuDepKPIFactsCRMListRepository;
        this.menuDepKPIFactMeetsRepository = menuDepKPIFactMeetsRepository;
        this.menuDepKPIFactCallsRepository = menuDepKPIFactCallsRepository;
        this.menuDepKPIFactFinanceRepository = menuDepKPIFactFinanceRepository;
        this.menuDepKPIFactClientsFinRepository = menuDepKPIFactClientsFinRepository;

        this.menuDepKPIFactRequestsRepository = menuDepKPIFactRequestsRepository;
        this.menuDepKPIFactTendersRepository = menuDepKPIFactTendersRepository;
        this.menuDepKPIFactCRMClientsRepository = menuDepKPIFactCRMClientsRepository;
        this.menuDepKPIFactCRMToplistRepository = menuDepKPIFactCRMToplistRepository;
        this.menuDepKPIFactCRMGeneralRepository = menuDepKPIFactCRMGeneralRepository;
        this.menuDepKPIFactCRMTodoRepository = menuDepKPIFactCRMTodoRepository;
        this.menuDepKPIFactCRMRequestsRepository = menuDepKPIFactCRMRequestsRepository;
        this.menuDepKPIFactCRMTendersRepository = menuDepKPIFactCRMTendersRepository;
        this.menuDepKPIFactCSOPSMeetsListRepository = menuDepKPIFactCSOPSMeetsListRepository;
        this.menuDepKPIFactCSMeetsListRepository = menuDepKPIFactCSMeetsListRepository;
        this.menuDepKPIFactsClientsOutTimeRepository = menuDepKPIFactsClientsOutTimeRepository;
        this.menuDepKPIFactsClientsNotFillRepository = menuDepKPIFactsClientsNotFillRepository;
        this.menuDepKPIFactsClientsNotFillPassRepository = menuDepKPIFactsClientsNotFillPassRepository;
        this.menuDepKPIFactsClientsLostRepository = menuDepKPIFactsClientsLostRepository;
        this.menuDepKPIFactChiefClientsFinRepository = menuDepKPIFactChiefClientsFinRepository;
        this.menuDepKPIFactChiefBonusClientsFinRepository = menuDepKPIFactChiefBonusClientsFinRepository;
        this.menuDepKPIFactDebtorsRepository = menuDepKPIFactDebtorsRepository;
        this.menuDepKPIFactChiefDebtorsRepository = menuDepKPIFactChiefDebtorsRepository;
        this.menuDepKPIFactClientsProfitRepository = menuDepKPIFactClientsProfitRepository;
        this.menuDepKPIFactProfitRepository = menuDepKPIFactProfitRepository;
        this.menuDepKPIFactClientsDebtorsRepository = menuDepKPIFactClientsDebtorsRepository;
        this.menuDepKPIFactClientsUnpaidRepository = menuDepKPIFactClientsUnpaidRepository;
        this.menuDepKPIFactUnpaidRepository = menuDepKPIFactUnpaidRepository;

        this.menuDepKPIFactChiefClientsProfitRepository = menuDepKPIFactChiefClientsProfitRepository;
        this.menuDepKPIFactChiefProfitRepository = menuDepKPIFactChiefProfitRepository;
        this.menuDepKPIFactChiefClientsUnpaidRepository = menuDepKPIFactChiefClientsUnpaidRepository;
        this.menuDepKPIFactChiefUnpaidRepository = menuDepKPIFactChiefUnpaidRepository;
        this.menuDepKPIFactChiefClientsDebtorsRepository = menuDepKPIFactChiefClientsDebtorsRepository;
        this.menuDepKPIFactChiefDebtorsPaidRepository = menuDepKPIFactChiefDebtorsPaidRepository;

        this.menuDepKPIFactOPSHeadNewClientFinanceRepository = menuDepKPIFactOPSHeadNewClientFinanceRepository;
        this.menuDepKPIFactOPSHeadNewFinanceRepository = menuDepKPIFactOPSHeadNewFinanceRepository;

        this.menuDepKPIFactOPSHeadOldClientFinanceRepository = menuDepKPIFactOPSHeadOldClientFinanceRepository;
        this.menuDepKPIFactOPSHeadOldFinanceRepository = menuDepKPIFactOPSHeadOldFinanceRepository;
    }
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,

                              @RequestParam(name = "dep_kpi_facts_list_table_order_column", required = false) Long dep_kpi_facts_list_table_order_column,
                              @RequestParam(name = "dep_kpi_facts_list_table_order_type", required = false) String dep_kpi_facts_list_table_order_type,
                              @RequestParam(name = "dep_kpi_facts_list_table_search", required = false) String dep_kpi_facts_list_table_search,
                              @RequestParam(name = "dep_kpi_facts_list_table_pagelen", required = false) Long dep_kpi_facts_list_table_pagelen,
                              @RequestParam(name = "dep_kpi_facts_list_table_page", required = false) Long dep_kpi_facts_list_table_page
    ){
        model.addObject("dep_kpi_year", dep_kpi_year);

        model.addObject("dep_kpi_facts_list_table_order_column", dep_kpi_facts_list_table_order_column);
        model.addObject("dep_kpi_facts_list_table_order_type", dep_kpi_facts_list_table_order_type);
        model.addObject("dep_kpi_facts_list_table_search", dep_kpi_facts_list_table_search);
        model.addObject("dep_kpi_facts_list_table_pagelen", dep_kpi_facts_list_table_pagelen);
        model.addObject("dep_kpi_facts_list_table_page", dep_kpi_facts_list_table_page);

        model.setViewName("dep_kpi_facts/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "dep_kpi_facts_year", required = false) Long dep_kpi_facts_year
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(dep_kpi_facts_year != null) {
            result.setData(menuDepKPIFactsMainListRepository.queryDepKPIFactsMainListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_facts_year
            ));
        }
        return result;
    }

    @PostMapping("/get_users_table")
    public JSONDatatable get_users_table(
            @RequestParam(name = "dep_kpi_facts_year", required = false) Long dep_kpi_facts_year,
            @RequestParam(name = "dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(dep_kpi_facts_year != null) {
            result.setData(menuDepKPIFactsUsersListRepository.queryDepKPIFactsUsersListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_facts_year, dep_id
            ));
        }
        return result;
    }

    @PostMapping("/get_calc_table")
    public JSONDatatable get_calc_table(
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_facts_year", required = false) Long dep_kpi_facts_year,
            @RequestParam(name = "dep_kpi_facts_month", required = false) Long dep_kpi_facts_month,
            @RequestParam(name = "dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_user_id != null && dep_kpi_facts_year != null && dep_kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsCalcListRepository.queryUserKPICalcMainListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_facts_year, dep_kpi_facts_month, dep_id
            ));
        }
        return result;
    }

    @PostMapping("/get_crm_table")
    public JSONDatatable get_crm_table(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_facts_year", required = false) Long dep_kpi_facts_year,
            @RequestParam(name = "dep_kpi_facts_month", required = false) Long dep_kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if( dep_kpi_param_id != null && dep_kpi_user_id != null && dep_kpi_facts_year != null && dep_kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
            if(dep_kpi_param_id == 5) {
                result.setData(menuDepKPIFactsCRMListRepository.queryUserKPICRMMainListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_facts_year, dep_kpi_facts_month
                ));
            }
            else if(dep_kpi_param_id == 13){
                result.setData(menuDepKPIFactsCRMListRepository.queryUserKPICRMChiefMainListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_facts_year, dep_kpi_facts_month
                ));
            }
        }
        return result;
    }

    //Meetings
    @PostMapping("/meets_menu")
    public JSONDatatable meets_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactMeetsRepository.queryMenuDepKPIFactMeetsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Calls
    @PostMapping("/calls_menu")
    public JSONDatatable calls_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCallsRepository.queryMenuDepKPIFactCallsByID(user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month));
        }
        return result;
    }

    //Finance
    @PostMapping("/get_clients_finance_menu")
    public JSONDatatable get_clients_finance_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactClientsFinRepository.queryMenuDepKPIFactClientsFinByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/finance_menu")
    public JSONDatatable finance_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null && dep_kpi_user_id != null && dep_kpi_cnt_id != null && dep_kpi_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactFinanceRepository.queryMenuDepKPIFactFinanceByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }
    //Requests
    @PostMapping("/requests_menu")
    public JSONDatatable requests_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactRequestsRepository.queryMenuDepKPIFactRequestsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }
    //Tenders
    @PostMapping("/tenders_menu")
    public JSONDatatable tenders_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactTendersRepository.queryMenuDepKPIFactTendersByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //CRM Clients List
    @PostMapping("/crm_clients_menu")
    public JSONDatatable crm_clients_menu(
            @RequestParam(name = "dep_kpi_crm_param_id", required = false) Long dep_kpi_crm_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_crm_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCRMClientsRepository.queryMenuDepKPIFactCRMClientsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //CRM Toplist List
    @PostMapping("/crm_toplist_menu")
    public JSONDatatable crm_toplist_menu(
            @RequestParam(name = "dep_kpi_crm_param_id", required = false) Long dep_kpi_crm_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_crm_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCRMToplistRepository.queryMenuDepKPIFactCRMToplistByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //CRM General List
    @PostMapping("/crm_general_menu")
    public JSONDatatable crm_general_menu(
            @RequestParam(name = "dep_kpi_crm_param_id", required = false) Long dep_kpi_crm_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_crm_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCRMGeneralRepository.queryMenuDepKPIFactCRMGeneralByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //CRM TodoMenu
    @PostMapping("/crm_todo_menu")
    public JSONDatatable crm_todo_menu(
            @RequestParam(name = "dep_kpi_crm_param_id", required = false) Long dep_kpi_crm_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_crm_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCRMTodoRepository.queryMenuDepKPIFactCRMTodoByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }
    //CRM Requests
    @PostMapping("/crm_requests_menu")
    public JSONDatatable crm_requests_menu(
            @RequestParam(name = "dep_kpi_crm_param_id", required = false) Long dep_kpi_crm_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_crm_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCRMRequestsRepository.queryMenuDepKPIFactCRMRequestsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Tenders
    @PostMapping("/crm_tenders_menu")
    public JSONDatatable crm_tenders_menu(
            @RequestParam(name = "dep_kpi_crm_param_id", required = false) Long dep_kpi_crm_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_crm_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCRMTendersRepository.queryMenuDepKPIFactCRMTendersByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

   @PostMapping("/add_meets_block")
   public JSONDatatable add_meets_block(
           @RequestParam(name = "block_act_id", required = false) Long block_act_id,
           @RequestParam(name = "block_user_id", required = false) Long block_user_id,
           @RequestParam(name = "block_year", required = false) Long block_year,
           @RequestParam(name = "block_month", required = false) Long block_month
   ) {
       if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
       {
           List<User_List> user_List;
           List<User_Role_List> user_Role_List;

           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           String authname = auth.getName();
           user_List = user_ListRepository.queryUserByName(authname);
           user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

           StoredProcedureQuery AddKPIMeetsBlockQuery = entityManager
                   .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIMeetsBlock")
                   .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                   .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                   .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                   .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                   .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                   .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                   .setParameter(1, user_List.get(0).id)
                   .setParameter(2, user_Role_List.get(0).role_id)
                   .setParameter(3, block_act_id)
                   .setParameter(4, block_user_id)
                   .setParameter(5, block_year)
                   .setParameter(6, block_month)
                   ;
           AddKPIMeetsBlockQuery.execute();
       }
       return null;
   }

    @PostMapping("/del_meets_block")
    public JSONDatatable del_meets_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIMeetsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIMeetsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIMeetsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_calls_block")
    public JSONDatatable add_calls_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPICallsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICallsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPICallsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_calls_block")
    public JSONDatatable del_calls_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPICallsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICallsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPICallsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_profit_block")
    public JSONDatatable add_profit_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIFinanceBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIFinanceBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_profit_block")
    public JSONDatatable del_profit_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIFinanceBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_requests_block")
    public JSONDatatable add_requests_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIRequestsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIRequestsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIRequestsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_requests_block")
    public JSONDatatable del_requests_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIRequestsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIRequestsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIRequestsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_tenders_block")
    public JSONDatatable add_tenders_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPITendersBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_tenders_block")
    public JSONDatatable del_tenders_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPITendersBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_crm_client_block")
    public JSONDatatable add_crm_client_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmClientsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_crm_client_block")
    public JSONDatatable del_crm_client_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICrmClientsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_crm_todo_block")
    public JSONDatatable add_crm_todo_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmTodoBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_crm_todo_block")
    public JSONDatatable del_crm_todo_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICrmTodoBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_crm_requests_block")
    public JSONDatatable add_crm_requests_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmRequestsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_crm_requests_block")
    public JSONDatatable del_crm_requests_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICrmRequestsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_crm_tenders_block")
    public JSONDatatable add_crm_tenders_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmTendersBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_crm_tenders_block")
    public JSONDatatable del_crm_tenders_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICrmTendersBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITendersBlockQuery.execute();
        }
        return null;
    }

//CSOPS Meetings
    @PostMapping("/csops_meetings_menu")
    public JSONDatatable csops_meetings_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCSOPSMeetsListRepository.queryMenuDepKPIFactCSOPSMeetsListByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/add_csops_meets_block")
    public JSONDatatable add_csops_meets_block(
            @RequestParam(name = "block_user_kpi_meet_id", required = false) Long block_user_kpi_meet_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_user_kpi_meet_id != null && block_user_kpi_meet_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITCsOpsMeetsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICsOpsMeetingsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_user_kpi_meet_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITCsOpsMeetsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_csops_meets_block")
    public JSONDatatable del_csops_meets_block(
            @RequestParam(name = "block_user_kpi_meet_id", required = false) Long block_user_kpi_meet_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_user_kpi_meet_id != null && block_user_kpi_meet_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITCsOpsMeetsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICsOpsMeetingsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_user_kpi_meet_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITCsOpsMeetsBlockQuery.execute();
        }
        return null;
    }

//CS Meetings
    @PostMapping("/cs_meetings_menu")
    public JSONDatatable cs_meetings_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCSMeetsListRepository.queryMenuDepKPIFactCsMeetingsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/add_cs_meets_block")
    public JSONDatatable add_cs_meets_block(
            @RequestParam(name = "block_user_kpi_meet_id", required = false) Long block_user_kpi_meet_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_user_kpi_meet_id != null && block_user_kpi_meet_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITCsMeetsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICsMeetingsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_user_kpi_meet_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITCsMeetsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_cs_meets_block")
    public JSONDatatable del_cs_meets_block(
            @RequestParam(name = "block_user_kpi_meet_id", required = false) Long block_user_kpi_meet_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_user_kpi_meet_id != null && block_user_kpi_meet_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITCsMeetsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICsMeetingsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_user_kpi_meet_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITCsMeetsBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/add_crm_toplist_block")
    public JSONDatatable add_crm_toplist_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmToplistBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITendersBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_crm_toplist_block")
    public JSONDatatable del_crm_toplist_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICrmToplistBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITendersBlockQuery.execute();
        }
        return null;
    }

//Clients OutTime
    @PostMapping("/get_outtime_table")
    public JSONDatatable get_outtime_table(
            @RequestParam(name = "dep_kpi_general_param_id", required = false) Long dep_kpi_general_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_general_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsOutTimeRepository.queryUserKPICRMDepKPIFactsClientsOutTime(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/add_outtime_block")
    public JSONDatatable add_outtime_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIOuttimeBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmOuttimeBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIOuttimeBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_outtime_block")
    public JSONDatatable del_outtime_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIOuttimeBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIOuttimeBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIOuttimeBlockQuery.execute();
        }
        return null;
    }

    //Clients NotFill
    @PostMapping("/get_notfill_table")
    public JSONDatatable get_notfill_table(
            @RequestParam(name = "dep_kpi_general_param_id", required = false) Long dep_kpi_general_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_general_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsNotFillRepository.queryUserKPICRMDepKPIFactsClientsNotFill(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/add_notfill_block")
    public JSONDatatable add_notfill_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPINotfillBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmNotfillBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPINotfillBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_notfill_block")
    public JSONDatatable del_notfill_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPINotfillBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPINotfillBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPINotfillBlockQuery.execute();
        }
        return null;
    }

//Clients NotFill Pass
    @PostMapping("/get_notfill_pass_table")
    public JSONDatatable get_notfill_pass_table(
            @RequestParam(name = "dep_kpi_general_param_id", required = false) Long dep_kpi_general_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_general_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsNotFillPassRepository.queryUserKPICRMDepKPIFactsClientsNotFillPass(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/add_notfill_pass_block")
    public JSONDatatable add_notfill_pass_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmNotfillPassBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_notfill_pass_block")
    public JSONDatatable del_notfill_pass_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPINotfillPassBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

//Clients Lost
    @PostMapping("/get_lost_table")
    public JSONDatatable get_lost_table(
            @RequestParam(name = "dep_kpi_general_param_id", required = false) Long dep_kpi_general_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_general_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsLostRepository.queryUserKPICRMDepKPIFactsClientsLost(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Chief Clients Fin
    @PostMapping("/get_chief_clients_table")
    public JSONDatatable get_chief_clients_table(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefClientsFinRepository.queryMenuDepKPIFactChiefClientsFinByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/add_chief_clients_block")
    public JSONDatatable add_chief_clients_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_ser_id", required = false) Long block_ser_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIChiefClientsFinBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_ser_id)
                    .setParameter(5, block_user_id)
                    .setParameter(6, block_year)
                    .setParameter(7, block_month)
                    ;
            AddKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_chief_clients_block")
    public JSONDatatable del_chief_clients_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_ser_id", required = false) Long block_ser_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIChiefClientsFinBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_ser_id)
                    .setParameter(5, block_user_id)
                    .setParameter(6, block_year)
                    .setParameter(7, block_month)
                    ;
            DelKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

    //Chief Clients Fin
    @PostMapping("/get_chief_bonus_clients_table")
    public JSONDatatable get_chief_bonus_clients_table(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefBonusClientsFinRepository.queryMenuDepKPIFactChiefBonusClientsFinByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/add_chief_bonus_clients_block")
    public JSONDatatable add_chief_bonus_clients_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_ser_id", required = false) Long block_ser_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIChiefBonusClientsFinBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_ser_id)
                    .setParameter(5, block_user_id)
                    .setParameter(6, block_year)
                    .setParameter(7, block_month)
                    ;
            AddKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_chief_bonus_clients_block")
    public JSONDatatable del_chief_bonus_clients_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_ser_id", required = false) Long block_ser_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIChiefBonusClientsFinBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_ser_id)
                    .setParameter(5, block_user_id)
                    .setParameter(6, block_year)
                    .setParameter(7, block_month)
                    ;
            DelKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

    //Debtors
    @PostMapping("/debtors_menu")
    public JSONDatatable debtors_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactDebtorsRepository.queryMenuDepKPIFactDebtorsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }

    //Debtors
    @PostMapping("/chief_debtors_menu")
    public JSONDatatable chief_debtors_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefDebtorsRepository.queryMenuDepKPIFactChiefDebtorsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/get_clients_profit_menu")
    public JSONDatatable get_clients_profit_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactClientsProfitRepository.queryMenuDepKPIFactClientsProfitByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/profit_menu")
    public JSONDatatable profit_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null && dep_kpi_user_id != null && dep_kpi_cnt_id != null && dep_kpi_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactProfitRepository.queryMenuDepKPIFactProfitByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/get_clients_debtors_menu")
    public JSONDatatable get_clients_debtors_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactClientsDebtorsRepository.queryMenuDepKPIFactClientsDebtorsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/get_clients_unpaid_menu")
    public JSONDatatable get_clients_unpaid_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactClientsUnpaidRepository.queryMenuDepKPIFactClientsUnpaidByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/unpaid_menu")
    public JSONDatatable unpaid_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null && dep_kpi_user_id != null && dep_kpi_cnt_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactUnpaidRepository.queryMenuDepKPIFactUnpaidByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/get_chief_clients_profit_menu")
    public JSONDatatable get_chief_clients_profit_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefClientsProfitRepository.queryMenuDepKPIFactChiefClientsProfitByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/chief_profit_menu")
    public JSONDatatable chief_profit_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null && dep_kpi_user_id != null && dep_kpi_cnt_id != null && dep_kpi_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefProfitRepository.queryMenuDepKPIFactChiefProfitByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/get_chief_clients_unpaid_menu")
    public JSONDatatable get_chief_clients_unpaid_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefClientsUnpaidRepository.queryMenuDepKPIFactChiefClientsUnpaidByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/chief_unpaid_menu")
    public JSONDatatable chief_unpaid_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null && dep_kpi_user_id != null && dep_kpi_cnt_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefUnpaidRepository.queryMenuDepKPIFactChiefUnpaidByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }

    //Finance
    @PostMapping("/get_chief_clients_debtors_menu")
    public JSONDatatable get_chief_clients_debtors_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefClientsDebtorsRepository.queryMenuDepKPIFactChiefClientsDebtorsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    //Debtors
    @PostMapping("/chief_debtors_paid_menu")
    public JSONDatatable chief_debtors_paid_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefDebtorsPaidRepository.queryMenuDepKPIFactChiefDebtorsPaidByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }
//OPS Head
    //New Finance
    @PostMapping("/get_ops_head_new_clients_finance_menu")
    public JSONDatatable get_ops_head_new_clients_finance_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactOPSHeadNewClientFinanceRepository.queryMenuDepKPIFactOpsHeadNewCLientFinanceByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/get_ops_head_new_finance_menu")
    public JSONDatatable get_ops_head_new_finance_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactOPSHeadNewFinanceRepository.queryMenuDepKPIFactOPSHeadNEwFinanceByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }

//Old Finance
    @PostMapping("/get_ops_head_old_clients_finance_menu")
    public JSONDatatable get_ops_head_old_clients_finance_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactOPSHeadOldClientFinanceRepository.queryMenuDepKPIFactOpsHeadOldCLientFinanceByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month
            ));
        }
        return result;
    }

    @PostMapping("/get_ops_head_old_finance_menu")
    public JSONDatatable get_ops_head_old_finance_menu(
            @RequestParam(name = "dep_kpi_param_id", required = false) Long dep_kpi_param_id,
            @RequestParam(name = "dep_kpi_user_id", required = false) Long dep_kpi_user_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "dep_kpi_month", required = false) Long dep_kpi_month,
            @RequestParam(name = "dep_kpi_cnt_id", required = false) Long dep_kpi_cnt_id,
            @RequestParam(name = "dep_kpi_ser_id", required = false) Long dep_kpi_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_param_id != null && dep_kpi_year != null && dep_kpi_month != null &&  dep_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactOPSHeadOldFinanceRepository.queryMenuDepKPIFactOPSHeadOldFinanceByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_user_id, dep_kpi_year, dep_kpi_month, dep_kpi_cnt_id, dep_kpi_ser_id
            ));
        }
        return result;
    }
}