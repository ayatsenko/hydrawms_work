package com.idltd.hydramob.controller.clients_abc;

import com.idltd.hydramob.entity.Service_Type_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.ClientClassList;
import com.idltd.hydramob.repository.Service_Type_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.clients_abc.MenuClientsABCDetailRepository;
import com.idltd.hydramob.repository.list.ClientClassListRepository;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/clients_abc")
public class ClientsABCController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private ClientClassListRepository clientClassListRepository;

    private MenuClientsABCDetailRepository menuClientsABCDetailRepository;

    public ClientsABCController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            ClientClassListRepository clientClassListRepository,

            MenuClientsABCDetailRepository menuClientsABCDetailRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.clientClassListRepository = clientClassListRepository;

        this.menuClientsABCDetailRepository = menuClientsABCDetailRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Service_Type_List> service_Type_List;
        List<ClientClassList> editClientClassList;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("ser_id", 0);
        service_Type_List = service_Type_ListRepository.queryReportSerIDByUserID(user_List.get(0).id);
        mav.addObject("service_list", service_Type_List);

        editClientClassList = clientClassListRepository.queryAllClientClassListBy();
        mav.addObject("edit_classes_list", editClientClassList);

        mav.setViewName("clients_abc/cover");
        return mav;
    }

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_ser_id") Long req_ser_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(start_date.length() > 0 && req_ser_id != null) {
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

            result.setData(menuClientsABCDetailRepository.queryGetClientsABCDetail(user_List.get(0).id, user_Role_List.get(0).role_id, req_ser_id, StartResult, EndResult));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/add_class")
    public ModelAndView add_class(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "edit_class_id") Long edit_class_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContClass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                .setParameter(4, edit_class_id)
                ;
        AddClientClassQuery.execute();
        return null;
    }

    @PostMapping("/edit_class")
    public ModelAndView edit_class(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "edit_class_id") Long edit_class_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContClass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                .setParameter(4, edit_class_id)
                ;
        EditClientClassQuery.execute();
        return null;
    }

    @PostMapping("/del_class")
    public ModelAndView del_class(
            @RequestParam(name = "cnt_id") Long cnt_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DelClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContClass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                ;
        DelClientClassQuery.execute();
        return null;
    }
}
