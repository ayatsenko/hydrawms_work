package com.idltd.hydramob.controller.users_kpi_facts;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.Users_KPI_Type_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.dep_kpi_facts.*;
import com.idltd.hydramob.repository.users_kpi_facts.*;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users_kpi_facts")
public class UsersKPIFactsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuUserKPIFactsMainListRepository menuUserKPIFactsMainListRepository;
    private MenuUserKPIFactsCalcListRepository menuUserKPIFactsCalcListRepository;
    private MenuUserKPIFactsCRMListRepository menuUserKPIFactsCRMListRepository;
    private MenuUserKPIFactMeetsRepository menuUserKPIFactMeetsRepository;
    private MenuUserKPIFactCallsRepository menuUserKPIFactCallsRepository;
    private MenuUserKPIFactFinanceRepository menuUserKPIFactFinanceRepository;
    private MenuUserKPIFactRequestsRepository menuUserKPIFactRequestsRepository;
    private MenuUserKPIFactTendersRepository menuUserKPIFactTendersRepository;
    private MenuUserKPIFactCRMClientsRepository menuUserKPIFactCRMClientsRepository;
    private MenuUserKPIFactCRMToplistRepository menuUserKPIFactCRMToplistRepository;
    private MenuDepKPIFactCRMGeneralRepository menuDepKPIFactCRMGeneralRepository;
    private MenuUserKPIFactCRMTodoRepository menuUserKPIFactCRMTodoRepository;
    private MenuUserKPIFactCRMRequestsRepository menuUserKPIFactCRMRequestsRepository;
    private MenuUserKPIFactCRMTendersRepository menuUserKPIFactCRMTendersRepository;
    private MenuDepKPIFactsClientsOutTimeRepository menuDepKPIFactsClientsOutTimeRepository;
    private MenuDepKPIFactsClientsNotFillRepository menuDepKPIFactsClientsNotFillRepository;
    private MenuDepKPIFactsClientsNotFillPassRepository menuDepKPIFactsClientsNotFillPassRepository;
    private MenuDepKPIFactsClientsLostRepository menuDepKPIFactsClientsLostRepository;

    private MenuDepKPIFactCSMeetsListRepository menuDepKPIFactCSMeetsListRepository;
    private MenuDepKPIFactCSOPSMeetsListRepository menuDepKPIFactCSOPSMeetsListRepository;
    private MenuDepKPIFactChiefBonusClientsFinRepository menuDepKPIFactChiefBonusClientsFinRepository;
    private MenuDepKPIFactChiefClientsFinRepository menuDepKPIFactChiefClientsFinRepository;

    public UsersKPIFactsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuUserKPIFactsMainListRepository menuUserKPIFactsMainListRepository,
            MenuUserKPIFactsCalcListRepository menuUserKPIFactsCalcListRepository,
            MenuUserKPIFactsCRMListRepository menuUserKPIFactsCRMListRepository,
            MenuUserKPIFactMeetsRepository menuUserKPIFactMeetsRepository,
            MenuUserKPIFactCallsRepository menuUserKPIFactCallsRepository,
            MenuUserKPIFactFinanceRepository menuUserKPIFactFinanceRepository,
            MenuUserKPIFactRequestsRepository menuUserKPIFactRequestsRepository,
            MenuUserKPIFactTendersRepository menuUserKPIFactTendersRepository,
            MenuUserKPIFactCRMClientsRepository menuUserKPIFactCRMClientsRepository,
            MenuUserKPIFactCRMToplistRepository menuUserKPIFactCRMToplistRepository,
            MenuDepKPIFactCRMGeneralRepository menuDepKPIFactCRMGeneralRepository,
            MenuUserKPIFactCRMTodoRepository menuUserKPIFactCRMTodoRepository,
            MenuUserKPIFactCRMRequestsRepository menuUserKPIFactCRMRequestsRepository,
            MenuUserKPIFactCRMTendersRepository menuUserKPIFactCRMTendersRepository,
            MenuDepKPIFactsClientsOutTimeRepository menuDepKPIFactsClientsOutTimeRepository,
            MenuDepKPIFactsClientsNotFillRepository menuDepKPIFactsClientsNotFillRepository,
            MenuDepKPIFactsClientsNotFillPassRepository menuDepKPIFactsClientsNotFillPassRepository,
            MenuDepKPIFactsClientsLostRepository menuDepKPIFactsClientsLostRepository,
            MenuDepKPIFactCSMeetsListRepository menuDepKPIFactCSMeetsListRepository,
            MenuDepKPIFactCSOPSMeetsListRepository menuDepKPIFactCSOPSMeetsListRepository,
            MenuDepKPIFactChiefBonusClientsFinRepository menuDepKPIFactChiefBonusClientsFinRepository,
            MenuDepKPIFactChiefClientsFinRepository menuDepKPIFactChiefClientsFinRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuUserKPIFactsMainListRepository = menuUserKPIFactsMainListRepository;
        this.menuUserKPIFactsCalcListRepository = menuUserKPIFactsCalcListRepository;
        this.menuUserKPIFactsCRMListRepository = menuUserKPIFactsCRMListRepository;
        this.menuUserKPIFactMeetsRepository = menuUserKPIFactMeetsRepository;
        this.menuUserKPIFactCallsRepository = menuUserKPIFactCallsRepository;
        this.menuUserKPIFactFinanceRepository = menuUserKPIFactFinanceRepository;
        this.menuUserKPIFactRequestsRepository = menuUserKPIFactRequestsRepository;
        this.menuUserKPIFactTendersRepository = menuUserKPIFactTendersRepository;
        this.menuUserKPIFactCRMClientsRepository = menuUserKPIFactCRMClientsRepository;
        this.menuUserKPIFactCRMToplistRepository = menuUserKPIFactCRMToplistRepository;
        this.menuDepKPIFactCRMGeneralRepository = menuDepKPIFactCRMGeneralRepository;
        this.menuUserKPIFactCRMTodoRepository = menuUserKPIFactCRMTodoRepository;
        this.menuUserKPIFactCRMRequestsRepository = menuUserKPIFactCRMRequestsRepository;
        this.menuUserKPIFactCRMTendersRepository = menuUserKPIFactCRMTendersRepository;
        this.menuDepKPIFactsClientsOutTimeRepository = menuDepKPIFactsClientsOutTimeRepository;
        this.menuDepKPIFactsClientsNotFillRepository = menuDepKPIFactsClientsNotFillRepository;
        this.menuDepKPIFactsClientsNotFillPassRepository = menuDepKPIFactsClientsNotFillPassRepository;
        this.menuDepKPIFactsClientsLostRepository = menuDepKPIFactsClientsLostRepository;
        this.menuDepKPIFactCSMeetsListRepository = menuDepKPIFactCSMeetsListRepository;
        this.menuDepKPIFactCSOPSMeetsListRepository = menuDepKPIFactCSOPSMeetsListRepository;
        this.menuDepKPIFactChiefBonusClientsFinRepository = menuDepKPIFactChiefBonusClientsFinRepository;
        this.menuDepKPIFactChiefClientsFinRepository = menuDepKPIFactChiefClientsFinRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "users_id", required = false) Long users_id,
                              @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,

                              @RequestParam(name = "users_kpi_facts_list_table_order_column", required = false) Long users_kpi_facts_list_table_order_column,
                              @RequestParam(name = "users_kpi_facts_list_table_order_type", required = false) String users_kpi_facts_list_table_order_type,
                              @RequestParam(name = "users_kpi_facts_list_table_search", required = false) String users_kpi_facts_list_table_search,
                              @RequestParam(name = "users_kpi_facts_list_table_pagelen", required = false) Long users_kpi_facts_list_table_pagelen,
                              @RequestParam(name = "users_kpi_facts_list_table_page", required = false) Long users_kpi_facts_list_table_page
    ){
        List<Users_KPI_Type_List> users_KPI_Type_List;

        model.addObject("users_id", users_id);

        model.addObject("users_kpi_year", users_kpi_year);

        model.addObject("users_kpi_facts_list_table_order_column", users_kpi_facts_list_table_order_column);
        model.addObject("users_kpi_facts_list_table_order_type", users_kpi_facts_list_table_order_type);
        model.addObject("users_kpi_facts_list_table_search", users_kpi_facts_list_table_search);
        model.addObject("users_kpi_facts_list_table_pagelen", users_kpi_facts_list_table_pagelen);
        model.addObject("users_kpi_facts_list_table_page", users_kpi_facts_list_table_page);

        model.setViewName("users_kpi_facts/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "users_kpi_facts_year", required = false) Long users_kpi_facts_year
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(users_kpi_facts_year != null) {
            result.setData(menuUserKPIFactsMainListRepository.queryUserKPIFactsMainListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_facts_year
            ));
        }
        return result;
    }

    @PostMapping("/get_calc_table")
    public JSONDatatable get_calc_table(
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_facts_year", required = false) Long users_kpi_facts_year,
            @RequestParam(name = "users_kpi_facts_month", required = false) Long users_kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_user_id != null && users_kpi_facts_year != null && users_kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactsCalcListRepository.queryUserKPICalcMainListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_facts_year, users_kpi_facts_month
            ));
        }
        return result;
    }

    @PostMapping("/get_crm_table")
    public JSONDatatable get_crm_table(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_facts_year", required = false) Long users_kpi_facts_year,
            @RequestParam(name = "users_kpi_facts_month", required = false) Long users_kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if( users_kpi_param_id != null && users_kpi_user_id != null && users_kpi_facts_year != null && users_kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(users_kpi_param_id == 5) {
                result.setData(menuUserKPIFactsCRMListRepository.queryUserKPICRMMainListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_facts_year, users_kpi_facts_month
                ));
            }
            else if(users_kpi_param_id == 13) {
                result.setData(menuUserKPIFactsCRMListRepository.queryUserKPICRMChiefMainListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_facts_year, users_kpi_facts_month
                ));
            }
        }
        return result;
    }

