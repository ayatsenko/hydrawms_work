package com.idltd.hydramob.controller.user_settings;

import com.idltd.hydramob.entity.User;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.user_settings.DetailUserSettings;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.UsersRepository;
import com.idltd.hydramob.repository.user_settings.DetailUserSettingsRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import com.idltd.hydramob.utils.JSONWraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/user_settings")
public class UserSettingsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private DetailUserSettingsRepository detailUserSettingsRepository;
    private final UsersRepository usersRepository;

    public UserSettingsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            DetailUserSettingsRepository detailUserSettingsRepository,
            UsersRepository usersRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.detailUserSettingsRepository = detailUserSettingsRepository;
        this.usersRepository = usersRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model
    ){
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailUserSettings> detailUserSettings;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.setViewName("user_settings/detail");
        return model;
    }

    @GetMapping("/get_user_detail")
    public JSONDatatable get_user_detail(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(detailUserSettingsRepository.queryUserSettingsDetailByID(user_List.get(0).id, user_Role_List.get(0).role_id, user_List.get(0).username));
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/edit_user")
    public ModelAndView pass_model(
            @RequestParam(value="user_id") Long cur_user_id,
            @RequestParam(value="user_surname") String user_surname,
            @RequestParam(value="user_email") String user_email,
            @RequestParam(value="user_mainphone") String user_mainphone,
            @RequestParam(value="user_nonmainphone") String user_nonmainphone,
            @RequestParam(value="telegram_chatid", defaultValue = "0") Long telegram_chatid
    ) {

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditUserDetailQuery = entityManager
                .createStoredProcedureQuery("PKG_USERS.pr_EditUserDetailRole")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cur_user_id)
                .setParameter(4, user_surname)
                .setParameter(5, user_email)
                .setParameter(6, user_mainphone)
                .setParameter(7, user_nonmainphone)
                .setParameter(8, telegram_chatid);
        EditUserDetailQuery.execute();
        return null;
    }

    @RequestMapping("/pass_detail")
    public ModelAndView detailview(
                                   @RequestParam(value="user_id") Long user_id,
                                   @RequestParam(value="user_name") String user_name,
                                   @RequestParam(value="user_surname") String user_surname,

                                   @RequestParam(value="pos_name", required = false) String pos_name,
                                   @RequestParam(value="role_name", required = false) String role_name,

                                   @RequestParam(value="user_email", required = false) String user_email,
                                   @RequestParam(value="user_mainphone", required = false) String user_mainphone,
                                   @RequestParam(value="user_nonmainphone", required = false) String user_nonmainphone,
                                   @RequestParam(value="telegram_chatid", required = false) Long telegram_chatid
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("user_id", user_id);
        mav.addObject("user_name", user_name);
        mav.addObject("user_surname", user_surname);

        mav.addObject("pos_name", pos_name);
        mav.addObject("role_name", role_name);

        mav.addObject("user_email", user_email);
        mav.addObject("user_mainphone", user_mainphone);
        mav.addObject("user_nonmainphone", user_nonmainphone);
        mav.addObject("telegram_chatid", telegram_chatid);

        mav.setViewName("/user_settings/pass_detail");
        return mav;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/pass_model")
    public ModelAndView pass_model(
            @RequestParam(value="user_id") Long user_id,
            @RequestParam(value="user_name") String user_name,
            @RequestParam(value="user_surname") String user_surname,
            @RequestParam(value="user_password") String password
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            JSONWraper result = new JSONWraper();
            User u=usersRepository.queryCurrUserByID(user_id);
            u.setPassword(bCryptPasswordEncoder.encode(password));
            u=usersRepository.save(u);
            result.result=true;
            result.data=u.getPassword();

            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("user_id", user_id);
        mav.addObject("user_name", user_name);
        mav.addObject("user_surname", user_surname);

        mav.setViewName("/user_settings/pass_detail");
        return mav;
    }
 }
