package com.idltd.hydramob.controller.directions;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.directions.Direction;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.directions.DetailDirectionsRepository;
import com.idltd.hydramob.repository.directions.MenuDirectionsRepository;
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
@RequestMapping("/directions")
public class DirectionController {

    private MenuDirectionsRepository menuDirectionsRepository;
    private DetailDirectionsRepository detailDirectionsRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    public DirectionController(
            MenuDirectionsRepository menuDirectionsRepository,
            User_ListRepository user_ListRepository,
            DetailDirectionsRepository detailDirectionsRepository,
            User_Role_ListRepository user_Role_ListRepository
    ) {
        this.menuDirectionsRepository = menuDirectionsRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailDirectionsRepository = detailDirectionsRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "dir_id", required = false) Long dir_id,
                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page

    ){
        model.addObject("dir_id", dir_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("directions/cover");
        return model;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuDirectionsRepository.queryMenuDirectionByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="dir_id") Long dir_id,
                                   @RequestParam(value="directions_table_order_column") Long order_column,
                                   @RequestParam(value="directions_table_order_type") String order_type,
                                   @RequestParam(value="directions_table_search") String table_search,
                                   @RequestParam(value="directions_table_pagelen") Long pagelen,
                                   @RequestParam(value="directions_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<Direction> directionDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){

        }
        else{
            directionDetail = detailDirectionsRepository.queryDetailDirectionByID(user_List.get(0).id,user_Role_List.get(0).role_id, dir_id);

            mav.addObject("dir_id", directionDetail.get(0).dir_id);
            mav.addObject("dir_name", directionDetail.get(0).dir_name);
            mav.addObject("dir_sname", directionDetail.get(0).dir_sname);
            mav.addObject("dir_description", directionDetail.get(0).dir_description);
        }

        mav.addObject("directions_table_order_column", order_column);
        mav.addObject("directions_table_order_type", order_type);
        mav.addObject("directions_table_search", table_search);
        mav.addObject("directions_table_pagelen", pagelen);
        mav.addObject("directions_table_page", page);

        mav.setViewName("/directions/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="dir_id") Long dir_id,
            @RequestParam(value="dir_name", required = false) String dir_name,
            @RequestParam(value="dir_sname", required = false) String dir_sname,
            @RequestParam(value="dir_description", required = false) String dir_description,

            @RequestParam(value="directions_table_order_column", required = false) Long order_column,
            @RequestParam(value="directions_table_order_type", required = false) String order_type,
            @RequestParam(value="directions_table_search", required = false) String table_search,
            @RequestParam(value="directions_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="directions_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddDirectionQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_AddDirection")
                            .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .setParameter(1, dir_sname)
                            .setParameter(2, dir_name)
                            .setParameter(3, dir_description);
                    AddDirectionQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditDirectionQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditDirection")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .setParameter(1, dir_id)
                            .setParameter(2, dir_sname)
                            .setParameter(3, dir_name)
                            .setParameter(4, dir_description);
                    EditDirectionQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelDirectionQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_DelDirection")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .setParameter(1, dir_id);
                    DelDirectionQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("dir_id", dir_id);
        mav.addObject("dir_name", dir_name);
        mav.addObject("dir_sname", dir_sname);
        mav.addObject("dir_description", dir_description);

        mav.addObject("directions_table_order_column", order_column);
        mav.addObject("directions_table_order_type", order_type);
        mav.addObject("directions_table_search", table_search);
        mav.addObject("directions_table_pagelen", pagelen);
        mav.addObject("directions_table_page", page);

        mav.setViewName("/directions/detail");
        return mav;
    }
}
