package com.idltd.hydramob.controller.user_interface_context;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.user_interface_context.MenuUserInterfaceContextRepository;
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
@RequestMapping("/user_interface_context")
public class UserInterfaceContextController {
    private final User_ListRepository user_ListRepository;
    private final MenuUserInterfaceContextRepository menuUserInterfaceContextRepository;

    public UserInterfaceContextController(
            User_ListRepository user_ListRepository,
            MenuUserInterfaceContextRepository menuUserInterfaceContextRepository
                                ) {
        this.user_ListRepository = user_ListRepository;
        this.menuUserInterfaceContextRepository = menuUserInterfaceContextRepository;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable(
            @RequestParam(value="role_id") Long role_id,
            @RequestParam(value="menu_id") Long menu_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> User_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        User_List = user_ListRepository.queryUserByName(authname);

        if(role_id != null && menu_id != null) {
            result.setData(menuUserInterfaceContextRepository.queryUserInterfaceContextByID(User_List.get(0).id, role_id, menu_id));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/up")
    public ModelAndView up(
            @RequestParam(name = "mcurl_id") long mcurl_id
    ) {
        List<User_List> User_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        User_List = user_ListRepository.queryUserByName(authname);

        StoredProcedureQuery MailAskQuery = entityManager
                .createStoredProcedureQuery("PKG_USERS.pr_UserContextUp")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .setParameter(1, User_List.get(0).id)
                .setParameter(2, mcurl_id);
        MailAskQuery.execute();
        return null;
    }

    @PostMapping("/down")
    public ModelAndView down(
            @RequestParam(name = "mcurl_id") long mcurl_id
    ) {
        List<User_List> User_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        User_List = user_ListRepository.queryUserByName(authname);

        StoredProcedureQuery MailAskQuery = entityManager
                .createStoredProcedureQuery("PKG_USERS.pr_UserContextDown")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .setParameter(1, User_List.get(0).id)
                .setParameter(2, mcurl_id);
        MailAskQuery.execute();
        return null;
    }

    @PostMapping("/hide")
    public ModelAndView send_user_mail(
            @RequestParam(name = "mcurl_id") long mcurl_id
    ) {
        List<User_List> User_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        User_List = user_ListRepository.queryUserByName(authname);

        StoredProcedureQuery MailAskQuery = entityManager
                .createStoredProcedureQuery("PKG_USERS.pr_UserContextHide")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .setParameter(1, User_List.get(0).id)
                .setParameter(2, mcurl_id);
        MailAskQuery.execute();
        return null;
    }
}
