package com.idltd.hydramob.controller.users_kpi;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.users_kpi.DetailUserKPIList;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.users_kpi.*;
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
@RequestMapping("/users_kpi")
public class UsersKPIController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuUserKPIListRepository menuUserKPIListRepository;
    private DetailUserKPIListRepository detailUserKPIListRepository;

    private MenuUserKPIMeetsRepository menuUserKPIMeetsRepository;
    private MenuUserKPICallsRepository menuUserKPICallsRepository;
    private MenuUserKPIRequestsRepository menuUserKPIRequestsRepository;
    private MenuUserKPITendersRepository menuUserKPITendersRepository;
    private MenuUserKPIFinanceRepository menuUserKPIFinanceRepository;

    public UsersKPIController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuUserKPIListRepository menuUserKPIListRepository,
            DetailUserKPIListRepository detailUserKPIListRepository,

            MenuUserKPIMeetsRepository menuUserKPIMeetsRepository,
            MenuUserKPICallsRepository menuUserKPICallsRepository,
            MenuUserKPIRequestsRepository menuUserKPIRequestsRepository,
            MenuUserKPITendersRepository menuUserKPITendersRepository,
            MenuUserKPIFinanceRepository menuUserKPIFinanceRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuUserKPIListRepository = menuUserKPIListRepository;
        this.detailUserKPIListRepository = detailUserKPIListRepository;

        this.menuUserKPIMeetsRepository = menuUserKPIMeetsRepository;
        this.menuUserKPICallsRepository = menuUserKPICallsRepository;
        this.menuUserKPIRequestsRepository = menuUserKPIRequestsRepository;
        this.menuUserKPITendersRepository = menuUserKPITendersRepository;
        this.menuUserKPIFinanceRepository = menuUserKPIFinanceRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "user_id", required = false) Long user_id,
                              @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
                              @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month,

                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page

    ){
        model.addObject("user_id", user_id);
        model.addObject("users_kpi_year", users_kpi_year);
        model.addObject("users_kpi_month", users_kpi_month);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("users_kpi/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(users_kpi_year != null && users_kpi_month != null) {
            result.setData(menuUserKPIListRepository.queryUserKPIListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_year, users_kpi_month));
        }
        return result;
    }

    @RequestMapping("/list_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="user_id") Long cur_user_id,
                                   @RequestParam(value="users_kpi_year") Long year,
                                   @RequestParam(value="users_kpi_month") Long month,

                                   @RequestParam(value="users_kpi_list_table_order_column") Long order_column,
                                   @RequestParam(value="users_kpi_list_table_order_type") String order_type,
                                   @RequestParam(value="users_kpi_list_table_search") String table_search,
                                   @RequestParam(value="users_kpi_list_table_pagelen") Long pagelen,
                                   @RequestParam(value="users_kpi_list_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<DetailUserKPIList> detailUserKPIList;
        List<DetailUserKPIList>  detailDelUserKPIList;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){

        }
        else if(mode == 1) {
            detailUserKPIList = detailUserKPIListRepository .queryUserKPIDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id, year, month);

            mav.addObject("user_id", detailUserKPIList.get(0).user_id);
            mav.addObject("user_surname", detailUserKPIList.get(0).user_surname);

            mav.addObject("users_kpi_year", year);
            mav.addObject("users_kpi_month", month);

            mav.addObject("meets_plan", detailUserKPIList.get(0).meets_plan);
            mav.addObject("calls_plan", detailUserKPIList.get(0).calls_plan);
            mav.addObject("req_plan", detailUserKPIList.get(0).req_plan);
            mav.addObject("fin_plan", detailUserKPIList.get(0).fin_plan);
        }
        else{
            detailDelUserKPIList = detailUserKPIListRepository .queryUserKPIDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id, year, month);

            mav.addObject("user_id", detailDelUserKPIList.get(0).user_id);
            mav.addObject("user_surname", detailDelUserKPIList.get(0).user_surname);

            mav.addObject("users_kpi_year", year);
            mav.addObject("users_kpi_month", month);

            mav.addObject("meets_plan", detailDelUserKPIList.get(0).meets_plan);
            mav.addObject("calls_plan", detailDelUserKPIList.get(0).calls_plan);
            mav.addObject("req_plan", detailDelUserKPIList.get(0).req_plan);
            mav.addObject("fin_plan", detailDelUserKPIList.get(0).fin_plan);
        }

        mav.addObject("users_kpi_list_table_order_column", order_column);
        mav.addObject("users_kpi_list_table_order_type", order_type);
        mav.addObject("users_kpi_list_table_search", table_search);
        mav.addObject("users_kpi_list_table_pagelen", pagelen);
        mav.addObject("users_kpi_list_table_page", page);

        mav.setViewName("/users_kpi/list_detail");
        return mav;
    }
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/list_model")
    public ModelAndView list_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id") Long cur_user_id,
            @RequestParam(value="user_surname") String user_surname,

            @RequestParam(value="users_kpi_year") Long users_kpi_year,
            @RequestParam(value="users_kpi_month") Long users_kpi_month,

            @RequestParam(value="meets_plan") Long meets_plan,
            @RequestParam(value="calls_plan") Long calls_plan,
            @RequestParam(value="req_plan") Long req_plan,
            @RequestParam(value="fin_plan") Long fin_plan,

            @RequestParam(value="user_kpi_list_detail_table_order_column", required = false) Long order_column,
            @RequestParam(value="user_kpi_list_detail_table_order_type", required = false) String order_type,
            @RequestParam(value="user_kpi_list_detail_table_search", required = false) String table_search,
            @RequestParam(value="user_kpi_list_detail_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="user_kpi_list_detail_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:

                    break;
                case 1:
                    StoredProcedureQuery EditUserKPIQuery = entityManager
                            .createStoredProcedureQuery("PKG_KPI.pr_EditUserKPI")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cur_user_id)
                            .setParameter(4, users_kpi_year)
                            .setParameter(5, users_kpi_month)
                            .setParameter(6, meets_plan)
                            .setParameter(7, calls_plan)
                            .setParameter(8, req_plan)
                            .setParameter(9, fin_plan);
                    EditUserKPIQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelUserKPIQuery = entityManager
                            .createStoredProcedureQuery("PKG_KPI.pr_DelUserKPI")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cur_user_id)
                            .setParameter(4, users_kpi_year)
                            .setParameter(5, users_kpi_month);
                    DelUserKPIQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("user_id", cur_user_id);
        mav.addObject("user_surname", user_surname);

        mav.addObject("users_kpi_year", users_kpi_year);
        mav.addObject("users_kpi_month", users_kpi_month);

        mav.addObject("meets_plan", meets_plan);
        mav.addObject("calls_plan", calls_plan);
        mav.addObject("req_plan", req_plan);
        mav.addObject("fin_plan", fin_plan);

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("user_kpi_list_detail_table_order_column", order_column);
        mav.addObject("user_kpi_list_detail_table_order_type", order_type);
        mav.addObject("user_kpi_list_detail_table_search", table_search);
        mav.addObject("user_kpi_list_detail_table_pagelen", pagelen);
        mav.addObject("user_kpi_list_detail_table_page", page);

        mav.setViewName("/users_kpi/list_detail");
        return mav;
    }