//Meetings
    @PostMapping("/meets_menu")
    public JSONDatatable meets_menu(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactMeetsRepository.queryMenuUserKPIFactMeetsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

//Calls
    @PostMapping("/calls_menu")
    public JSONDatatable calls_menu(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactCallsRepository.queryMenuUserKPIFactCallsByID(user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month));
        }
        return result;
    }

//Finance
    @PostMapping("/finance_menu")
    public JSONDatatable finance_menu(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactFinanceRepository.queryMenuUserKPIFactFinanceByID(user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month));
        }
        return result;
    }
//Requests
    @PostMapping("/requests_menu")
    public JSONDatatable requests_menu(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactRequestsRepository.queryMenuUserKPIFactRequestsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }
//Tenders
    @PostMapping("/tenders_menu")
    public JSONDatatable tenders_menu(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactTendersRepository.queryMenuUserKPIFactTendersByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

//CRM Clients List
    @PostMapping("/crm_clients_menu")
    public JSONDatatable crm_clients_menu(
            @RequestParam(name = "users_kpi_crm_param_id", required = false) Long users_kpi_crm_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_crm_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactCRMClientsRepository.queryMenuUserKPIFactCRMClientsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

//CRM Toplist List
    @PostMapping("/crm_toplist_menu")
    public JSONDatatable crm_toplist_menu(
            @RequestParam(name = "users_kpi_crm_param_id", required = false) Long users_kpi_crm_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_crm_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactCRMToplistRepository.queryMenuUserKPIFactCRMToplistByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //CRM TodoMenu
    @PostMapping("/crm_todo_menu")
    public JSONDatatable crm_todo_menu(
            @RequestParam(name = "users_kpi_crm_param_id", required = false) Long users_kpi_crm_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_crm_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactCRMTodoRepository.queryMenuUserKPIFactCRMTodoByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }
//CRM Requests
    @PostMapping("/crm_requests_menu")
    public JSONDatatable crm_requests_menu(
            @RequestParam(name = "users_kpi_crm_param_id", required = false) Long users_kpi_crm_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_crm_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactCRMRequestsRepository.queryMenuUserKPIFactCRMRequestsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //Tenders
    @PostMapping("/crm_tenders_menu")
    public JSONDatatable crm_tenders_menu(
            @RequestParam(name = "users_kpi_crm_param_id", required = false) Long users_kpi_crm_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_crm_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFactCRMTendersRepository.queryMenuUserKPIFactCRMTendersByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //CRM General List
    @PostMapping("/crm_general_menu")
    public JSONDatatable crm_general_menu(
            @RequestParam(name = "users_kpi_crm_param_id", required = false) Long users_kpi_crm_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_crm_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCRMGeneralRepository.queryMenuDepKPIFactCRMGeneralByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //Clients OutTime
    @PostMapping("/get_outtime_table")
    public JSONDatatable get_outtime_table(
            @RequestParam(name = "users_kpi_general_param_id", required = false) Long users_kpi_general_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_general_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsOutTimeRepository.queryUserKPICRMDepKPIFactsClientsOutTime(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //Clients NotFill
    @PostMapping("/get_notfill_table")
    public JSONDatatable get_notfill_table(
            @RequestParam(name = "users_kpi_general_param_id", required = false) Long users_kpi_general_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_general_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsNotFillRepository.queryUserKPICRMDepKPIFactsClientsNotFill(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //Clients NotFill Pass
    @PostMapping("/get_notfill_pass_table")
    public JSONDatatable get_notfill_pass_table(
            @RequestParam(name = "users_kpi_general_param_id", required = false) Long users_kpi_general_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_general_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsNotFillPassRepository.queryUserKPICRMDepKPIFactsClientsNotFillPass(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }
    //Clients Lost
    @PostMapping("/get_lost_table")
    public JSONDatatable get_lost_table(
            @RequestParam(name = "users_kpi_general_param_id", required = false) Long users_kpi_general_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_general_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactsClientsLostRepository.queryUserKPICRMDepKPIFactsClientsLost(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //Chief Clients Fin
    @PostMapping("/get_chief_clients_table")
    public JSONDatatable get_chief_clients_table(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefClientsFinRepository.queryMenuDepKPIFactChiefClientsFinByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //Chief Clients Fin
    @PostMapping("/get_chief_bonus_clients_table")
    public JSONDatatable get_chief_bonus_clients_table(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null &&  users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactChiefBonusClientsFinRepository.queryMenuDepKPIFactChiefBonusClientsFinByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //CSOPS Meetings
    @PostMapping("/csops_meetings_menu")
    public JSONDatatable csops_meetings_menu(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null && users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCSOPSMeetsListRepository.queryMenuDepKPIFactCSOPSMeetsListByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }

    //CS Meetings
    @PostMapping("/cs_meetings_menu")
    public JSONDatatable cs_meetings_menu(
            @RequestParam(name = "users_kpi_param_id", required = false) Long users_kpi_param_id,
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_param_id != null && users_kpi_year != null && users_kpi_month != null && users_kpi_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIFactCSMeetsListRepository.queryMenuDepKPIFactCsMeetingsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_year, users_kpi_month
            ));
        }
        return result;
    }


}
