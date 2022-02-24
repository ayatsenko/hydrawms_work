package com.idltd.hydramob.controller.departments;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.departments.DepartmentUsersListRepository;
import com.idltd.hydramob.repository.departments.DetailDepartmentsListRepository;
import com.idltd.hydramob.repository.departments.MenuDepartmentUsersListRepository;
import com.idltd.hydramob.repository.departments.MenuDepartmentsListRepository;
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
@RequestMapping("/departments")
public class DeprtmentsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuDepartmentsListRepository menuDepartmentsListRepository;
    private DetailDepartmentsListRepository detailDepartmentsListRepository;

    private MenuDepartmentUsersListRepository menuDepartmentUsersListRepository;
    private DepartmentUsersListRepository departmentUsersListRepository;

    public DeprtmentsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuDepartmentsListRepository menuDepartmentsListRepository,
            DetailDepartmentsListRepository detailDepartmentsListRepository,

            MenuDepartmentUsersListRepository menuDepartmentUsersListRepository,
            DepartmentUsersListRepository departmentUsersListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuDepartmentsListRepository = menuDepartmentsListRepository;
        this.detailDepartmentsListRepository = detailDepartmentsListRepository;

        this.menuDepartmentUsersListRepository = menuDepartmentUsersListRepository;
        this.departmentUsersListRepository = departmentUsersListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "dep_id", required = false) Long dep_id

    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.setViewName("departments/cover");
        return model;
    }

    @PostMapping("/get_main_table")
    public JSONDatatable get_main_table(
            @RequestParam(value="dep_id", required = false) Long dep_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuDepartmentsListRepository.queryDepartmentsMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));

        return result;
    }

    @GetMapping("/get_department_detail")
    public JSONDatatable get_department_detail(
            @RequestParam(name="mode") Long mode,
            @RequestParam(name="dep_id", defaultValue = "0") Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_id != null && dep_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailDepartmentsListRepository.queryDepartmentsMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, dep_id));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/add_department")
    public ModelAndView add_department(
            @RequestParam(name = "dep_name", required = false) String dep_name,
            @RequestParam(name = "dep_sname", required = false) String dep_sname,
            @RequestParam(name = "dep_description", required = false) String dep_description,
            @RequestParam(name = "dep_colour", required = false) String dep_colour
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddDepartmentQuery = entityManager
                    .createStoredProcedureQuery("PKG_ADMIN.pr_AddDepartment")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_name)
                    .setParameter(4, dep_sname)
                    .setParameter(5, dep_description)
                    .setParameter(6, dep_colour)
                    ;
            AddDepartmentQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_department")
    public ModelAndView edit_department(
            @RequestParam(name = "dep_id") Long dep_id,
            @RequestParam(name = "dep_name", required = false) String dep_name,
            @RequestParam(name = "dep_sname", required = false) String dep_sname,
            @RequestParam(name = "dep_description", required = false) String dep_description,
            @RequestParam(name = "dep_colour", required = false) String dep_colour
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditDepartmentQuery = entityManager
                    .createStoredProcedureQuery("PKG_ADMIN.pr_EditDepartment")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    .setParameter(4, dep_name)
                    .setParameter(5, dep_sname)
                    .setParameter(6, dep_description)
                    .setParameter(7, dep_colour)
                    ;
            EditDepartmentQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_department")
    public ModelAndView del_department(
            @RequestParam(name = "dep_id") Long dep_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelDepartmentQuery = entityManager
                    .createStoredProcedureQuery("PKG_ADMIN.pr_DelDepartment")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    ;
            DelDepartmentQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_users_table")
    public JSONDatatable get_users_table(
            @RequestParam(value="dep_id", required = false) Long dep_id,
            @RequestParam(value="dep_user_link_id", required = false) Long dep_user_link_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dep_id != null && dep_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDepartmentUsersListRepository.queryDepartmentUsersMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, dep_id));
        }
        return result;
    }

    @GetMapping("/get_users_list")
    public JSONDatatable get_users_list(
            @RequestParam(name="mode") Long mode,
            @RequestParam(name="user_id") Long user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0) {
            result.setData(departmentUsersListRepository.queryDepartmentUsersListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        else{
            result.setData(departmentUsersListRepository.queryDepartmentUserOneByUserID(user_List.get(0).id, user_Role_List.get(0).role_id,user_id));
        }
        return result;
    }

    @PostMapping("/add_department_user")
    public ModelAndView add_department_user(
            @RequestParam(name = "dep_id") Long dep_id,
            @RequestParam(name = "user_id") Long user_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelDepartmentUserQuery = entityManager
                    .createStoredProcedureQuery("PKG_ADMIN.pr_AddDepartmentUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_id)
                    .setParameter(4, user_id)
                    ;
            DelDepartmentUserQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_department_user")
    public ModelAndView del_department_user(
            @RequestParam(name = "dep_user_link_id") Long dep_user_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelDepartmentUserQuery = entityManager
                    .createStoredProcedureQuery("PKG_ADMIN.pr_DelDepartmentUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, dep_user_link_id)
                    ;
            DelDepartmentUserQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
}
