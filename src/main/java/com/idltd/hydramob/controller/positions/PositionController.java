package com.idltd.hydramob.controller.positions;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.positions.Positions;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.positions.DetailPositionsRepository;
import com.idltd.hydramob.repository.positions.MenuPositionsRepository;
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
@RequestMapping("/positions")
public class PositionController {

    private MenuPositionsRepository menuPositionsRepository;
    private DetailPositionsRepository detailPositionsRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    public PositionController(
            MenuPositionsRepository menuPositionsRepository,
            User_ListRepository user_ListRepository,
            DetailPositionsRepository detailPositionsRepository,
            User_Role_ListRepository user_Role_ListRepository
    ) {
        this.menuPositionsRepository = menuPositionsRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailPositionsRepository = detailPositionsRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "pos_id", required = false) Long pos_id,
                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page

    ){
        model.addObject("pos_id", pos_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("positions/cover");
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

        result.setData(menuPositionsRepository.queryMenuPositionByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="pos_id") Long pos_id,
                                   @RequestParam(value="positions_table_order_column") Long order_column,
                                   @RequestParam(value="positions_table_order_type") String order_type,
                                   @RequestParam(value="positions_table_search") String table_search,
                                   @RequestParam(value="positions_table_pagelen") Long pagelen,
                                   @RequestParam(value="positions_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<Positions> positionDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){

        }
        else{
            positionDetail = detailPositionsRepository.queryDetailPositionByID(user_List.get(0).id,user_Role_List.get(0).role_id, pos_id);

            mav.addObject("pos_id", positionDetail.get(0).pos_id);
            mav.addObject("pos_name", positionDetail.get(0).pos_name);
            mav.addObject("pos_sname", positionDetail.get(0).pos_sname);
            mav.addObject("pos_description", positionDetail.get(0).pos_description);
        }

        mav.addObject("positions_table_order_column", order_column);
        mav.addObject("positions_table_order_type", order_type);
        mav.addObject("positions_table_search", table_search);
        mav.addObject("positions_table_pagelen", pagelen);
        mav.addObject("positions_table_page", page);

        mav.setViewName("/positions/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="pos_id") Long pos_id,
            @RequestParam(value="pos_name", required = false) String pos_name,
            @RequestParam(value="pos_sname", required = false) String pos_sname,
            @RequestParam(value="pos_description", required = false) String pos_description,

            @RequestParam(value="positions_table_order_column", required = false) Long order_column,
            @RequestParam(value="positions_table_order_type", required = false) String order_type,
            @RequestParam(value="positions_table_search", required = false) String table_search,
            @RequestParam(value="positions_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="positions_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddPosQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_AddPosition")
                            .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .setParameter(1, pos_sname)
                            .setParameter(2, pos_name)
                            .setParameter(3, pos_description);
                    AddPosQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditPosQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditPosition")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .setParameter(1, pos_id)
                            .setParameter(2, pos_sname)
                            .setParameter(3, pos_name)
                            .setParameter(4, pos_description);
                    EditPosQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelPosQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_DelPosition")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .setParameter(1, pos_id);
                    DelPosQuery.execute();
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

        mav.addObject("pos_id", pos_id);
        mav.addObject("pos_name", pos_name);
        mav.addObject("pos_sname", pos_sname);
        mav.addObject("pos_description", pos_description);

        mav.addObject("positions_table_order_column", order_column);
        mav.addObject("positions_table_order_type", order_type);
        mav.addObject("positions_table_search", table_search);
        mav.addObject("positions_table_pagelen", pagelen);
        mav.addObject("positions_table_page", page);

        mav.setViewName("/positions/detail");
        return mav;
    }
}