//Meetings
@PostMapping("/meets_menu")
public JSONDatatable meets_menu(
        @RequestParam(name = "user_id", required = false) Long cur_user_id,
        @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
        @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
) {
    JSONDatatable result = new JSONDatatable();
    List<User_List> user_List;
    List<User_Role_List> user_Role_List;

    if(users_kpi_year != null && users_kpi_month != null &&  cur_user_id != null) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuUserKPIMeetsRepository.queryMenuUserKPIMeetsByID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id, users_kpi_year, users_kpi_month));
    }
    return result;
}

//Calls
    @PostMapping("/calls_menu")
    public JSONDatatable calls_menu(
            @RequestParam(name = "user_id", required = false) Long cur_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_year != null && users_kpi_month != null &&  cur_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPICallsRepository.queryMenuUserKPICallsByID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id, users_kpi_year, users_kpi_month));
        }
        return result;
    }

//Requests
    @PostMapping("/requests_menu")
    public JSONDatatable requests_menu(
            @RequestParam(name = "user_id", required = false) Long cur_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_year != null && users_kpi_month != null &&  cur_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIRequestsRepository.queryMenuUserKPIRequestsByID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id, users_kpi_year, users_kpi_month));
        }
        return result;
    }

//Tenders
    @PostMapping("/tenders_menu")
    public JSONDatatable tenders_menu(
            @RequestParam(name = "user_id", required = false) Long cur_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_year != null && users_kpi_month != null &&  cur_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPITendersRepository.queryMenuUserKPITendersByID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id, users_kpi_year, users_kpi_month));
        }
        return result;
    }


//Finance
    @PostMapping("/finance_menu")
    public JSONDatatable finance_menu(
            @RequestParam(name = "user_id", required = false) Long cur_user_id,
            @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
            @RequestParam(name = "users_kpi_month", required = false) Long users_kpi_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_year != null && users_kpi_month != null &&  cur_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIFinanceRepository.queryMenuUserKPIFinanceByID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id, users_kpi_year, users_kpi_month));
        }
        return result;
    }

}
