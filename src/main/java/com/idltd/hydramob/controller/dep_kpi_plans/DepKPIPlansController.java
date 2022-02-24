package com.idltd.hydramob.controller.dep_kpi_plans;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.dep_kpi_plans.DepartmentParamList;
import com.idltd.hydramob.entity.list.DepartmentList;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.dep_kpi_plans.*;
import com.idltd.hydramob.repository.list.DepartmentListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/dep_kpi_plans")
public class DepKPIPlansController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private DepartmentListRepository departmentListRepository;
    private DepartmentParamListRepository departmentParamListRepository;

    private MenuDepKPIPlansMainListRepository menuDepKPIPlansMainListRepository;
    private DepartmentUserListRepository departmentUserListRepository;
    private DepartmentUserParamListRepository departmentUserParamListRepository;
    private MenuDepKPIPlansUsersListRepository menuDepKPIPlansUsersListRepository;
    private MenuDepKPIPlansClientsListRepository menuDepKPIPlansClientsListRepository;
    private DepatrtmentNewClientListListRepository depatrtmentNewClientListListRepository;
    public DepKPIPlansController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            DepartmentListRepository departmentListRepository,
            DepartmentParamListRepository departmentParamListRepository,
            MenuDepKPIPlansMainListRepository menuDepKPIPlansMainListRepository,
            DepartmentUserListRepository departmentUserListRepository,
            DepartmentUserParamListRepository departmentUserParamListRepository,
            MenuDepKPIPlansUsersListRepository menuDepKPIPlansUsersListRepository,
            MenuDepKPIPlansClientsListRepository menuDepKPIPlansClientsListRepository,
            DepatrtmentNewClientListListRepository depatrtmentNewClientListListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.departmentListRepository = departmentListRepository;
        this.departmentParamListRepository = departmentParamListRepository;

        this.menuDepKPIPlansMainListRepository = menuDepKPIPlansMainListRepository;
        this.departmentUserListRepository = departmentUserListRepository;
        this.departmentUserParamListRepository = departmentUserParamListRepository;
        this.menuDepKPIPlansUsersListRepository = menuDepKPIPlansUsersListRepository;
        this.menuDepKPIPlansClientsListRepository = menuDepKPIPlansClientsListRepository;
        this.depatrtmentNewClientListListRepository = depatrtmentNewClientListListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year

    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        List<DepartmentList> department_list;
        List<DepartmentParamList> department_param_list;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.addObject("users_kpi_year", users_kpi_year);

        department_list = departmentListRepository.queryGetAllDep(user_List.get(0).id, user_Role_List.get(0).role_id);
        model.addObject("department_list", department_list);

        model.setViewName("dep_kpi_plans/cover");
        return model;
    }

    @GetMapping("/get_dep_param_list")
    public JSONDatatable get_dep_param_list(
            @RequestParam(value="dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(departmentParamListRepository.queryGetAllDepParam(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_id
            ));
        }
        return result;
    }

    @PostMapping("/get_main_table")
    public JSONDatatable get_main_table(
            @RequestParam(value="dep_id", required = false) Long dep_id,
            @RequestParam(value="dep_kpi_plans_year", required = false) Long dep_kpi_plans_year,
            @RequestParam(value="dep_kpi_type_id", required = false) Long dep_kpi_type_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_id != null && dep_kpi_plans_year != null && dep_kpi_type_id != null && dep_id >= 0 && dep_kpi_plans_year >= 0 && dep_kpi_type_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIPlansMainListRepository.queryUserKPIPlansMainMenuByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_id, dep_kpi_plans_year, dep_kpi_type_id
            ));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/dep_kpi_plans_edit")
    public JSONDatatable dep_kpi_plans_edit(
            @RequestParam(name = "dep_id", required = false) Long dep_id,
            @RequestParam(name = "dep_kpi_plans_year", required = false) Long dep_kpi_plans_year,
            @RequestParam(name = "dep_kpi_type_id", required = false) Long dep_kpi_type_id,
            @RequestParam(name = "month_1", required = false) String month_1,
            @RequestParam(name = "month_2", required = false) String month_2,
            @RequestParam(name = "month_3", required = false) String month_3,
            @RequestParam(name = "month_4", required = false) String month_4,
            @RequestParam(name = "month_5", required = false) String month_5,
            @RequestParam(name = "month_6", required = false) String month_6,
            @RequestParam(name = "month_7", required = false) String month_7,
            @RequestParam(name = "month_8", required = false) String month_8,
            @RequestParam(name = "month_9", required = false) String month_9,
            @RequestParam(name = "month_10", required = false) String month_10,
            @RequestParam(name = "month_11", required = false) String month_11,
            @RequestParam(name = "month_12", required = false) String month_12
    ) {
        if(dep_id != null && dep_kpi_plans_year != null && dep_kpi_type_id != null && dep_id > 0 && dep_kpi_plans_year > 0 && dep_kpi_type_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditUserKPIPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_EditDEP_KPI_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    .setParameter(4, dep_kpi_plans_year)
                    .setParameter(5, dep_kpi_type_id)
                    .setParameter(6, month_1)
                    .setParameter(7, month_2)
                    .setParameter(8, month_3)
                    .setParameter(9, month_4)
                    .setParameter(10, month_5)
                    .setParameter(11, month_6)
                    .setParameter(12, month_7)
                    .setParameter(13, month_8)
                    .setParameter(14, month_9)
                    .setParameter(15, month_10)
                    .setParameter(16, month_11)
                    .setParameter(17, month_12)
                    ;
            EditUserKPIPlanQuery.execute();
        }
        return null;
    }

    @PostMapping("/dep_kpi_plans_clear")
    public JSONDatatable dep_kpi_plans_clear(
            @RequestParam(name = "dep_id", required = false) Long dep_id,
            @RequestParam(name = "dep_kpi_plans_year", required = false) Long dep_kpi_plans_year,
            @RequestParam(name = "dep_kpi_type_id", required = false) Long dep_kpi_type_id
    ) {
        if(dep_id != null && dep_kpi_plans_year != null && dep_kpi_type_id != null && dep_id > 0 && dep_kpi_plans_year > 0 && dep_kpi_type_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditUserKPIPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_ClearDEP_KPI_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    .setParameter(4, dep_kpi_plans_year)
                    .setParameter(5, dep_kpi_type_id)
                    ;
            EditUserKPIPlanQuery.execute();
        }
        return null;
    }

    @GetMapping("/get_dep_users")
    public JSONDatatable get_dep_users(
            @RequestParam(value="dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(departmentUserListRepository.queryGetAllDepUsers(user_List.get(0).id, user_Role_List.get(0).role_id, dep_id));
        }
        return result;
    }

    @GetMapping("/get_dep_users_param")
    public JSONDatatable get_dep_users_param(
            @RequestParam(value="dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(departmentUserParamListRepository.queryGetAllDepUserParams(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_id
            ));
        }
        return result;
    }

    @PostMapping("/get_users_table")
    public JSONDatatable get_users_table(
            @RequestParam(name = "users_kpi_plans_year", required = false) Long users_kpi_plans_year,
            @RequestParam(name = "user_kpi_type_id", required = false) Long user_kpi_type_id,
            @RequestParam(name = "dep_user_id", required = false) Long dep_user_id,
            @RequestParam(name = "dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(users_kpi_plans_year != null) {
            result.setData(menuDepKPIPlansUsersListRepository.queryUserKPIPlansListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_plans_year, user_kpi_type_id, dep_user_id, dep_id
            ));
        }
        return result;
    }

    @PostMapping("/users_kpi_plans_edit")
    public JSONDatatable users_kpi_plans_edit(
            @RequestParam(name = "user_kpi_year", required = false) Long user_kpi_year,
            @RequestParam(name = "user_kpi_type_id", required = false) Long user_kpi_type_id,
            @RequestParam(name = "user_kpi_id", required = false) Long user_kpi_id,
            @RequestParam(name = "month_1", required = false) String month_1,
            @RequestParam(name = "month_2", required = false) String month_2,
            @RequestParam(name = "month_3", required = false) String month_3,
            @RequestParam(name = "month_4", required = false) String month_4,
            @RequestParam(name = "month_5", required = false) String month_5,
            @RequestParam(name = "month_6", required = false) String month_6,
            @RequestParam(name = "month_7", required = false) String month_7,
            @RequestParam(name = "month_8", required = false) String month_8,
            @RequestParam(name = "month_9", required = false) String month_9,
            @RequestParam(name = "month_10", required = false) String month_10,
            @RequestParam(name = "month_11", required = false) String month_11,
            @RequestParam(name = "month_12", required = false) String month_12
    ) {
        if(user_kpi_year != null && user_kpi_year > 0 && user_kpi_type_id != null && user_kpi_type_id > 0 && user_kpi_id != null && user_kpi_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditUserKPIPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_EditUSER_KPI_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_year)
                    .setParameter(4, user_kpi_type_id)
                    .setParameter(5, user_kpi_id)
                    .setParameter(6, month_1)
                    .setParameter(7, month_2)
                    .setParameter(8, month_3)
                    .setParameter(9, month_4)
                    .setParameter(10, month_5)
                    .setParameter(11, month_6)
                    .setParameter(12, month_7)
                    .setParameter(13, month_8)
                    .setParameter(14, month_9)
                    .setParameter(15, month_10)
                    .setParameter(16, month_11)
                    .setParameter(17, month_12)
                    ;
            EditUserKPIPlanQuery.execute();
        }
        return null;
    }

    @PostMapping("/users_kpi_plans_del")
    public JSONDatatable users_kpi_plans_del(
            @RequestParam(name = "user_kpi_year", required = false) Long user_kpi_year,
            @RequestParam(name = "user_kpi_type_id", required = false) Long user_kpi_type_id,
            @RequestParam(name = "user_kpi_id", required = false) Long user_kpi_id
    ) {
        if(user_kpi_year != null && user_kpi_year > 0 && user_kpi_type_id != null && user_kpi_type_id > 0 && user_kpi_id != null && user_kpi_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditUserKPIPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelUSER_KPI_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_year)
                    .setParameter(4, user_kpi_type_id)
                    .setParameter(5, user_kpi_id)
                    ;
            EditUserKPIPlanQuery.execute();
        }
        return null;
    }

    @PostMapping("/get_clients_table")
    public JSONDatatable get_clients_table(
            @RequestParam(name = "dep_kpi_type_year", required = false) Long dep_kpi_type_year,
            @RequestParam(name = "dep_kpi_type_id", required = false) Long dep_kpi_type_id,
            @RequestParam(name = "dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_kpi_type_year != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepKPIPlansClientsListRepository.queryUserKPIPlansClientsListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_type_year, dep_kpi_type_id, dep_id
            ));
        }
        return result;
    }

    @GetMapping("/get_client_list")
    public JSONDatatable get_client_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="dep_kpi_type_year") Long dep_kpi_type_year,
            @RequestParam(value="dep_id") Long dep_id,
            @RequestParam(value="cnt_filter") String cnt_filter
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(depatrtmentNewClientListListRepository.queryDepNewClientFilteredByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, dep_kpi_type_year, dep_id, cnt_filter));
        return result;
    }

    @PostMapping("/add_kpi_clients_plans")
    public JSONDatatable add_kpi_clients_plans(
            @RequestParam(name = "dep_id", required = false) Long dep_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "month_1", required = false) String month_1,
            @RequestParam(name = "month_2", required = false) String month_2,
            @RequestParam(name = "month_3", required = false) String month_3,
            @RequestParam(name = "month_4", required = false) String month_4,
            @RequestParam(name = "month_5", required = false) String month_5,
            @RequestParam(name = "month_6", required = false) String month_6,
            @RequestParam(name = "month_7", required = false) String month_7,
            @RequestParam(name = "month_8", required = false) String month_8,
            @RequestParam(name = "month_9", required = false) String month_9,
            @RequestParam(name = "month_10", required = false) String month_10,
            @RequestParam(name = "month_11", required = false) String month_11,
            @RequestParam(name = "month_12", required = false) String month_12
    ) {
        if(dep_id != null && dep_id > 0 && dep_kpi_year != null && dep_kpi_year > 0 && cnt_id != null && cnt_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIClientsPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEP_KPI_CNT_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    .setParameter(4, dep_kpi_year)
                    .setParameter(5, cnt_id)
                    .setParameter(6, month_1)
                    .setParameter(7, month_2)
                    .setParameter(8, month_3)
                    .setParameter(9, month_4)
                    .setParameter(10, month_5)
                    .setParameter(11, month_6)
                    .setParameter(12, month_7)
                    .setParameter(13, month_8)
                    .setParameter(14, month_9)
                    .setParameter(15, month_10)
                    .setParameter(16, month_11)
                    .setParameter(17, month_12)
                    ;
            AddKPIClientsPlanQuery.execute();
        }
        return null;
    }

    @PostMapping("/edit_kpi_clients_plans")
    public JSONDatatable edit_kpi_clients_plans(
            @RequestParam(name = "dep_id", required = false) Long dep_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "month_1", required = false) String month_1,
            @RequestParam(name = "month_2", required = false) String month_2,
            @RequestParam(name = "month_3", required = false) String month_3,
            @RequestParam(name = "month_4", required = false) String month_4,
            @RequestParam(name = "month_5", required = false) String month_5,
            @RequestParam(name = "month_6", required = false) String month_6,
            @RequestParam(name = "month_7", required = false) String month_7,
            @RequestParam(name = "month_8", required = false) String month_8,
            @RequestParam(name = "month_9", required = false) String month_9,
            @RequestParam(name = "month_10", required = false) String month_10,
            @RequestParam(name = "month_11", required = false) String month_11,
            @RequestParam(name = "month_12", required = false) String month_12
    ) {
        if(dep_id != null && dep_id > 0 && dep_kpi_year != null && dep_kpi_year > 0 && cnt_id != null && cnt_id >= 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditKPIClientsPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_EditDEP_KPI_CNT_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    .setParameter(4, dep_kpi_year)
                    .setParameter(5, cnt_id)
                    .setParameter(6, month_1)
                    .setParameter(7, month_2)
                    .setParameter(8, month_3)
                    .setParameter(9, month_4)
                    .setParameter(10, month_5)
                    .setParameter(11, month_6)
                    .setParameter(12, month_7)
                    .setParameter(13, month_8)
                    .setParameter(14, month_9)
                    .setParameter(15, month_10)
                    .setParameter(16, month_11)
                    .setParameter(17, month_12)
                    ;
            EditKPIClientsPlanQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_kpi_clients_plans")
    public JSONDatatable del_kpi_clients_plans(
            @RequestParam(name = "dep_id", required = false) Long dep_id,
            @RequestParam(name = "dep_kpi_year", required = false) Long dep_kpi_year,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        if(dep_id != null && dep_id > 0 && dep_kpi_year != null && dep_kpi_year > 0 && cnt_id != null && cnt_id >= 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIClientsPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEP_KPI_CNT_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    .setParameter(4, dep_kpi_year)
                    .setParameter(5, cnt_id)
                    ;
            DelKPIClientsPlanQuery.execute();
        }
        return null;
    }
}
